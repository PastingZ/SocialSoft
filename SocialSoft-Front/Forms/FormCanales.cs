using SocialSoft.Client.Models;
using SocialSoft.Client.Services;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;

namespace SocialSoft.Client.Forms
{
    /// <summary>
    /// Formulario para gestionar Canales.
    /// Consume los endpoints REST:
    ///   GET    /canales                       → Listar todos
    ///   GET    /canales/{id}                  → Buscar por ID
    ///   GET    /canales/categoria/{categoria} → Buscar por categoría
    ///   POST   /canales                       → Insertar
    ///   PUT    /canales                       → Actualizar
    ///   DELETE /canales/{id}                  → Eliminar
    /// </summary>
    public class FormCanales : Form
    {
        private TextBox txtId, txtNombre, txtDescripcion, txtSeguidores, txtCategoria;
        private DataGridView dgvCanales;
        private Label lblStatus;
        private RestService _restService;

        private const string BASE_URL = "http://localhost:8080/SocialSoft-REST-1.0-SNAPSHOT";

        public FormCanales()
        {
            _restService = new RestService(BASE_URL);
            InicializarComponentes();
        }

        private void InicializarComponentes()
        {
            this.Text = "📺 Gestión de Canales - REST Client";
            this.Size = new Size(900, 550);
            this.StartPosition = FormStartPosition.CenterScreen;
            this.BackColor = Color.FromArgb(30, 30, 46);

            // ─── Panel de campos ───
            Panel panelCampos = new Panel();
            panelCampos.Location = new Point(10, 10);
            panelCampos.Size = new Size(320, 250);
            panelCampos.BackColor = Color.FromArgb(36, 39, 58);
            this.Controls.Add(panelCampos);

            int y = 10;
            txtId = CrearCampo(panelCampos, "ID:", ref y);
            txtNombre = CrearCampo(panelCampos, "Nombre:", ref y);
            txtDescripcion = CrearCampo(panelCampos, "Descripción:", ref y);
            txtSeguidores = CrearCampo(panelCampos, "N° Seguidores:", ref y);
            txtCategoria = CrearCampo(panelCampos, "Categoría:", ref y);

            // ─── Panel de botones ───
            Panel panelBotones = new Panel();
            panelBotones.Location = new Point(10, 270);
            panelBotones.Size = new Size(320, 200);
            panelBotones.BackColor = Color.FromArgb(36, 39, 58);
            this.Controls.Add(panelBotones);

            int btnY = 10;
            int btnW = 145;
            int btnH = 38;

            // LISTAR TODOS
            Button btnListar = CrearBotonAccion("📋 Listar Todos", 10, btnY, btnW, btnH, Color.FromArgb(137, 180, 250));
            btnListar.Click += async (s, e) =>
            {
                try
                {
                    lblStatus.Text = "⏳ GET /canales ...";
                    List<Canal> lista = await _restService.GetListAsync<Canal>("/canales");
                    dgvCanales.DataSource = lista;
                    lblStatus.Text = $"✅ {lista.Count} canales encontrados.";
                }
                catch (Exception ex)
                {
                    lblStatus.Text = "❌ Error";
                    MessageBox.Show(ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            };
            panelBotones.Controls.Add(btnListar);

            // BUSCAR POR ID
            Button btnBuscar = CrearBotonAccion("🔍 Buscar por ID", 165, btnY, btnW, btnH, Color.FromArgb(148, 226, 213));
            btnBuscar.Click += async (s, e) =>
            {
                try
                {
                    if (string.IsNullOrEmpty(txtId.Text)) { MessageBox.Show("Ingresa un ID"); return; }
                    lblStatus.Text = $"⏳ GET /canales/{txtId.Text} ...";
                    Canal c = await _restService.GetAsync<Canal>($"/canales/{txtId.Text}");
                    if (c != null)
                    {
                        txtNombre.Text = c.Nombre;
                        txtDescripcion.Text = c.Descripcion;
                        txtSeguidores.Text = c.NumeroSeguidores.ToString();
                        txtCategoria.Text = c.Categoria;
                        lblStatus.Text = $"✅ Canal: {c.Nombre}";
                    }
                }
                catch (Exception ex)
                {
                    lblStatus.Text = "❌ Error";
                    MessageBox.Show(ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            };
            panelBotones.Controls.Add(btnBuscar);

            btnY += 48;

            // BUSCAR POR CATEGORÍA
            Button btnPorCategoria = CrearBotonAccion("🏷️ Por Categoría", 10, btnY, btnW, btnH, Color.FromArgb(203, 166, 247));
            btnPorCategoria.Click += async (s, e) =>
            {
                try
                {
                    if (string.IsNullOrEmpty(txtCategoria.Text)) { MessageBox.Show("Ingresa una categoría"); return; }
                    lblStatus.Text = $"⏳ GET /canales/categoria/{txtCategoria.Text} ...";
                    List<Canal> lista = await _restService.GetListAsync<Canal>($"/canales/categoria/{txtCategoria.Text}");
                    dgvCanales.DataSource = lista;
                    lblStatus.Text = $"✅ {lista.Count} canales en categoría '{txtCategoria.Text}'.";
                }
                catch (Exception ex)
                {
                    lblStatus.Text = "❌ Error";
                    MessageBox.Show(ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            };
            panelBotones.Controls.Add(btnPorCategoria);

            // INSERTAR
            Button btnInsertar = CrearBotonAccion("➕ Insertar", 165, btnY, btnW, btnH, Color.FromArgb(166, 227, 161));
            btnInsertar.Click += async (s, e) =>
            {
                try
                {
                    var canal = new Canal
                    {
                        Nombre = txtNombre.Text,
                        Descripcion = txtDescripcion.Text,
                        NumeroSeguidores = int.TryParse(txtSeguidores.Text, out int seg) ? seg : 0,
                        Categoria = txtCategoria.Text
                    };
                    lblStatus.Text = "⏳ POST /canales ...";
                    int res = await _restService.PostAsync<Canal, int>("/canales", canal);
                    lblStatus.Text = $"✅ Canal insertado. Resultado: {res}";
                    MessageBox.Show($"Canal insertado. Resultado: {res}", "Éxito");
                }
                catch (Exception ex)
                {
                    lblStatus.Text = "❌ Error";
                    MessageBox.Show(ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            };
            panelBotones.Controls.Add(btnInsertar);

            btnY += 48;

            // ACTUALIZAR
            Button btnActualizar = CrearBotonAccion("✏️ Actualizar", 10, btnY, btnW, btnH, Color.FromArgb(249, 226, 175));
            btnActualizar.Click += async (s, e) =>
            {
                try
                {
                    var canal = new Canal
                    {
                        Id = int.TryParse(txtId.Text, out int id) ? id : 0,
                        Nombre = txtNombre.Text,
                        Descripcion = txtDescripcion.Text,
                        NumeroSeguidores = int.TryParse(txtSeguidores.Text, out int seg) ? seg : 0,
                        Categoria = txtCategoria.Text
                    };
                    lblStatus.Text = "⏳ PUT /canales ...";
                    int res = await _restService.PutAsync<Canal, int>("/canales", canal);
                    lblStatus.Text = $"✅ Canal actualizado. Resultado: {res}";
                    MessageBox.Show($"Canal actualizado. Resultado: {res}", "Éxito");
                }
                catch (Exception ex)
                {
                    lblStatus.Text = "❌ Error";
                    MessageBox.Show(ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            };
            panelBotones.Controls.Add(btnActualizar);

            // ELIMINAR
            Button btnEliminar = CrearBotonAccion("🗑️ Eliminar", 165, btnY, btnW, btnH, Color.FromArgb(243, 139, 168));
            btnEliminar.Click += async (s, e) =>
            {
                try
                {
                    if (string.IsNullOrEmpty(txtId.Text)) { MessageBox.Show("Ingresa un ID"); return; }
                    var conf = MessageBox.Show($"¿Eliminar canal ID {txtId.Text}?", "Confirmar", MessageBoxButtons.YesNo, MessageBoxIcon.Warning);
                    if (conf == DialogResult.Yes)
                    {
                        lblStatus.Text = $"⏳ DELETE /canales/{txtId.Text} ...";
                        int res = await _restService.DeleteAsync<int>($"/canales/{txtId.Text}");
                        lblStatus.Text = $"✅ Canal eliminado. Resultado: {res}";
                        LimpiarCampos();
                    }
                }
                catch (Exception ex)
                {
                    lblStatus.Text = "❌ Error";
                    MessageBox.Show(ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            };
            panelBotones.Controls.Add(btnEliminar);

            // ─── DataGridView ───
            dgvCanales = new DataGridView();
            dgvCanales.Location = new Point(340, 10);
            dgvCanales.Size = new Size(535, 450);
            dgvCanales.BackgroundColor = Color.FromArgb(36, 39, 58);
            dgvCanales.ForeColor = Color.FromArgb(205, 214, 244);
            dgvCanales.GridColor = Color.FromArgb(69, 71, 90);
            dgvCanales.DefaultCellStyle.BackColor = Color.FromArgb(36, 39, 58);
            dgvCanales.DefaultCellStyle.ForeColor = Color.FromArgb(205, 214, 244);
            dgvCanales.DefaultCellStyle.SelectionBackColor = Color.FromArgb(69, 71, 90);
            dgvCanales.ColumnHeadersDefaultCellStyle.BackColor = Color.FromArgb(49, 50, 68);
            dgvCanales.ColumnHeadersDefaultCellStyle.ForeColor = Color.FromArgb(205, 214, 244);
            dgvCanales.EnableHeadersVisualStyles = false;
            dgvCanales.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dgvCanales.ReadOnly = true;
            dgvCanales.AllowUserToAddRows = false;
            dgvCanales.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            dgvCanales.CellClick += (s, e) =>
            {
                if (e.RowIndex >= 0)
                {
                    var row = dgvCanales.Rows[e.RowIndex];
                    txtId.Text = row.Cells["Id"].Value?.ToString() ?? "";
                    txtNombre.Text = row.Cells["Nombre"].Value?.ToString() ?? "";
                    txtDescripcion.Text = row.Cells["Descripcion"].Value?.ToString() ?? "";
                    txtSeguidores.Text = row.Cells["NumeroSeguidores"].Value?.ToString() ?? "";
                    txtCategoria.Text = row.Cells["Categoria"].Value?.ToString() ?? "";
                }
            };
            this.Controls.Add(dgvCanales);

            // ─── Status ───
            lblStatus = new Label();
            lblStatus.Location = new Point(10, 480);
            lblStatus.Size = new Size(860, 25);
            lblStatus.Font = new Font("Segoe UI", 9);
            lblStatus.ForeColor = Color.FromArgb(166, 227, 161);
            lblStatus.Text = "🟢 Listo. Conectando a: " + BASE_URL;
            this.Controls.Add(lblStatus);
        }

        private TextBox CrearCampo(Panel panel, string label, ref int y)
        {
            Label lbl = new Label();
            lbl.Text = label;
            lbl.Location = new Point(10, y);
            lbl.AutoSize = true;
            lbl.Font = new Font("Segoe UI", 9);
            lbl.ForeColor = Color.FromArgb(166, 173, 200);
            panel.Controls.Add(lbl);

            y += 18;
            TextBox txt = new TextBox();
            txt.Location = new Point(10, y);
            txt.Size = new Size(295, 25);
            txt.BackColor = Color.FromArgb(49, 50, 68);
            txt.ForeColor = Color.FromArgb(205, 214, 244);
            txt.BorderStyle = BorderStyle.FixedSingle;
            txt.Font = new Font("Segoe UI", 9);
            panel.Controls.Add(txt);

            y += 30;
            return txt;
        }

        private Button CrearBotonAccion(string texto, int x, int y, int w, int h, Color borderColor)
        {
            Button btn = new Button();
            btn.Text = texto;
            btn.Location = new Point(x, y);
            btn.Size = new Size(w, h);
            btn.FlatStyle = FlatStyle.Flat;
            btn.FlatAppearance.BorderColor = borderColor;
            btn.FlatAppearance.BorderSize = 1;
            btn.BackColor = Color.FromArgb(49, 50, 68);
            btn.ForeColor = Color.FromArgb(205, 214, 244);
            btn.Font = new Font("Segoe UI", 9, FontStyle.Bold);
            btn.Cursor = Cursors.Hand;
            btn.MouseEnter += (s, e) => { btn.BackColor = Color.FromArgb(69, 71, 90); };
            btn.MouseLeave += (s, e) => { btn.BackColor = Color.FromArgb(49, 50, 68); };
            return btn;
        }

        private void LimpiarCampos()
        {
            txtId.Text = txtNombre.Text = txtDescripcion.Text = txtSeguidores.Text = txtCategoria.Text = "";
            lblStatus.Text = "🧹 Campos limpiados.";
        }
    }
}

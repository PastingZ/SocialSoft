using SocialSoft.Client.Models;
using SocialSoft.Client.Services;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;

namespace SocialSoft.Client.Forms
{
    /// <summary>
    /// Formulario para gestionar Planes de Suscripción.
    /// Consume los endpoints REST:
    ///   GET    /planes          → Listar todos
    ///   GET    /planes/{id}     → Buscar por ID
    ///   POST   /planes          → Insertar
    ///   PUT    /planes          → Actualizar
    ///   DELETE /planes/{id}     → Eliminar
    /// </summary>
    public class FormPlanes : Form
    {
        private TextBox txtId, txtNombre, txtCosto;
        private DataGridView dgvPlanes;
        private Label lblStatus;
        private RestService _restService;

        private const string BASE_URL = "http://localhost:8080/SocialSoft-REST-1.0-SNAPSHOT";

        public FormPlanes()
        {
            _restService = new RestService(BASE_URL);
            InicializarComponentes();
        }

        private void InicializarComponentes()
        {
            this.Text = "📋 Gestión de Planes de Suscripción - REST Client";
            this.Size = new Size(800, 480);
            this.StartPosition = FormStartPosition.CenterScreen;
            this.BackColor = Color.FromArgb(30, 30, 46);

            // ─── Panel de campos ───
            Panel panelCampos = new Panel();
            panelCampos.Location = new Point(10, 10);
            panelCampos.Size = new Size(300, 160);
            panelCampos.BackColor = Color.FromArgb(36, 39, 58);
            this.Controls.Add(panelCampos);

            int y = 10;
            txtId = CrearCampo(panelCampos, "ID:", ref y);
            txtNombre = CrearCampo(panelCampos, "Nombre del Plan:", ref y);
            txtCosto = CrearCampo(panelCampos, "Costo Mensual (S/):", ref y);

            // ─── Panel de botones ───
            Panel panelBotones = new Panel();
            panelBotones.Location = new Point(10, 180);
            panelBotones.Size = new Size(300, 210);
            panelBotones.BackColor = Color.FromArgb(36, 39, 58);
            this.Controls.Add(panelBotones);

            int btnY = 10;
            int btnW = 135;
            int btnH = 38;

            // LISTAR
            Button btnListar = CrearBotonAccion("📋 Listar Todos", 10, btnY, btnW, btnH, Color.FromArgb(137, 180, 250));
            btnListar.Click += async (s, e) =>
            {
                try
                {
                    lblStatus.Text = "⏳ GET /planes ...";
                    List<PlanSuscripcion> lista = await _restService.GetListAsync<PlanSuscripcion>("/planes");
                    dgvPlanes.DataSource = lista;
                    lblStatus.Text = $"✅ {lista.Count} planes encontrados.";
                }
                catch (Exception ex)
                {
                    lblStatus.Text = "❌ Error";
                    MessageBox.Show(ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            };
            panelBotones.Controls.Add(btnListar);

            // BUSCAR
            Button btnBuscar = CrearBotonAccion("🔍 Buscar ID", 155, btnY, btnW, btnH, Color.FromArgb(148, 226, 213));
            btnBuscar.Click += async (s, e) =>
            {
                try
                {
                    if (string.IsNullOrEmpty(txtId.Text)) { MessageBox.Show("Ingresa un ID"); return; }
                    lblStatus.Text = $"⏳ GET /planes/{txtId.Text} ...";
                    PlanSuscripcion p = await _restService.GetAsync<PlanSuscripcion>($"/planes/{txtId.Text}");
                    if (p != null)
                    {
                        txtNombre.Text = p.Nombre;
                        txtCosto.Text = p.CostoMensual.ToString("F2");
                        lblStatus.Text = $"✅ Plan: {p.Nombre} - S/ {p.CostoMensual:F2}";
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

            // INSERTAR
            Button btnInsertar = CrearBotonAccion("➕ Insertar", 10, btnY, btnW, btnH, Color.FromArgb(166, 227, 161));
            btnInsertar.Click += async (s, e) =>
            {
                try
                {
                    var plan = new PlanSuscripcion
                    {
                        Nombre = txtNombre.Text,
                        CostoMensual = double.TryParse(txtCosto.Text, out double costo) ? costo : 0
                    };
                    lblStatus.Text = "⏳ POST /planes ...";
                    int res = await _restService.PostAsync<PlanSuscripcion, int>("/planes", plan);
                    lblStatus.Text = $"✅ Plan insertado. Resultado: {res}";
                    MessageBox.Show($"Plan insertado. Resultado: {res}", "Éxito");
                }
                catch (Exception ex)
                {
                    lblStatus.Text = "❌ Error";
                    MessageBox.Show(ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            };
            panelBotones.Controls.Add(btnInsertar);

            // ACTUALIZAR
            Button btnActualizar = CrearBotonAccion("✏️ Actualizar", 155, btnY, btnW, btnH, Color.FromArgb(249, 226, 175));
            btnActualizar.Click += async (s, e) =>
            {
                try
                {
                    var plan = new PlanSuscripcion
                    {
                        Id = int.TryParse(txtId.Text, out int id) ? id : 0,
                        Nombre = txtNombre.Text,
                        CostoMensual = double.TryParse(txtCosto.Text, out double costo) ? costo : 0
                    };
                    lblStatus.Text = "⏳ PUT /planes ...";
                    int res = await _restService.PutAsync<PlanSuscripcion, int>("/planes", plan);
                    lblStatus.Text = $"✅ Plan actualizado. Resultado: {res}";
                    MessageBox.Show($"Plan actualizado. Resultado: {res}", "Éxito");
                }
                catch (Exception ex)
                {
                    lblStatus.Text = "❌ Error";
                    MessageBox.Show(ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            };
            panelBotones.Controls.Add(btnActualizar);

            btnY += 48;

            // ELIMINAR
            Button btnEliminar = CrearBotonAccion("🗑️ Eliminar", 10, btnY, btnW, btnH, Color.FromArgb(243, 139, 168));
            btnEliminar.Click += async (s, e) =>
            {
                try
                {
                    if (string.IsNullOrEmpty(txtId.Text)) { MessageBox.Show("Ingresa un ID"); return; }
                    var conf = MessageBox.Show($"¿Eliminar plan ID {txtId.Text}?", "Confirmar", MessageBoxButtons.YesNo, MessageBoxIcon.Warning);
                    if (conf == DialogResult.Yes)
                    {
                        lblStatus.Text = $"⏳ DELETE /planes/{txtId.Text} ...";
                        int res = await _restService.DeleteAsync<int>($"/planes/{txtId.Text}");
                        lblStatus.Text = $"✅ Plan eliminado. Resultado: {res}";
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

            // LIMPIAR
            Button btnLimpiar = CrearBotonAccion("🧹 Limpiar", 155, btnY, btnW, btnH, Color.FromArgb(180, 190, 254));
            btnLimpiar.Click += (s, e) => LimpiarCampos();
            panelBotones.Controls.Add(btnLimpiar);

            // ─── DataGridView ───
            dgvPlanes = new DataGridView();
            dgvPlanes.Location = new Point(320, 10);
            dgvPlanes.Size = new Size(455, 380);
            dgvPlanes.BackgroundColor = Color.FromArgb(36, 39, 58);
            dgvPlanes.ForeColor = Color.FromArgb(205, 214, 244);
            dgvPlanes.GridColor = Color.FromArgb(69, 71, 90);
            dgvPlanes.DefaultCellStyle.BackColor = Color.FromArgb(36, 39, 58);
            dgvPlanes.DefaultCellStyle.ForeColor = Color.FromArgb(205, 214, 244);
            dgvPlanes.DefaultCellStyle.SelectionBackColor = Color.FromArgb(69, 71, 90);
            dgvPlanes.ColumnHeadersDefaultCellStyle.BackColor = Color.FromArgb(49, 50, 68);
            dgvPlanes.ColumnHeadersDefaultCellStyle.ForeColor = Color.FromArgb(205, 214, 244);
            dgvPlanes.EnableHeadersVisualStyles = false;
            dgvPlanes.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dgvPlanes.ReadOnly = true;
            dgvPlanes.AllowUserToAddRows = false;
            dgvPlanes.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            dgvPlanes.CellClick += (s, e) =>
            {
                if (e.RowIndex >= 0)
                {
                    var row = dgvPlanes.Rows[e.RowIndex];
                    txtId.Text = row.Cells["Id"].Value?.ToString() ?? "";
                    txtNombre.Text = row.Cells["Nombre"].Value?.ToString() ?? "";
                    txtCosto.Text = row.Cells["CostoMensual"].Value?.ToString() ?? "";
                }
            };
            this.Controls.Add(dgvPlanes);

            // ─── Status ───
            lblStatus = new Label();
            lblStatus.Location = new Point(10, 410);
            lblStatus.Size = new Size(760, 25);
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
            txt.Size = new Size(275, 25);
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
            txtId.Text = txtNombre.Text = txtCosto.Text = "";
            lblStatus.Text = "🧹 Campos limpiados.";
        }
    }
}

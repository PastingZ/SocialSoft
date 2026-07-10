using SocialSoft.Client.Models;
using SocialSoft.Client.Services;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;

namespace SocialSoft.Client.Forms
{
    /// <summary>
    /// Formulario para gestionar Usuarios.
    /// Consume los endpoints REST:
    ///   GET    /usuarios          → Listar todos
    ///   GET    /usuarios/{id}     → Buscar por ID
    ///   POST   /usuarios          → Insertar
    ///   PUT    /usuarios          → Actualizar
    ///   DELETE /usuarios/{id}     → Eliminar
    /// </summary>
    public class FormUsuarios : Form
    {
        // ─── Controles del formulario ───
        private TextBox txtId, txtNombre, txtDni, txtEdad, txtCiudad, txtTelefono, txtCorreo, txtProfesion;
        private DataGridView dgvUsuarios;
        private Label lblStatus;

        // ─── El servicio REST que usaremos ───
        private RestService _restService;

        // La URL base de tu API. Cámbiala si tu servidor corre en otro puerto.
        private const string BASE_URL = "http://localhost:8080/SocialSoft-REST-1.0-SNAPSHOT";

        public FormUsuarios()
        {
            // Creamos la instancia del servicio REST
            _restService = new RestService(BASE_URL);

            // Configuramos la pantalla
            InicializarComponentes();
        }

        private void InicializarComponentes()
        {
            // ─── Configuración del formulario ───
            this.Text = "👤 Gestión de Usuarios - REST Client";
            this.Size = new Size(900, 650);
            this.StartPosition = FormStartPosition.CenterScreen;
            this.BackColor = Color.FromArgb(30, 30, 46);

            // ─── Panel de campos (izquierda) ───
            Panel panelCampos = new Panel();
            panelCampos.Location = new Point(10, 10);
            panelCampos.Size = new Size(320, 350);
            panelCampos.BackColor = Color.FromArgb(36, 39, 58);
            this.Controls.Add(panelCampos);

            int y = 10;
            txtId = CrearCampo(panelCampos, "ID:", ref y);
            txtNombre = CrearCampo(panelCampos, "Nombre Completo:", ref y);
            txtDni = CrearCampo(panelCampos, "DNI:", ref y);
            txtEdad = CrearCampo(panelCampos, "Edad:", ref y);
            txtCiudad = CrearCampo(panelCampos, "Ciudad:", ref y);
            txtTelefono = CrearCampo(panelCampos, "Teléfono:", ref y);
            txtCorreo = CrearCampo(panelCampos, "Correo:", ref y);
            txtProfesion = CrearCampo(panelCampos, "Profesión:", ref y);

            // ─── Panel de botones ───
            Panel panelBotones = new Panel();
            panelBotones.Location = new Point(10, 370);
            panelBotones.Size = new Size(320, 200);
            panelBotones.BackColor = Color.FromArgb(36, 39, 58);
            this.Controls.Add(panelBotones);

            int btnY = 10;
            int btnW = 145;
            int btnH = 38;

            // Botón LISTAR TODOS
            Button btnListar = CrearBotonAccion("📋 Listar Todos", 10, btnY, btnW, btnH, Color.FromArgb(137, 180, 250));
            btnListar.Click += async (s, e) =>
            {
                try
                {
                    lblStatus.Text = "⏳ Llamando GET /usuarios ...";
                    // ¡AQUÍ CONSUMIMOS EL SERVICIO REST!
                    List<Usuario> lista = await _restService.GetListAsync<Usuario>("/usuarios");
                    dgvUsuarios.DataSource = lista;
                    lblStatus.Text = $"✅ Se encontraron {lista.Count} usuarios.";
                }
                catch (Exception ex)
                {
                    lblStatus.Text = "❌ Error al listar";
                    MessageBox.Show($"Error: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            };
            panelBotones.Controls.Add(btnListar);

            // Botón BUSCAR POR ID
            Button btnBuscar = CrearBotonAccion("🔍 Buscar por ID", 165, btnY, btnW, btnH, Color.FromArgb(148, 226, 213));
            btnBuscar.Click += async (s, e) =>
            {
                try
                {
                    if (string.IsNullOrEmpty(txtId.Text))
                    {
                        MessageBox.Show("Ingresa un ID para buscar", "Aviso");
                        return;
                    }
                    lblStatus.Text = $"⏳ Llamando GET /usuarios/{txtId.Text} ...";
                    Usuario u = await _restService.GetAsync<Usuario>($"/usuarios/{txtId.Text}");
                    if (u != null)
                    {
                        // Cargamos los datos en los campos
                        txtNombre.Text = u.NombreCompleto;
                        txtDni.Text = u.Dni;
                        txtEdad.Text = u.Edad.ToString();
                        txtCiudad.Text = u.Ciudad;
                        txtTelefono.Text = u.Telefono;
                        txtCorreo.Text = u.Correo;
                        txtProfesion.Text = u.Profesion;
                        lblStatus.Text = $"✅ Usuario encontrado: {u.NombreCompleto}";
                    }
                }
                catch (Exception ex)
                {
                    lblStatus.Text = "❌ Error al buscar";
                    MessageBox.Show($"Error: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            };
            panelBotones.Controls.Add(btnBuscar);

            btnY += 48;

            // Botón INSERTAR
            Button btnInsertar = CrearBotonAccion("➕ Insertar", 10, btnY, btnW, btnH, Color.FromArgb(166, 227, 161));
            btnInsertar.Click += async (s, e) =>
            {
                try
                {
                    // Armamos el objeto con los datos de los campos
                    var usuario = new Usuario
                    {
                        NombreCompleto = txtNombre.Text,
                        Dni = txtDni.Text,
                        Edad = int.TryParse(txtEdad.Text, out int edad) ? edad : 0,
                        Ciudad = txtCiudad.Text,
                        Telefono = txtTelefono.Text,
                        Correo = txtCorreo.Text,
                        Profesion = txtProfesion.Text
                    };

                    lblStatus.Text = "⏳ Llamando POST /usuarios ...";
                    // ¡Enviamos el objeto como JSON al servidor!
                    int resultado = await _restService.PostAsync<Usuario, int>("/usuarios", usuario);
                    lblStatus.Text = $"✅ Inserción exitosa. Resultado: {resultado}";
                    MessageBox.Show($"Usuario insertado correctamente. Resultado: {resultado}", "Éxito");
                }
                catch (Exception ex)
                {
                    lblStatus.Text = "❌ Error al insertar";
                    MessageBox.Show($"Error: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            };
            panelBotones.Controls.Add(btnInsertar);

            // Botón ACTUALIZAR
            Button btnActualizar = CrearBotonAccion("✏️ Actualizar", 165, btnY, btnW, btnH, Color.FromArgb(249, 226, 175));
            btnActualizar.Click += async (s, e) =>
            {
                try
                {
                    var usuario = new Usuario
                    {
                        Id = int.TryParse(txtId.Text, out int id) ? id : 0,
                        NombreCompleto = txtNombre.Text,
                        Dni = txtDni.Text,
                        Edad = int.TryParse(txtEdad.Text, out int edad) ? edad : 0,
                        Ciudad = txtCiudad.Text,
                        Telefono = txtTelefono.Text,
                        Correo = txtCorreo.Text,
                        Profesion = txtProfesion.Text
                    };

                    lblStatus.Text = "⏳ Llamando PUT /usuarios ...";
                    int resultado = await _restService.PutAsync<Usuario, int>("/usuarios", usuario);
                    lblStatus.Text = $"✅ Actualización exitosa. Resultado: {resultado}";
                    MessageBox.Show($"Usuario actualizado. Resultado: {resultado}", "Éxito");
                }
                catch (Exception ex)
                {
                    lblStatus.Text = "❌ Error al actualizar";
                    MessageBox.Show($"Error: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            };
            panelBotones.Controls.Add(btnActualizar);

            btnY += 48;

            // Botón ELIMINAR
            Button btnEliminar = CrearBotonAccion("🗑️ Eliminar", 10, btnY, btnW, btnH, Color.FromArgb(243, 139, 168));
            btnEliminar.Click += async (s, e) =>
            {
                try
                {
                    if (string.IsNullOrEmpty(txtId.Text))
                    {
                        MessageBox.Show("Ingresa un ID para eliminar", "Aviso");
                        return;
                    }

                    var confirmar = MessageBox.Show($"¿Eliminar usuario con ID {txtId.Text}?", "Confirmar", MessageBoxButtons.YesNo, MessageBoxIcon.Warning);
                    if (confirmar == DialogResult.Yes)
                    {
                        lblStatus.Text = $"⏳ Llamando DELETE /usuarios/{txtId.Text} ...";
                        int resultado = await _restService.DeleteAsync<int>($"/usuarios/{txtId.Text}");
                        lblStatus.Text = $"✅ Eliminación exitosa. Resultado: {resultado}";
                        MessageBox.Show($"Usuario eliminado. Resultado: {resultado}", "Éxito");
                        LimpiarCampos();
                    }
                }
                catch (Exception ex)
                {
                    lblStatus.Text = "❌ Error al eliminar";
                    MessageBox.Show($"Error: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            };
            panelBotones.Controls.Add(btnEliminar);

            // Botón LIMPIAR
            Button btnLimpiar = CrearBotonAccion("🧹 Limpiar", 165, btnY, btnW, btnH, Color.FromArgb(180, 190, 254));
            btnLimpiar.Click += (s, e) => LimpiarCampos();
            panelBotones.Controls.Add(btnLimpiar);

            // ─── DataGridView (derecha) ───
            dgvUsuarios = new DataGridView();
            dgvUsuarios.Location = new Point(340, 10);
            dgvUsuarios.Size = new Size(535, 550);
            dgvUsuarios.BackgroundColor = Color.FromArgb(36, 39, 58);
            dgvUsuarios.ForeColor = Color.FromArgb(205, 214, 244);
            dgvUsuarios.GridColor = Color.FromArgb(69, 71, 90);
            dgvUsuarios.DefaultCellStyle.BackColor = Color.FromArgb(36, 39, 58);
            dgvUsuarios.DefaultCellStyle.ForeColor = Color.FromArgb(205, 214, 244);
            dgvUsuarios.DefaultCellStyle.SelectionBackColor = Color.FromArgb(69, 71, 90);
            dgvUsuarios.ColumnHeadersDefaultCellStyle.BackColor = Color.FromArgb(49, 50, 68);
            dgvUsuarios.ColumnHeadersDefaultCellStyle.ForeColor = Color.FromArgb(205, 214, 244);
            dgvUsuarios.EnableHeadersVisualStyles = false;
            dgvUsuarios.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dgvUsuarios.ReadOnly = true;
            dgvUsuarios.AllowUserToAddRows = false;
            dgvUsuarios.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            // Al hacer click en una fila, cargamos los datos en los campos
            dgvUsuarios.CellClick += (s, e) =>
            {
                if (e.RowIndex >= 0)
                {
                    var row = dgvUsuarios.Rows[e.RowIndex];
                    txtId.Text = row.Cells["Id"].Value?.ToString() ?? "";
                    txtNombre.Text = row.Cells["NombreCompleto"].Value?.ToString() ?? "";
                    txtDni.Text = row.Cells["Dni"].Value?.ToString() ?? "";
                    txtEdad.Text = row.Cells["Edad"].Value?.ToString() ?? "";
                    txtCiudad.Text = row.Cells["Ciudad"].Value?.ToString() ?? "";
                    txtTelefono.Text = row.Cells["Telefono"].Value?.ToString() ?? "";
                    txtCorreo.Text = row.Cells["Correo"].Value?.ToString() ?? "";
                    txtProfesion.Text = row.Cells["Profesion"].Value?.ToString() ?? "";
                }
            };
            this.Controls.Add(dgvUsuarios);

            // ─── Barra de estado ───
            lblStatus = new Label();
            lblStatus.Location = new Point(10, 580);
            lblStatus.Size = new Size(860, 25);
            lblStatus.Font = new Font("Segoe UI", 9);
            lblStatus.ForeColor = Color.FromArgb(166, 227, 161);
            lblStatus.Text = "🟢 Listo. Conectando a: " + BASE_URL;
            this.Controls.Add(lblStatus);
        }

        // ─── Métodos helper ───

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
            txtId.Text = "";
            txtNombre.Text = "";
            txtDni.Text = "";
            txtEdad.Text = "";
            txtCiudad.Text = "";
            txtTelefono.Text = "";
            txtCorreo.Text = "";
            txtProfesion.Text = "";
            lblStatus.Text = "🧹 Campos limpiados.";
        }
    }
}

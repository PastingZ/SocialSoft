using System;
using System.Drawing;
using System.Windows.Forms;

namespace SocialSoft.Client.Forms
{
    /// <summary>
    /// Formulario principal - Menú para navegar a las pantallas de cada entidad.
    /// </summary>
    public class FormMenu : Form
    {
        public FormMenu()
        {
            // ─── Configuración del formulario ───
            this.Text = "SocialSoft - Cliente REST en C#";
            this.Size = new Size(500, 400);
            this.StartPosition = FormStartPosition.CenterScreen;
            this.FormBorderStyle = FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.BackColor = Color.FromArgb(30, 30, 46);

            // ─── Título ───
            Label lblTitulo = new Label();
            lblTitulo.Text = "🌐 SocialSoft - Cliente REST";
            lblTitulo.Font = new Font("Segoe UI", 18, FontStyle.Bold);
            lblTitulo.ForeColor = Color.FromArgb(205, 214, 244);
            lblTitulo.AutoSize = true;
            lblTitulo.Location = new Point(80, 20);
            this.Controls.Add(lblTitulo);

            // ─── Subtítulo ───
            Label lblSubtitulo = new Label();
            lblSubtitulo.Text = "Selecciona una entidad para gestionar:";
            lblSubtitulo.Font = new Font("Segoe UI", 10);
            lblSubtitulo.ForeColor = Color.FromArgb(166, 173, 200);
            lblSubtitulo.AutoSize = true;
            lblSubtitulo.Location = new Point(110, 60);
            this.Controls.Add(lblSubtitulo);

            // ─── Botones del menú ───
            int y = 110;
            int btnWidth = 300;
            int btnHeight = 50;

            Button btnUsuarios = CrearBoton("👤  Gestionar Usuarios", y, btnWidth, btnHeight);
            btnUsuarios.Click += (s, e) =>
            {
                FormUsuarios form = new FormUsuarios();
                form.ShowDialog();
            };
            this.Controls.Add(btnUsuarios);

            y += 65;
            Button btnCanales = CrearBoton("📺  Gestionar Canales", y, btnWidth, btnHeight);
            btnCanales.Click += (s, e) =>
            {
                FormCanales form = new FormCanales();
                form.ShowDialog();
            };
            this.Controls.Add(btnCanales);

            y += 65;
            Button btnPlanes = CrearBoton("📋  Gestionar Planes", y, btnWidth, btnHeight);
            btnPlanes.Click += (s, e) =>
            {
                FormPlanes form = new FormPlanes();
                form.ShowDialog();
            };
            this.Controls.Add(btnPlanes);

            y += 65;
            Button btnSoap = CrearBoton("⚡  Probar conexión SOAP", y, btnWidth, btnHeight);
            btnSoap.Click += (s, e) =>
            {
                FormSoapDemo form = new FormSoapDemo();
                form.ShowDialog();
            };
            this.Controls.Add(btnSoap);

            // ─── Etiqueta de estado ───
            y += 75;
            Label lblInfo = new Label();
            lblInfo.Text = "⚡ Asegúrate de que GlassFish esté corriendo en localhost:8080";
            lblInfo.Font = new Font("Segoe UI", 8);
            lblInfo.ForeColor = Color.FromArgb(249, 226, 175);
            lblInfo.AutoSize = true;
            lblInfo.Location = new Point(60, y);
            this.Controls.Add(lblInfo);
        }

        /// <summary>
        /// Método helper para crear botones con estilo consistente.
        /// </summary>
        private Button CrearBoton(string texto, int y, int width, int height)
        {
            Button btn = new Button();
            btn.Text = texto;
            btn.Size = new Size(width, height);
            btn.Location = new Point((this.ClientSize.Width - width) / 2, y);
            btn.FlatStyle = FlatStyle.Flat;
            btn.FlatAppearance.BorderColor = Color.FromArgb(137, 180, 250);
            btn.FlatAppearance.BorderSize = 1;
            btn.BackColor = Color.FromArgb(49, 50, 68);
            btn.ForeColor = Color.FromArgb(205, 214, 244);
            btn.Font = new Font("Segoe UI", 12, FontStyle.Bold);
            btn.Cursor = Cursors.Hand;

            // Hover effect
            btn.MouseEnter += (s, e) => { btn.BackColor = Color.FromArgb(69, 71, 90); };
            btn.MouseLeave += (s, e) => { btn.BackColor = Color.FromArgb(49, 50, 68); };

            return btn;
        }
    }
}

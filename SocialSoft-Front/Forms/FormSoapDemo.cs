using System;
using System.Drawing;
using System.Windows.Forms;
using SocialSoft.Client.Services; 

namespace SocialSoft.Client.Forms
{
    public class FormSoapDemo : Form
    {
        private Button btnTestSoap;
        private TextBox txtResultado;
        private SoapService _soapService;

        public FormSoapDemo()
        {
            _soapService = new SoapService();

            this.Text = "Prueba de SOAP Manual";
            this.Size = new Size(600, 400);
            this.StartPosition = FormStartPosition.CenterScreen;
            this.BackColor = Color.FromArgb(30, 30, 46);

            btnTestSoap = new Button
            {
                Text = "⚡ Enviar Petición SOAP",
                Location = new Point(200, 20),
                Size = new Size(200, 40),
                BackColor = Color.FromArgb(137, 180, 250),
                ForeColor = Color.Black,
                Font = new Font("Segoe UI", 10, FontStyle.Bold)
            };

            txtResultado = new TextBox
            {
                Location = new Point(20, 80),
                Size = new Size(540, 260),
                Multiline = true,
                ScrollBars = ScrollBars.Vertical,
                BackColor = Color.FromArgb(49, 50, 68),
                ForeColor = Color.FromArgb(205, 214, 244),
                Font = new Font("Consolas", 9),
                ReadOnly = true
            };

            // Al hacer clic, consumimos el servicio SOAP
            btnTestSoap.Click += async (s, e) =>
            {
                txtResultado.Text = "⏳ Enviando sobre XML (Envelope) a GlassFish...";
                try
                {
                    // Llamamos al método que armamos manualmente
                    string xml = await _soapService.ListarUsuariosAsync();
                    
                    // Mostramos el XML puro que devuelve el Web Service SOAP
                    txtResultado.Text = "✅ ¡Éxito! Respuesta SOAP:\r\n\r\n" + xml;
                }
                catch (Exception ex)
                {
                    txtResultado.Text = $"❌ Error SOAP: {ex.Message}";
                }
            };

            this.Controls.Add(btnTestSoap);
            this.Controls.Add(txtResultado);
        }
    }
}

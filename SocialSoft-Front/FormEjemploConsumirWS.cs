using System;
using System.Windows.Forms;
// Suponiendo que agregaste el "Connected Service" (WCF) y se generó el namespace del WS:
// using SocialSoft.Client.SocialSoftWS;

namespace SocialSoft.Client
{
    public partial class FormEjemploConsumirWS : Form
    {
        // Controles simulados (En el examen ya estarán en el Designer)
        private ComboBox comboBoxUsuarios = new ComboBox();
        private ComboBox comboBoxCanales = new ComboBox();
        private ComboBox comboBoxPlanes = new ComboBox();
        private Button btnConsumirBotonWs = new Button() { Text = "Suscribirse" };

        public FormEjemploConsumirWS()
        {
            InitializeComponent();
            
            // Simulamos el diseño solo para que veas que el botón llama al evento
            btnConsumirBotonWs.Click += BtnConsumirBotonWs_Click;
            this.Controls.Add(btnConsumirBotonWs);
        }

        // =========================================================================
        // EJEMPLO 1: COMO SE VE EL CÓDIGO DEL BOTÓN QUE LLAMA AL WEB SERVICE
        // =========================================================================
        private async void BtnConsumirBotonWs_Click(object sender, EventArgs e)
        {
            try
            {
                // 1. Instanciar el cliente SOAP autogenerado por "Connected Services"
                // En el examen, si tu WS se llama UsuarioCanalSuscripcionWS, C# generará la clase con el sufijo "Client"
                // UsuarioCanalSuscripcionWSClient clienteWS = new UsuarioCanalSuscripcionWSClient();

                // 2. Armar el objeto DTO que enviaremos.
                // NOTA: Los objetos como "usuarioCanalSuscripcion" o "registroSuscripcionDTO" 
                // ya vienen autogenerados dentro del namespace del clienteWS.
                
                /* DESCOMENTAR EN EL EXAMEN CUANDO TENGAS LA REFERENCIA:
                
                var suscripcion = new usuarioCanalSuscripcion()
                {
                    // Solo pasamos el ID, es lo normal en BD relacional para hacer la FK
                    usuario = new usuario { id = (int)comboBoxUsuarios.SelectedValue },
                    canal = new canal { id = (int)comboBoxCanales.SelectedValue },
                    plan = new planSuscripcion { id = (int)comboBoxPlanes.SelectedValue },
                    estado = "ACTIVO",
                    
                    // Asegúrate de que las fechas se manden con "Specified = true" si C# lo requiere (TÍPICO ERROR EN EXAMEN)
                    fechaRegistro = DateTime.Now,
                    fechaRegistroSpecified = true
                };

                var pago = new pagoSuscripcion()
                {
                    metodoPago = new metodoPago { id = 1 }, // Ej: Tarjeta
                    monto = 50.00m, // Este valor debería venir de txtMonto.Text o del plan elegido
                    fechaPago = DateTime.Now,
                    fechaPagoSpecified = true,
                    estado = "PAGADO"
                };

                var registroDTO = new registroSuscripcionDTO()
                {
                    suscripcion = suscripcion,
                    pago = pago
                };

                // 3. Llamada Asíncrona (¡MUY IMPORTANTE EL AWAIT!)
                int resultado = await clienteWS.registrarSuscripcionConPagoAsync(registroDTO);

                if (resultado > 0)
                {
                    MessageBox.Show("¡Éxito! Suscripción y pago registrados en la Base de Datos.");
                }
                else
                {
                    MessageBox.Show("Ocurrió un error en el registro (Rollback en BD).");
                }
                */
            }
            catch (Exception ex)
            {
                // Siempre capturar la excepción por si se cae GlassFish o MySQL
                MessageBox.Show("Error de comunicación: " + ex.Message);
            }
        }
    }
}

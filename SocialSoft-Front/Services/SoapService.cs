using System;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace SocialSoft.Client.Services
{
    /// <summary>
    /// Servicio de demostración para consumir el API SOAP (WS) de GlassFish
    /// usando HttpClient y enviando el XML manualmente.
    /// </summary>
    public class SoapService
    {
        private readonly HttpClient _httpClient;

        // La URL de tu servicio SOAP (revisado desde el WSDL)
        private const string SOAP_URL = "http://localhost:8080/SocialSoft-WS-1.0-SNAPSHOT/UsuarioWS";

        public SoapService()
        {
            _httpClient = new HttpClient();
        }

        /// <summary>
        /// Llama a la operación SOAP "listarUsuarios"
        /// </summary>
        public async Task<string> ListarUsuariosAsync()
        {
            // PASO 1: Armamos el "Sobre" (Envelope) SOAP en formato XML.
            // Esto es exactamente lo que envía un cliente como SOAP UI o Postman.
            // Notar que llamamos a la operación <listarUsuarios> dentro del namespace de tu proyecto.
            string soapEnvelope = @"
                <soapenv:Envelope xmlns:soapenv=""http://schemas.xmlsoap.org/soap/envelope/"" xmlns:ws=""http://ws.practica.edu.pe/"">
                   <soapenv:Header/>
                   <soapenv:Body>
                      <ws:listarUsuarios/>
                   </soapenv:Body>
                </soapenv:Envelope>";

            // PASO 2: Configuramos el contenido HTTP como "text/xml" (Requisito del protocolo SOAP)
            var content = new StringContent(soapEnvelope, Encoding.UTF8, "text/xml");

            // PASO 3: Hacemos un POST a la URL del Web Service
            HttpResponseMessage response = await _httpClient.PostAsync(SOAP_URL, content);

            // PASO 4: Leemos la respuesta en formato XML
            string xmlResponse = await response.Content.ReadAsStringAsync();

            if (!response.IsSuccessStatusCode)
            {
                throw new Exception($"Error SOAP HTTP {response.StatusCode}: {xmlResponse}");
            }

            // Retornamos todo el XML en crudo. Opcionalmente se puede parsear con XDocument.Parse()
            return xmlResponse;
        }
    }
}

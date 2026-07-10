using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace SocialSoft.Client.Services
{
    /// <summary>
    /// Servicio genérico para consumir APIs REST.
    /// 
    /// ¿CÓMO FUNCIONA?
    /// ================
    /// 1. Usamos HttpClient para hacer peticiones HTTP (GET, POST, PUT, DELETE).
    /// 2. Enviamos y recibimos datos en formato JSON.
    /// 3. Usamos Newtonsoft.Json para convertir objetos C# ↔ JSON.
    /// 
    /// EJEMPLO DE USO:
    /// ===============
    ///   var servicio = new RestService("http://localhost:8080/SocialSoft-REST-1.0-SNAPSHOT");
    ///   
    ///   // GET - Listar todos los usuarios
    ///   List<Usuario> usuarios = await servicio.GetListAsync<Usuario>("/usuarios");
    ///   
    ///   // POST - Insertar un nuevo usuario
    ///   int resultado = await servicio.PostAsync<Usuario, int>("/usuarios", nuevoUsuario);
    /// </summary>
    public class RestService
    {
        // HttpClient es el objeto principal para hacer peticiones HTTP en C#
        private readonly HttpClient _httpClient;

        // URL base de tu API REST (ej: "http://localhost:8080/SocialSoft-REST")
        private readonly string _baseUrl;

        /// <summary>
        /// Constructor: recibe la URL base de tu API REST.
        /// </summary>
        /// <param name="baseUrl">Ejemplo: "http://localhost:8080/SocialSoft-REST"</param>
        public RestService(string baseUrl)
        {
            _baseUrl = baseUrl.TrimEnd('/');
            _httpClient = new HttpClient();
            // Timeout de 30 segundos para que no se quede esperando infinitamente
            _httpClient.Timeout = TimeSpan.FromSeconds(30);
        }

        // ============================================================
        //  GET - Obtener una LISTA de objetos
        // ============================================================
        /// <summary>
        /// Hace un GET y devuelve una lista de objetos.
        /// Ejemplo: GetListAsync<Usuario>("/usuarios") → List<Usuario>
        /// </summary>
        public async Task<List<T>> GetListAsync<T>(string endpoint)
        {
            // 1. Construimos la URL completa
            string url = _baseUrl + endpoint;

            // 2. Hacemos la petición GET
            HttpResponseMessage response = await _httpClient.GetAsync(url);

            // 3. Leemos el cuerpo de la respuesta como string (será un JSON)
            string json = await response.Content.ReadAsStringAsync();

            // 4. Si la respuesta no fue exitosa, lanzamos error
            if (!response.IsSuccessStatusCode)
            {
                throw new Exception($"Error en GET {url}: {response.StatusCode} - {json}");
            }

            // 5. Convertimos el JSON a una lista de objetos C#
            List<T> lista = JsonConvert.DeserializeObject<List<T>>(json);
            return lista ?? new List<T>();
        }

        // ============================================================
        //  GET - Obtener UN SOLO objeto
        // ============================================================
        /// <summary>
        /// Hace un GET y devuelve un solo objeto.
        /// Ejemplo: GetAsync<Usuario>("/usuarios/1") → Usuario
        /// </summary>
        public async Task<T> GetAsync<T>(string endpoint)
        {
            string url = _baseUrl + endpoint;
            HttpResponseMessage response = await _httpClient.GetAsync(url);
            string json = await response.Content.ReadAsStringAsync();

            if (!response.IsSuccessStatusCode)
            {
                throw new Exception($"Error en GET {url}: {response.StatusCode} - {json}");
            }

            return JsonConvert.DeserializeObject<T>(json);
        }

        // ============================================================
        //  POST - Enviar un objeto (crear/insertar)
        // ============================================================
        /// <summary>
        /// Hace un POST enviando un objeto en el body como JSON.
        /// Ejemplo: PostAsync<Usuario, int>("/usuarios", usuario) → int (resultado)
        /// </summary>
        /// <typeparam name="TRequest">Tipo del objeto que envías</typeparam>
        /// <typeparam name="TResponse">Tipo del objeto que recibes</typeparam>
        public async Task<TResponse> PostAsync<TRequest, TResponse>(string endpoint, TRequest data)
        {
            string url = _baseUrl + endpoint;

            // 1. Convertimos el objeto C# a JSON
            string jsonEnvio = JsonConvert.SerializeObject(data);

            // 2. Creamos el contenido HTTP con el JSON
            //    - StringContent envuelve el JSON para poder enviarlo
            //    - "application/json" le dice al servidor que estamos enviando JSON
            var content = new StringContent(jsonEnvio, Encoding.UTF8, "application/json");

            // 3. Hacemos la petición POST
            HttpResponseMessage response = await _httpClient.PostAsync(url, content);

            // 4. Leemos la respuesta
            string jsonRespuesta = await response.Content.ReadAsStringAsync();

            if (!response.IsSuccessStatusCode)
            {
                throw new Exception($"Error en POST {url}: {response.StatusCode} - {jsonRespuesta}");
            }

            // 5. Convertimos la respuesta JSON al tipo esperado
            return JsonConvert.DeserializeObject<TResponse>(jsonRespuesta);
        }

        // ============================================================
        //  PUT - Actualizar un objeto existente
        // ============================================================
        /// <summary>
        /// Hace un PUT enviando un objeto en el body como JSON.
        /// Ejemplo: PutAsync<Usuario, int>("/usuarios", usuarioModificado) → int
        /// </summary>
        public async Task<TResponse> PutAsync<TRequest, TResponse>(string endpoint, TRequest data)
        {
            string url = _baseUrl + endpoint;

            string jsonEnvio = JsonConvert.SerializeObject(data);
            var content = new StringContent(jsonEnvio, Encoding.UTF8, "application/json");

            // PUT es igual que POST pero usa el método HTTP PUT
            HttpResponseMessage response = await _httpClient.PutAsync(url, content);

            string jsonRespuesta = await response.Content.ReadAsStringAsync();

            if (!response.IsSuccessStatusCode)
            {
                throw new Exception($"Error en PUT {url}: {response.StatusCode} - {jsonRespuesta}");
            }

            return JsonConvert.DeserializeObject<TResponse>(jsonRespuesta);
        }

        // ============================================================
        //  DELETE - Eliminar un registro
        // ============================================================
        /// <summary>
        /// Hace un DELETE para eliminar un registro.
        /// Ejemplo: DeleteAsync<int>("/usuarios/5") → int (resultado)
        /// </summary>
        public async Task<TResponse> DeleteAsync<TResponse>(string endpoint)
        {
            string url = _baseUrl + endpoint;

            HttpResponseMessage response = await _httpClient.DeleteAsync(url);
            string jsonRespuesta = await response.Content.ReadAsStringAsync();

            if (!response.IsSuccessStatusCode)
            {
                throw new Exception($"Error en DELETE {url}: {response.StatusCode} - {jsonRespuesta}");
            }

            return JsonConvert.DeserializeObject<TResponse>(jsonRespuesta);
        }
    }
}

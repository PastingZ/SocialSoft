using Newtonsoft.Json;
using System;

namespace SocialSoft.Client.Models
{
    /// <summary>
    /// Modelo que representa un Canal del sistema SocialSoft.
    /// Mapea directamente al modelo Java: pe.edu.practica.model.Canal
    /// </summary>
    public class Canal
    {
        [JsonProperty("id")]
        public int Id { get; set; }

        [JsonProperty("nombre")]
        public string Nombre { get; set; }

        [JsonProperty("descripcion")]
        public string Descripcion { get; set; }

        [JsonProperty("fechaCreacion")]
        public DateTime? FechaCreacion { get; set; }

        [JsonProperty("numeroSeguidores")]
        public int NumeroSeguidores { get; set; }

        [JsonProperty("categoria")]
        public string Categoria { get; set; }
    }
}

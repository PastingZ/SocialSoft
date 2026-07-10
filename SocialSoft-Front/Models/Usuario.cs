using Newtonsoft.Json;
using System;

namespace SocialSoft.Client.Models
{
    /// <summary>
    /// Modelo que representa un Usuario del sistema SocialSoft.
    /// Mapea directamente al modelo Java: pe.edu.practica.model.Usuario
    /// </summary>
    public class Usuario
    {
        [JsonProperty("id")]
        public int Id { get; set; }

        [JsonProperty("nombreCompleto")]
        public string NombreCompleto { get; set; }

        [JsonProperty("dni")]
        public string Dni { get; set; }

        [JsonProperty("edad")]
        public int Edad { get; set; }

        [JsonProperty("ciudad")]
        public string Ciudad { get; set; }

        [JsonProperty("fechaNacimiento")]
        public DateTime? FechaNacimiento { get; set; }

        [JsonProperty("telefono")]
        public string Telefono { get; set; }

        [JsonProperty("correo")]
        public string Correo { get; set; }

        [JsonProperty("profesion")]
        public string Profesion { get; set; }
    }
}

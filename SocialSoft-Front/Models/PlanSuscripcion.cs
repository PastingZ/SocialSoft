using Newtonsoft.Json;

namespace SocialSoft.Client.Models
{
    /// <summary>
    /// Modelo que representa un Plan de Suscripción del sistema SocialSoft.
    /// Mapea directamente al modelo Java: pe.edu.practica.model.PlanSuscripcion
    /// </summary>
    public class PlanSuscripcion
    {
        [JsonProperty("id")]
        public int Id { get; set; }

        [JsonProperty("nombre")]
        public string Nombre { get; set; }

        [JsonProperty("costoMensual")]
        public double CostoMensual { get; set; }
    }
}

using SocialSoft.Client.Forms;

namespace SocialSoft.Client;

static class Program
{
    /// <summary>
    ///  Punto de entrada de la aplicación.
    ///  Aquí arrancamos nuestro formulario de menú principal.
    /// </summary>
    [STAThread]
    static void Main()
    {
        ApplicationConfiguration.Initialize();
        // Lanzamos el formulario de menú principal
        Application.Run(new FormMenu());
    }
}
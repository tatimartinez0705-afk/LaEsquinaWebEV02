
package com.laesquina.web;

// Importar las clases necesarias para trabajar con solicitudes HTTP.
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet de ejemplo para practicar las solicitudes GET y POST.
 * GET muestra un formulario y POST procesa los datos recibidos.
 */
@WebServlet("/platillos")
public class PlatilloServlet extends HttpServlet {

    // Identificador para mantener compatibilidad de serialización.
    private static final long serialVersionUID = 1L;

    /**
     * Atiende solicitudes GET y presenta el formulario HTML.
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Definir la codificación de la respuesta para mostrar tildes.
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        // Preparar el escritor que enviará el HTML al navegador.
        PrintWriter salida = response.getWriter();

        // Construir la página HTML con un formulario para registrar platillos.
        salida.println("<!DOCTYPE html>");
        salida.println("<html lang='es'>");
        salida.println("<head>");
        salida.println("<meta charset='UTF-8'>");
        salida.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        salida.println("<title>Registro de platillos</title>");
        salida.println("</head>");
        salida.println("<body>");
        salida.println("<h1>La Esquina del Sabor</h1>");
        salida.println("<h2>Registro de platillos</h2>");

        // El formulario envía sus datos al mismo Servlet mediante POST.
        salida.println("<form method='post' action='platillos'>");
        salida.println("<label for='nombre'>Nombre:</label>");
        salida.println("<input type='text' id='nombre' name='nombre' required maxlength='100'>");
        salida.println("<br><br>");

        salida.println("<label for='descripcion'>Descripción:</label>");
        salida.println("<input type='text' id='descripcion' name='descripcion' maxlength='255'>");
        salida.println("<br><br>");

        salida.println("<label for='precio'>Precio:</label>");
        salida.println("<input type='number' id='precio' name='precio' min='1' step='0.01' required>");
        salida.println("<br><br>");

        salida.println("<label for='categoria'>Categoría:</label>");
        salida.println("<select id='categoria' name='categoria' required>");
        salida.println("<option value=''>Seleccione una categoría</option>");
        salida.println("<option value='Entradas'>Entradas</option>");
        salida.println("<option value='Platos principales'>Platos principales</option>");
        salida.println("<option value='Bebidas'>Bebidas</option>");
        salida.println("<option value='Postres'>Postres</option>");
        salida.println("</select>");
        salida.println("<br><br>");

        salida.println("<button type='submit'>Registrar platillo</button>");
        salida.println("</form>");
        salida.println("</body>");
        salida.println("</html>");
    }

    /**
     * Atiende solicitudes POST y valida los datos enviados por el formulario.
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Leer correctamente los caracteres enviados desde el formulario.
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());

        // Obtener y limpiar los campos de texto.
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String categoria = request.getParameter("categoria");
        String precioTexto = request.getParameter("precio");

        // Validar que los campos obligatorios tengan contenido.
        if (nombre == null || nombre.isBlank()
                || categoria == null || categoria.isBlank()
                || precioTexto == null || precioTexto.isBlank()) {

            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "El nombre, el precio y la categoría son obligatorios.");
            return;
        }

        // Convertir y validar el precio recibido.
        double precio;
        try {
            precio = Double.parseDouble(precioTexto);

            if (!Double.isFinite(precio) || precio <= 0) {
                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "El precio debe ser un número mayor que cero.");
                return;
            }
        } catch (NumberFormatException excepcion) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "El precio ingresado no tiene un formato válido.");
            return;
        }

        // Mostrar una confirmación de los datos recibidos.
        // En esta primera versión no se guardan en una base de datos.
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        PrintWriter salida = response.getWriter();

        salida.println("<!DOCTYPE html>");
        salida.println("<html lang='es'>");
        salida.println("<head><meta charset='UTF-8'><title>Resultado</title></head>");
        salida.println("<body>");
        salida.println("<h1>Resultado del registro</h1>");
        salida.println("<p>El formulario se procesó correctamente.</p>");

        // Escapar los valores antes de mostrarlos para evitar interpretar HTML ingresado.
        salida.println("<p><strong>Nombre:</strong> "
                + escaparHTML(nombre.trim()) + "</p>");
        salida.println("<p><strong>Descripción:</strong> "
                + escaparHTML(descripcion == null ? "" : descripcion.trim()) + "</p>");
        salida.println("<p><strong>Precio:</strong> " + precio + "</p>");
        salida.println("<p><strong>Categoría:</strong> "
                + escaparHTML(categoria.trim()) + "</p>");

        salida.println("<p><a href='platillos'>Volver al formulario</a></p>");
        salida.println("</body>");
        salida.println("</html>");
    }

    /**
     * Escapa caracteres especiales para mostrar texto de forma segura en HTML.
     */
    private String escaparHTML(String texto) {
        return texto
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}

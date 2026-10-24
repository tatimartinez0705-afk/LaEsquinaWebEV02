
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <!-- Configurar los caracteres y la visualización de la página. -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>La Esquina del Sabor - EV02</title>

    <style>
        /* Estilos básicos para presentar la página de inicio. */
        body {
            font-family: Arial, sans-serif;
            background-color: #fff8ef;
            color: #38251c;
            margin: 0;
            padding: 40px 20px;
            text-align: center;
        }

        main {
            max-width: 650px;
            margin: 40px auto;
            padding: 30px;
            background-color: white;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #a64b20;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            padding: 12px 20px;
            background-color: #a64b20;
            color: white;
            text-decoration: none;
            border-radius: 6px;
        }

        a:hover {
            background-color: #7d3518;
        }
    </style>
</head>
<body>
    <main>
        <!-- Presentación del sistema de ejemplo. -->
        <h1>La Esquina del Sabor</h1>
        <h2>Sistema de gestión de platillos</h2>

        <p>
            Bienvenido al módulo web desarrollado para la evidencia
            GA7-220501096-AA2-EV02.
        </p>

        <p>
            Esta aplicación permite practicar formularios HTML,
            Servlets, solicitudes GET y POST, y páginas JSP.
        </p>

        <!-- Enviar al usuario al Servlet de registro de platillos. -->
        <a href="${pageContext.request.contextPath}/platillos">
            Abrir formulario de platillos
        </a>
    </main>
</body>
</html>

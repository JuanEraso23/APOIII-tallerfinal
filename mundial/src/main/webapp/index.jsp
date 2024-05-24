<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="lib/header.jsp" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mundial de Futbol</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-image: url(fondo.jpg);
            background-size: cover;
            background-repeat: no-repeat;
            margin: 0;
            padding: 0;
            font-family: 'Arial', sans-serif;
            height: 100vh; /* Asegura que el body ocupa el 100% de la altura de la ventana */
            display: flex;
            flex-direction: column;
        }

        .navbar-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 0px;
        }

        .logo-text {
            font-size: 45px;
            font-weight: bold;
            color: white;
        }

        .navbar ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            display: flex;
        }

        .navbar li {
            margin-right: 20px;
        }

        .navbar li a {
            color: white;
            text-decoration: none;
            font-size: 18px;
            transition: color 0.3s;
            position: relative;
        }

        .navbar li a:hover {
            color: #55B5FF;
        }

        .navbar li a::before {
            content: "";
            position: absolute;
            width: 0;
            height: 2px;
            bottom: -2px;
            left: 0;
            background-color: #55B5FF;
            visibility: hidden;
            transition: all 0.3s ease-in-out;
        }

        .navbar li a:hover::before {
            width: 100%;
            visibility: visible;
        }

        .main-content {
            display: flex;
            justify-content: center; /* Centra horizontalmente */
            align-items: center;     /* Centra verticalmente */
            flex-grow: 1;            /* Toma todo el espacio disponible */
        }

        .card {
            background-color: #007BFF; /* Color azul */
            color: white;              /* Color del texto en blanco */
            width: 100%;               /* Ajusta el ancho de la tarjeta si es necesario */
        }

        .card-header {
            background-color: #007BFF; /* Color azul */
            border-bottom: none;       /* Eliminar el borde inferior */
        }

        .card-body {
            font-size: 24px;           /* Aumenta el tamaño de la letra */
            font-weight: bold;         /* Pone el texto en negrita */
        }
    </style>
</head>
<body>
    <!-- Navbar con Bootstrap -->
    <nav>
        <div class="navbar-container">
            <div class="logo-text">
                Mundial de Fútbol
            </div>
            <ul class="navbar">
                <li><a href="index.jsp">Inicio</a></li>
                <li><a href="inicio.jsp">Equipo</a></li>
            </ul>
        </div>
    </nav>

    <div class="main-content">
        <div class="col-md-8">
            <div class="card" style="text-align: center;">
                <div class="card-header d-flex justify-content-center align-items-center">
                    <h5>Bienvenido a nuestro aplicativo</h5>
                </div>
                <div class="card-body">
                    Mundial de Fútbol
                </div>
            </div>
        </div>
    </div>
</body>
</html>

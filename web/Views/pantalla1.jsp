<%-- 
    Document   : pantalla1
    Created on : 16 ago. 2022, 15:33:05
    Author     : Ana Gatjens Campos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pantalla 1</title>
    </head>
    <body>
        <nav>
            <div>
                <a href="#" data-toggle="dropdown">Cerrar Sesion</a>
                <div>
                <a>${usu}</a>
                <a href="UsuarioAdministradorServlet?accion=Salir">Salir</a>
                <a></a>
                
                </div>
            </div>    
        </nav>
        <div><h2>Bienvenido al sistema de administrador usuario ${usu}</h2></div>
         <div>
            <a href="UsuarioAdministradorServlet?accion=listUsuarioAdministrador">Lista de usuario administrador</a>            
        </div>
        <div>
            <a href="ProductoServlet?accion=listProducto">Lista de productos</a>            
        </div>
    </body>
</html>

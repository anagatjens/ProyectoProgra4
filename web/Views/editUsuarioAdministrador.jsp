<%-- 
    Document   : editUsuarioAdministrador
    Created on : 3 ago. 2022, 17:28:31
    Author     : Ana Gatjens Campos
--%>
<%@page import="Model.UsuarioAdministrador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="views/estilos.css" rel="stylesheet" type="text/css"/> 
        <title>Editar Usuario Administrador</title>
    </head>
    <body>
         <% UsuarioAdministrador _usuarioAdministrador = (UsuarioAdministrador) request.getAttribute("_UsuarioAdministrador"); %>
        <h1>MODIFICACIÓN DE USUARIOS ADMINISTRADORES</h1>
         <form action="UsuarioAdministradorServlet">             
            <table>               
                <tr>
                    <td>Nombre Completo:</td>
                    <td>
                        <input type="hidden" name="id" value="<%=_usuarioAdministrador.getId()%>"/>                        
                        <input type="text" name="nombre" value="<%=_usuarioAdministrador.getNombre()%>"/>
                 </td>                    
                </tr>
                <tr>
                    <td>Número de teléfono:</td>
                    <td>
                        <input type="text" name="residencia" value="<%=_usuarioAdministrador.getTelefono()%>"/>
                    </td>                    
                </tr>
                <tr>
                    <td>Dirección:</td>
                    <td><input type="text" name="telefono" value="<%=_usuarioAdministrador.getDireccion()%>"/>
                        
                    </td>                    
                </tr>
                <tr>
                    <td>Correo Electrónico:</td>
                    <td><input type="text" name="cedula" value="<%=_usuarioAdministrador.getCorreo()%>"/></td>                    
                </tr>
                <tr>
                    <td>Cédula:</td>
                    <td><input type="text" name="edad" value="<%=_usuarioAdministrador.getCedula()%>"/></td>                    
                </tr>
                <tr>
                    <td>Usuario:</td>
                    <td><input type="text" name="lugarTrabajo" value="<%=_usuarioAdministrador.getUsuario()%>"/></td>                    
                </tr>
                <tr>
                    <td>Contraseña:</td>
                    <td><input type="text" name="lugarTrabajo" value="<%=_usuarioAdministrador.getContrasena()%>"/></td>                    
                </tr>
                <tr>
                    <td colspan="2">
                         <input type="hidden" name="accion" value="updateUsuarioAdministrador"/>
                         <button type="submit">Actualizar Datos</button>
                </tr>
            </table>
    </body>
</html>

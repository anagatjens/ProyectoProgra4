<%-- 
    Document   : listUsuarioAdministrador
    Created on : 16 ago. 2022, 15:00:48
    Author     : Ana Gatjens Campos
--%>

<%@page import="java.util.List"%>
<%@page import="Model.UsuarioAdministrador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar Usuarios Administradores</title>
    </head>
    <body>
        <% List<UsuarioAdministrador> _listUsuarioAdministrador = (List<UsuarioAdministrador>) request.getAttribute("listaUsuarioAdministrador"); %>
        <h1>Lista de Usuarios Administradores</h1>
        <div> <a href="UsuarioAdministradorServlet?accion=addUsuarioAdministradorPage">Nuevo usuario administrador</a></div>
        <table id="users">
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Telefono</th>
                <th>Direccion</th>
                <th>Correo</th>
                 <th>Cedula</th>
                 <th>Usuario</th>
                 <th>Contraseña</th>
                 <th></th>
                 <th></th> <th></th>
            </tr>
            <%            
            for(UsuarioAdministrador _usuarioAdministrador :_listUsuarioAdministrador){                 
            %>
            <tr>
                <td><%=_usuarioAdministrador.getId()%></td>
                <td><%=_usuarioAdministrador.getNombre()%></td>
                <td><%=_usuarioAdministrador.getTelefono()%></td>
                <td><%=_usuarioAdministrador.getDireccion()%></td>
                <td><%=_usuarioAdministrador.getCorreo()%></td>
                <td><%=_usuarioAdministrador.getCedula()%></td>
                <td><%=_usuarioAdministrador.getUsuario()%></td>
                <td><%=_usuarioAdministrador.getContrasena()%></td>
                <td>                   
                </td>
                <td>
                    <a href="UsuarioAdministradorServlet?accion=editUsuarioAdministradorPage&id=<%=_usuarioAdministrador.getId()%>">Editar Usuario Administrador</a>
                </td>
                <td>
                   <a href="UsuarioAdministradorServlet?accion=deleteUsuarioAdministrador&id=<%=_usuarioAdministrador.getId()%>">Borrar</a>
                </td>
            </tr>
               <%}%> 
        </table>
    </body>
</html>

<%-- 
    Document   : addUsuarioAdministrador
    Created on : 16 ago. 2022, 15:09:47
    Author     : Ana Gatjens Campos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Usuario Administrador</title>
    </head>
    <body>
        <h1>Ingresar nueva habitacion</h1>
         <form action="UsuarioAdministradorServlet" method="POST">
             
            <table>               
                <tr>
                    <td>Nombre de Usuario:</td>
                    <td>
                        <input type="text" name="nombre"/>
                 </td>                    
                </tr>
                <tr>
                    <td>Numero de Telefono:</td>
                    <td>
                        <input type="text" name="telefono"/>
                    </td>                    
                </tr>
                <tr>
                    <td>Direccion:</td>
                    <td><input type="text" name="direccion"/>
                        
                    </td>                    
                </tr>
                <tr>
                    <td>Correo Electronico:</td>
                    <td><input type="text" name="correo"/></td>                    
                </tr>
                <tr>
                    <td>Numero de Cedula:</td>
                    <td><input type="text" name="cedula"/></td>                    
                </tr>
                <tr>
                    <td>Usuario:</td>
                    <td><input type="text" name="usuario"/></td>                    
                </tr>
                <tr>
                    <td>Contraseña:</td>
                    <td><input type="text" name="contrasena"/></td>                    
                </tr>
                <tr>
                    <td colspan="2">                      
                          <input type="hidden" name="accion" value="ConsultaPersona"/>
                          <a href="UsuarioAdministradorServlet?accion=addUsuarioAdministrador">Guardar Usuario Administrador</a>
                         
                    
                </tr>
            </table>
    </body>
</html>

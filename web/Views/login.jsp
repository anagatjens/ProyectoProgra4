<%-- 
    Document   : login
    Created on : 16 ago. 2022, 15:23:22
    Author     : Ana Gatjens Campos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario Login</title>
    </head>
    <body>
        <div>
            <form action="pantalla1.jsp">
                <div>
                    <p><strong>Pagina Login Tienda Verduras</strong></p>
                </div>
                <div>
                    <label>Usuario</label>
                    <input type="text" name="txtUsu" placeholder="Ingrese el usuario">
                </div>
                <div>
                    <label>Contraseña</label>
                    <input type="password" name="txtCont" placeholder="Ingrese la contraseña">
                </div>
                <input type="submit" name="accion" value="Ingresar">
            </form>
        </div>

    </body>
</html>

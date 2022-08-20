/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;

import Model.UsuarioAdministrador;
import ModelDAO.UsuarioAdministradorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ana Gatjens Campos
 */
@WebServlet(name = "UsuarioAdministradorServlet", urlPatterns = {"/UsuarioAdministradorServlet"})
public class UsuarioAdministradorServlet extends HttpServlet {
    UsuarioAdministradorDAO _usuarioAdministradorDAO;
    int r;
    String listarUsuarioAdministradorPage="views/listUsuarioAdministrador.jsp";
    String addUsuarioAdministradorPage="views/addUsuarioAdministrador.jsp";
    String editUsuarioAdministradorPage="views/editUsuarioAdministrador.jsp";
    
    public UsuarioAdministradorServlet(){
        this._usuarioAdministradorDAO = new UsuarioAdministradorDAO();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsuarioAdministradorServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsuarioAdministradorServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        this.doGet(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action= request.getParameter("accion"); 
        
        if(action.equalsIgnoreCase("listUsuarioAdministrador")){        
            try {
                getListUsuarioAdministrador(request,response);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioAdministradorServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(action.equalsIgnoreCase("addUsuarioAdministrador")){           
            try {
                //acceso=addUsuarioAdministrador;
                setNewUsuarioAdministrador(request,response);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioAdministradorServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(action.equalsIgnoreCase("editUsuarioAdministrador")){           
            try {
                //acceso=editUsuarioAdministrador;
                showEditPage(request,response);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioAdministradorServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(action.equalsIgnoreCase("deleteUsuarioAdministrador")){           
            try {                
                deleteUsuarioAdministrador(request,response);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioAdministradorServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(action.equalsIgnoreCase("updateUsuarioAdministrador")){           
            try {                
                UpdateUsuarioAdministrador(request,response);                                
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioAdministradorServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(action.equalsIgnoreCase("addUsuarioAdministradorPage")){           
            RequestDispatcher vista= request.getRequestDispatcher(addUsuarioAdministradorPage);
            vista.forward(request,response);
        }else if(action.equalsIgnoreCase("editUsuarioAdministradorPage")){
            try {
                /*CARGAMOS DEL DAO EL USUARIOADMINISTRADOR SEGUN EL ID*/
                loadUsuarioAdministrador(request,response);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioAdministradorServlet.class.getName()).log(Level.SEVERE, null, ex);
            }          
            RequestDispatcher vista= request.getRequestDispatcher(editUsuarioAdministradorPage);
            vista.forward(request,response);
        }else if(action.equalsIgnoreCase("Ingresar")){           
            try {
                validarLogin(request,response);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioAdministradorServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void getListUsuarioAdministrador(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException {
        try { 
            List<UsuarioAdministrador> _listUsuarioAdministrador = _usuarioAdministradorDAO.listar();
            request.setAttribute("listaUsuarioAdministrador",_listUsuarioAdministrador);
            RequestDispatcher dispatcher = request.getRequestDispatcher(listarUsuarioAdministradorPage);
            dispatcher.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(UsuarioAdministradorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void setNewUsuarioAdministrador(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
       
        String nombre =(String) request.getParameter("nombre");
        String telefono =(String) request.getParameter("telefono");
        String direccion =(String) request.getParameter("direccion");
        String correo =(String) request.getParameter("correo");
        String cedula =(String) request.getParameter("cedula");
        String usuario =(String) request.getParameter("usuario");
        String contrasena =(String) request.getParameter("contrasena");
        UsuarioAdministrador _usuarioAdministrador = new UsuarioAdministrador(nombre, telefono, direccion, correo, cedula, usuario, contrasena);
        _usuarioAdministradorDAO.add(_usuarioAdministrador);
        getListUsuarioAdministrador(request, response);
    }
    
    private void UpdateUsuarioAdministrador(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
        String id =(String) request.getParameter("id");
        String nombre =(String) request.getParameter("nombre");
        String telefono =(String) request.getParameter("telefono");
        String direccion =(String) request.getParameter("direccion");
        String correo =(String) request.getParameter("correo");
        String cedula =(String) request.getParameter("cedula");
        String usuario =(String) request.getParameter("usuario");
        String contrasena =(String) request.getParameter("contrasena");
        
        UsuarioAdministrador _usuarioAdministrador = new UsuarioAdministrador(Integer.parseInt(id), nombre, telefono, direccion, 
                correo, cedula, usuario, contrasena);
        _usuarioAdministradorDAO.edit(_usuarioAdministrador);
        getListUsuarioAdministrador(request, response);
    }
    
    private void deleteUsuarioAdministrador(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException {
       int id =Integer.parseInt(request.getParameter("id"));
       _usuarioAdministradorDAO.delete(id);
      getListUsuarioAdministrador(request, response);
    }
    
    private void showEditPage(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            UsuarioAdministrador _usuarioAdministrador = (UsuarioAdministrador)_usuarioAdministradorDAO.getData(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher(editUsuarioAdministradorPage);
            request.setAttribute("UsuarioAdministrador",_usuarioAdministrador);
            dispatcher.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(UsuarioAdministradorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadUsuarioAdministrador(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException {
        int id =Integer.parseInt(request.getParameter("id"));
        UsuarioAdministrador _UsuarioAdministrador = (UsuarioAdministrador)_usuarioAdministradorDAO.getData(id);
        request.setAttribute("_UsuarioAdministrador",_UsuarioAdministrador);
    }
    
    private void validarLogin(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
        UsuarioAdministrador _usuarioAdministrador = new UsuarioAdministrador();
        String accion=request.getParameter("accion");
        if(accion.equals("Ingresar")){
            String usu=request.getParameter("txtUsu");
            String cont=request.getParameter("txtCont");
            _usuarioAdministrador.setUsuario(usu);
            _usuarioAdministrador.setContrasena(cont);
            r=_usuarioAdministradorDAO.validar(_usuarioAdministrador);
            if(r==1){
                request.getSession().setAttribute("usu", usu);
                request.getRequestDispatcher("Pantalla1.jsp").forward(request,response);
            }else{
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }else{
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }   
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;

import Model.Cliente;
import ModelDAO.ClienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "ClienteServlet", urlPatterns = {"/ClienteServlet"})
public class ClienteServlet extends HttpServlet {
    ClienteDAO _clienteDAO;
    String listarClientePage="views/listCliente.jsp";
    String addClientePage="views/addCliente.jsp";
    String editClientePage="views/editCliente.jsp";
    
    public ClienteServlet(){
        this._clienteDAO = new ClienteDAO();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ClienteServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClienteServlet at " + request.getContextPath() + "</h1>");
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
        
        if(action.equalsIgnoreCase("listCliente")){        
            try {
                getListClients(request,response);
            } catch (SQLException ex) {
                Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(action.equalsIgnoreCase("addCliente")){           
            try {
                //acceso=addCliente;
                setNewCliente(request,response);
            } catch (SQLException ex) {
                Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(action.equalsIgnoreCase("editCliente")){           
            try {
                //acceso=editCliente;
                showEditPage(request,response);
            } catch (SQLException ex) {
                Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(action.equalsIgnoreCase("deleteCliente")){           
            try {                
                deleteCliente(request,response);
            } catch (SQLException ex) {
                Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(action.equalsIgnoreCase("updateCliente")){           
            try {                
                UpdateCliente(request,response);                                
            } catch (SQLException ex) {
                Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(action.equalsIgnoreCase("addClientePage")){           
            RequestDispatcher vista= request.getRequestDispatcher(addClientePage);
            vista.forward(request,response);
        }else if(action.equalsIgnoreCase("editClientePage")){
            try {
                /*CARGAMOS DEL DAO EL CLIENTE SEGUN EL ID*/
                loadCliente(request,response);
            } catch (SQLException ex) {
                Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }          
            RequestDispatcher vista= request.getRequestDispatcher(editClientePage);
            vista.forward(request,response);
        }
    }
    
    private void getListClients(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException {
        try { 
            List<Cliente> _listCliente = _clienteDAO.listar();
            request.setAttribute("listaClientes",_listCliente);
            RequestDispatcher dispatcher = request.getRequestDispatcher(listarClientePage);
            dispatcher.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void setNewCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
       
        String nombre =(String) request.getParameter("nombre");
        String telefono =(String) request.getParameter("telefono");
        String direccion =(String) request.getParameter("direccion");
        String correo =(String) request.getParameter("correo");
        String cedula =(String) request.getParameter("cedula");
        String usuario =(String) request.getParameter("usuario");
        Cliente _cliente = new Cliente(nombre, telefono, direccion, correo, cedula, usuario);
        _clienteDAO.add(_cliente);
        getListClients(request, response);
    }
    
    private void UpdateCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
        String id =(String) request.getParameter("id");
        String nombre =(String) request.getParameter("nombre");
        String telefono =(String) request.getParameter("telefono");
        String direccion =(String) request.getParameter("direccion");
        String correo =(String) request.getParameter("correo");
        String cedula =(String) request.getParameter("cedula");
        String usuario =(String) request.getParameter("usuario");
        
        Cliente _cliente = new Cliente(Integer.parseInt(id), nombre, telefono, direccion, 
                correo, cedula, usuario);
        _clienteDAO.edit(_cliente);
        getListClients(request, response);
    }
    
    private void deleteCliente(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException {
       int id =Integer.parseInt(request.getParameter("id"));
       _clienteDAO.delete(id);
      getListClients(request, response);
    }
    
    private void showEditPage(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Cliente _cliente = (Cliente)_clienteDAO.getData(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher(editClientePage);
            request.setAttribute("Cliente",_cliente);
            dispatcher.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadCliente(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException {
        int id =Integer.parseInt(request.getParameter("id"));
        Cliente _Cliente = (Cliente)_clienteDAO.getData(id);
        request.setAttribute("_Cliente",_Cliente);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;

/**
 *
 * @author Orlando
 */


import Model.Producto;
import ModelDAO.ProductoDAO;
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





@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"})
public class ProductoServlet extends HttpServlet {
    ProductoDAO _productoDAO;
    String listarProductoPage="views/listProducto.jsp";
    String addProductoPage="views/addProducto.jsp";
    String editProductoPage="views/editProducto.jsp";
   
    
    public ProductoServlet(){
        this._productoDAO = new ProductoDAO();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductoServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductoServlet at " + request.getContextPath() + "</h1>");
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
        
        if(action.equalsIgnoreCase("listProducto")){        
            try {
                getListProducto(request,response);
            } catch (SQLException ex) {
                Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(action.equalsIgnoreCase("addProducto")){           
            try {
                //acceso=addProducto;
                setNewProducto(request,response);
            } catch (SQLException ex) {
                Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(action.equalsIgnoreCase("editProducto")){           
            try {
                //acceso=editProducto;
                showEditPage(request,response);
            } catch (SQLException ex) {
                Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(action.equalsIgnoreCase("deleteProducto")){           
            try {                
                deleteProducto(request,response);
            } catch (SQLException ex) {
                Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(action.equalsIgnoreCase("updateProducto")){           
            try {                
                UpdateProducto(request,response);                                
            } catch (SQLException ex) {
                Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(action.equalsIgnoreCase("addProductoPage")){           
            RequestDispatcher vista= request.getRequestDispatcher(addProductoPage);
            vista.forward(request,response);
        }else if(action.equalsIgnoreCase("editProductoPage")){
            try {
                /*CARGAMOS DEL DAO EL PRODUCTO SEGUN EL ID*/
                loadProducto(request,response);
            } catch (SQLException ex) {
                Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }          
            RequestDispatcher vista= request.getRequestDispatcher(editProductoPage);
            vista.forward(request,response);
        }
    }
    
    private void getListProducto(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException {
        try { 
            List<Producto> _listProducto = _productoDAO.listar();
            request.setAttribute("listaProducto",_listProducto);
            RequestDispatcher dispatcher = request.getRequestDispatcher(listarProductoPage);
            dispatcher.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void setNewProducto(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
       
        String nombre =(String) request.getParameter("nombre");
        String categoria =(String) request.getParameter("categoria");
        String precio =(String) request.getParameter("precio");
        String descripcion =(String) request.getParameter("descripcion");
        Producto _producto = new Producto(nombre, categoria, Double.parseDouble(precio), descripcion);
        _productoDAO.add(_producto);
        getListProducto(request, response);
    }
    
    private void UpdateProducto(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
        String id =(String) request.getParameter("id");
        String nombre =(String) request.getParameter("nombre");
        String categoria =(String) request.getParameter("categoria");
        String precio =(String) request.getParameter("precio");
        String descripcion =(String) request.getParameter("descripcion");
        
        Producto _producto = new Producto(Integer.parseInt(id), nombre, categoria, Double.parseDouble(precio), 
                descripcion);
        _productoDAO.edit(_producto);
        getListProducto(request, response);
    }
    
    private void deleteProducto(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException {
       int id =Integer.parseInt(request.getParameter("id"));
       _productoDAO.delete(id);
      getListProducto(request, response);
    }
    
    private void showEditPage(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Producto _producto = (Producto)_productoDAO.getData(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher(editProductoPage);
            request.setAttribute("Producto",_producto);
            dispatcher.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadProducto(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException {
        int id =Integer.parseInt(request.getParameter("id"));
        Producto _Producto = (Producto)_productoDAO.getData(id);
        request.setAttribute("_Producto",_Producto);
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelDAO;

import Config.Conexion;
import Interfaces.CRUD;
import Model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductoDAO implements CRUD {
    
    Conexion cn = new Conexion();
    Connection con ;
    PreparedStatement ps;
    ResultSet rs;
    Producto _producto = new Producto();
    
    @Override
    public List listar() {
       ArrayList<Producto>list = new ArrayList<>();
       String sql = "select * from Producto";
        try {
            con=cn.getConection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                Producto _producto = new Producto();
              _producto.setId(rs.getInt("id"));             
              _producto.setNombre(rs.getString("Nombre"));
              _producto.setCategoria(rs.getString("Categoria"));
              _producto.setPrecio(rs.getDouble("Precio"));
              _producto.setDescripcion(rs.getString("Descripcion"));
              list.add(_producto);
            }
        } catch (SQLException e) {
            System.err.print("Error List"+ e);
        }
        return list;
    }
    
    @Override
    public Object getData(int id) {        
        String sql = "select * from Producto where id = ?";
        try {
            con=cn.getConection();
            ps=con.prepareStatement(sql);
            ps.setInt (1, id);
            rs=ps.executeQuery();
            while (rs.next()) {
                 id = rs.getInt("id");         
                String nombre = rs.getString("Nombre");
                String Categoria=rs.getString("Categoria");
                double Precio=rs.getDouble("Precio");
                String Descripcion = rs.getString("Descripcion");
                _producto =  new Producto(id, nombre,Categoria, Precio, Descripcion);                
            }
            
        } catch (SQLException e) {
            System.err.print("Error List"+ e);
            return null;
        }
        return _producto;
    }
    
    @Override
    public boolean add(Object _productoObj) {
        Producto _producto = (Producto)_productoObj;
        String sql = "INSERT into producto (Nombre, Categoria, Precio,Descripcion)values(?,?,?,?,?,?)";
        try {
            con = cn.getConection();            
            ps= con.prepareStatement(sql);
            ps.setString(1, _producto.getNombre());
            ps.setString(2, _producto.getCategoria());
            ps.setDouble(3, _producto.getPrecio());
            ps.setString(4, _producto.getDescripcion());
            ps.executeUpdate(); 
           return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    @Override
    public boolean edit(Object _productoObj) {
        Producto _producto = (Producto)_productoObj;
        String sql = "Update producto "
                + " set nombre= ?, Categoria= ?, Precio= ?,Descripcion= ?"
                + " where id= ?";
        try {
            con = cn.getConection();
            ps= con.prepareStatement(sql);
            ps.setString(1, _producto.getNombre());
            ps.setString(2, _producto.getCategoria());
            ps.setDouble(3, _producto.getPrecio());
            ps.setString(4, _producto.getDescripcion());
            ps.setInt(7, _producto.getId());
            
            ps.executeUpdate(); 
           return true;
        } catch (SQLException e) {
            return false;
        }
        
    }
        
        @Override
    public boolean delete(int id) {
        String sql = "delete producto where id = ?";
        try {
            con = cn.getConection();
            
            ps= con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate(); 
           return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
}


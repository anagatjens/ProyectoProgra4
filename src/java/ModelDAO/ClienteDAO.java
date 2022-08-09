/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelDAO;

import Config.Conexion;
import Interfaces.CRUD;
import Model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ana Gatjens Campos
 */
public class ClienteDAO implements CRUD {
    
    Conexion cn = new Conexion();
    Connection con ;
    PreparedStatement ps;
    ResultSet rs;
    Cliente _Cliente = new Cliente();
    
    @Override
    public List listar() {
       ArrayList<Cliente>list = new ArrayList<>();
       String sql = "select * from cliente";
        try {
            con=cn.getConection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                Cliente _cliente = new Cliente();
              _cliente.setId(rs.getInt("id"));             
              _cliente.setNombre(rs.getString("Nombre"));
              _cliente.setTelefono(rs.getString("Telefono"));
              _cliente.setDireccion(rs.getString("Direccion"));
              _cliente.setCorreo(rs.getString("Correo"));
              _cliente.setCedula(rs.getString("Cedula"));
              _cliente.setUsuario(rs.getString("Usuario"));
              list.add(_cliente);
            }
        } catch (SQLException e) {
            System.err.print("Error List"+ e);
        }
        return list;
    }
    
    @Override
    public Object getData(int id) {        
        String sql = "select * from cliente where id = ?";
        try {
            con=cn.getConection();
            ps=con.prepareStatement(sql);
            ps.setInt (1, id);
            rs=ps.executeQuery();
            while (rs.next()) {
                 id = rs.getInt("id");         
                String nombre = rs.getString("Nombre");
                String telefono=rs.getString("Telefono");
                String direccion=rs.getString("Direccion");
                String correo = rs.getString("Correo");
                String cedula = rs.getString("Cedula");
                String usuario=rs.getString("Usuario");
                _Cliente =  new Cliente(id, nombre,telefono, direccion, correo, cedula, usuario);                
            }
            
        } catch (SQLException e) {
            System.err.print("Error List"+ e);
            return null;
        }
        return _Cliente;
    }
    
    @Override
    public boolean add(Object _clienteObj) {
        Cliente _cliente = (Cliente)_clienteObj;
        String sql = "INSERT into cliente (nombre, telefono, direccion,correo,cedula,usuario)values(?,?,?,?,?,?)";
        try {
            con = cn.getConection();            
            ps= con.prepareStatement(sql);
            ps.setString(1, _cliente.getNombre());
            ps.setString(2, _cliente.getTelefono());
            ps.setString(3, _cliente.getDireccion());
            ps.setString(4, _cliente.getCorreo());
            ps.setString(5, _cliente.getCedula());
            ps.setString(6, _cliente.getUsuario());
            ps.executeUpdate(); 
           return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    @Override
    public boolean edit(Object _clienteObj) {
        Cliente _cliente = (Cliente)_clienteObj;
        String sql = "Update cliente "
                + " set nombre= ?, telefono= ?, direccion= ?,correo= ?,cedula= ?,usuario= ?"
                + " where id= ?";
        try {
            con = cn.getConection();
            ps= con.prepareStatement(sql);
            ps.setString(1, _cliente.getNombre());
            ps.setString(2, _cliente.getTelefono());
            ps.setString(3, _cliente.getDireccion());
            ps.setString(4, _cliente.getCorreo());
            ps.setString(5, _cliente.getCedula());
            ps.setString(6, _cliente.getUsuario());
            ps.setInt(7, _cliente.getId());
            
            ps.executeUpdate(); 
           return true;
        } catch (SQLException e) {
            return false;
        }
        
    }
        
        @Override
    public boolean delete(int id) {
        String sql = "delete cliente where id = ?";
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

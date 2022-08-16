/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelDAO;

import Config.Conexion;
import Interfaces.CRUD;
import Model.UsuarioAdministrador;
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
public class UsuarioAdministradorDAO implements CRUD {
    
    Conexion cn = new Conexion();
    Connection con ;
    PreparedStatement ps;
    ResultSet rs;
    UsuarioAdministrador _UsuarioAdministrador = new UsuarioAdministrador();
    
    @Override
    public List listar() {
       ArrayList<UsuarioAdministrador>list = new ArrayList<>();
       String sql = "select * from usuarioAdministrador";
        try {
            con=cn.getConection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                UsuarioAdministrador _usuarioAdministrador = new UsuarioAdministrador();
              _usuarioAdministrador.setId(rs.getInt("id"));             
              _usuarioAdministrador.setNombre(rs.getString("Nombre"));
              _usuarioAdministrador.setTelefono(rs.getString("Telefono"));
              _usuarioAdministrador.setDireccion(rs.getString("Direccion"));
              _usuarioAdministrador.setCorreo(rs.getString("Correo"));
              _usuarioAdministrador.setCedula(rs.getString("Cedula"));
              _usuarioAdministrador.setUsuario(rs.getString("Usuario"));
              _usuarioAdministrador.setContrasena(rs.getString("Contrasena"));
              list.add(_usuarioAdministrador);
            }
        } catch (SQLException e) {
            System.err.print("Error List"+ e);
        }
        return list;
    }
    
    @Override
    public Object getData(int id) {        
        String sql = "select * from usuarioAdministrador where id = ?";
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
                String contrasena=rs.getString("Contrasena");
                _UsuarioAdministrador =  new UsuarioAdministrador(id, nombre,telefono, direccion, correo, cedula, usuario, contrasena);                
            }
            
        } catch (SQLException e) {
            System.err.print("Error List"+ e);
            return null;
        }
        return _UsuarioAdministrador;
    }
    
    @Override
    public boolean add(Object _usuarioAdministradorObj) {
        UsuarioAdministrador _usuarioAdministrador = (UsuarioAdministrador)_usuarioAdministradorObj;
        String sql = "INSERT into usuarioAdministrador (nombre, telefono, direccion,correo,cedula,usuario,contrasena)values(?,?,?,?,?,?)";
        try {
            con = cn.getConection();            
            ps= con.prepareStatement(sql);
            ps.setString(1, _usuarioAdministrador.getNombre());
            ps.setString(2, _usuarioAdministrador.getTelefono());
            ps.setString(3, _usuarioAdministrador.getDireccion());
            ps.setString(4, _usuarioAdministrador.getCorreo());
            ps.setString(5, _usuarioAdministrador.getCedula());
            ps.setString(6, _usuarioAdministrador.getUsuario());
            ps.setString(7, _usuarioAdministrador.getContrasena());
            ps.executeUpdate(); 
           return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    @Override
    public boolean edit(Object _usuarioAdministradorObj) {
        UsuarioAdministrador _usuarioAdministrador = (UsuarioAdministrador)_usuarioAdministradorObj;
        String sql = "Update usuarioAdministrador "
                + " set nombre= ?, telefono= ?, direccion= ?,correo= ?,cedula= ?,usuario= ?,contrasena= ?"
                + " where id= ?";
        try {
            con = cn.getConection();
            ps= con.prepareStatement(sql);
            ps.setString(1, _usuarioAdministrador.getNombre());
            ps.setString(2, _usuarioAdministrador.getTelefono());
            ps.setString(3, _usuarioAdministrador.getDireccion());
            ps.setString(4, _usuarioAdministrador.getCorreo());
            ps.setString(5, _usuarioAdministrador.getCedula());
            ps.setString(6, _usuarioAdministrador.getUsuario());
            ps.setString(6, _usuarioAdministrador.getContrasena());
            ps.setInt(7, _usuarioAdministrador.getId());
            
            ps.executeUpdate(); 
           return true;
        } catch (SQLException e) {
            return false;
        }
        
    }
        
        @Override
    public boolean delete(int id) {
        String sql = "delete usuarioAdministrador where id = ?";
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

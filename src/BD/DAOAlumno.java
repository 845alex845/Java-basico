/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Clases.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author infie
 */
public class DAOAlumno {

    private PreparedStatement ps;
    private ResultSet rs;
    private Connection conexion;
    private final AccesoBD acceso;
    public DAOAlumno() {

        acceso = new AccesoBD();
    }

    public boolean create(Alumno a) {
        boolean result = false;
        try {
            conexion = AccesoBD.getConnection();
            String sql = "INSERT INTO ALUMNO (DOCUMENTO,NOMBRE,APEPAT,APEMAT,EDAD)"
                    + "VALUES( ?,?,?,?,? )";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, a.getDocumento());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getApepat());
            ps.setString(4, a.getApemat());
            ps.setString(5, a.getEdad());
            ps.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            result = false;
        } finally {
            AccesoBD.closeConnection(conexion, ps);
        }
        return result;
    }

    public Alumno read(String codigo) {
        Alumno result = null;
        try {
            conexion = AccesoBD.getConnection();
            String sql = "SELECT * FROM ALUMNO WHERE DOCUMENTO=?";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            if (rs.next()) {
                String documento = rs.getString("DOCUMENTO");
                String nombre = rs.getString("NOMBRE");
                String apepat = rs.getString("APEPAT");
                String apemat = rs.getString("APEMAT");
                String edad = rs.getString("EDAD");
                result = new Alumno(documento, nombre, apepat, apemat, edad);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            AccesoBD.closeConnection(conexion, ps);
        }
        return result;
    }

    public boolean delete(String codigo) {
        boolean result = false;
        try {
            conexion = AccesoBD.getConnection();
            String sql = "DELETE FROM ALUMNO WHERE DOCUMENTO=?";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, codigo);
            ps.execute();
            result = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            AccesoBD.closeConnection(conexion, ps);
        }
        return result;
    }

    public List<Alumno> readAll() {
        List<Alumno> result = new LinkedList<>();
        Alumno nuevo = null;
        try {
            conexion = AccesoBD.getConnection();
            String sql = "SELECT * FROM ALUMNO";
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String documento = rs.getString("DOCUMENTO");
                String nombre = rs.getString("NOMBRE");
                String apepat = rs.getString("APEPAT");
                String apemat = rs.getString("APEMAT");
                String edad = rs.getString("EDAD");
                nuevo = new Alumno(documento, nombre, apepat, apemat, edad);
                result.add(nuevo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            AccesoBD.closeConnection(conexion, ps);
        }
        return result;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author infie
 */
public class AccesoBD {
//en oracle
    private static final String USER = "prueba";
    private static final String PASS = "prueba";
    private static final String HOST = "@localhost:1521:ORCL";
    private static final String URL = "jdbc:oracle:thin:" + HOST;
    private static Connection conexion;
    
    //en mysql
    /*private static Connection conexion;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/ejercicio?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "root";*/
     
    /*private static Connection conexion;
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Alumno;";
    private static final String USER = "sa";
    private static final String PASS = "sqlserver";*/
    
    /*private static Connection conexion;
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/Alumno";
    private static final String USER = "postgres";
    private static final String PASS = "postgresql";*/
//jdbc:mysql://127.0.0.1:3306/?user=root

    public static Connection getConnection() {
        try {

          // Class.forName(DRIVER);//para mySQL es innecesario colocar el class.forName
            conexion = DriverManager.getConnection(URL,USER,PASS);
            System.out.println("Se ha hecho la conexion");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ggsote");
        }
        return conexion;

    }

    public static void closeConnection(Connection conexion, PreparedStatement ps) {
        try {
            conexion.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

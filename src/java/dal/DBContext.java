/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author PC
 */
public class DBContext {
    protected Connection connection;
    
    public  DBContext(){
        try{
            String url = "jdbc:sqlserver://localhost:1433;databaseName=laptopShop_3";
            String name = "sa";
            String pass = "sa";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, name, pass);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
}
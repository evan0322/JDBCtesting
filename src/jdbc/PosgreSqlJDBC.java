package jdbc;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PosgreSqlJDBC {

private static final String DB_DRIVER = "org.postgresql.Driver";
private static final String DB_CONNECTION="jdbc:postgresql://192.168.0.119:5432/userprofiles";
private static final String DB_USER = "wei";
private static final String DB_PASSWORD = "123";

public static void main(String[] argc) {
	String retval = "";

        System.out.println("***** PostgreSQL JDBC Connection Testing *****");

        try 
        {
        Class.forName(DB_DRIVER);
        }
        catch (ClassNotFoundException e) 
        {
        System.err.println("Please add PostgreSQL JDBC Driver in your Classpath ");
        System.err.println(e.getMessage());
        return;
        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        Connection connection = null;

        try 
        {
        connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        }
        catch (SQLException e) 
        {
        System.err.println("Connection Failed, Check console");
        System.err.println(e.getMessage());
        return;
        }

        if (connection == null) 
        {
        System.out.println("Connection Failed !");
        }
        else 
        {
        System.out.println("Connection established!");
        try {
        	//Retrieve data 
            DriverManager.setLoginTimeout(5);
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            
            Statement st = connection.createStatement();
            String sql;
            sql = "SELECT * FROM public.userdata WHERE id = 111";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                retval = rs.getString(1);
                System.out.println(retval);
            }
            rs.close();
            st.close();
            connection.close();
        	
        	//insert
        	/*connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        	Statement st = connection.createStatement();
            String sql;
            sql = "INSERT INTO public.userdata VALUES (99,'Test', 'Test')";
            st.executeUpdate(sql);*/
            
        } catch (SQLException e) {
            e.printStackTrace();
            retval = e.toString();
        }
        }
}
}
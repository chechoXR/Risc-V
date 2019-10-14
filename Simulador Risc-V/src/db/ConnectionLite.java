package db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import registros.Registros;
public class ConnectionLite {
	
	/**
	 * @param args the command line arguments
	 */
//	public static void main(String[] args) {
//		
//		ConnectionLite app = new ConnectionLite();
//		app.Insert("Insert into dss values(\"nauceas\", \"Por jartar mucho anoche :v \")");
//		app.Select("select * from dss;");
//		
//	}

	 private Connection connect() {
	        // SQLite connection string
//Para jar	
//		 String url = "jdbc:sqlite::resource:db/RISC-V-ISA";
	        String url = "jdbc:sqlite:db/RISC-V-ISA";
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(url);
//	            System.out.println("Conexión realizada con éxito");
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return conn;
	    }
	 
	    
	    public ArrayList<Registros> GetRegisters(){
	    	String query="select * from Registros;";
//	        String sql = "select * from dss where name like 'mi%';";
	    	ArrayList<Registros> registros = new ArrayList<Registros>();
	    	
	        String sql = query;
	        if(sql.charAt(sql.length()-1)!=';')
	        	sql+=";";
	        
	        
	        try (Connection conn = this.connect();
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
//	        	Crear BST, insertar datos como Objeto  y retortnarlo

	        	
//	        	System.out.println("Return ");
	            
	            // loop through the result set
	            while (rs.next()) {
//	                System.out.println(rs.getString("name") + "\t" + rs.getString("description"));
	            	registros.add(new Registros(rs.getString("Register"), rs.getString("ABI_Name"),rs.getString("Description"),rs.getString("Saver")));
	            }
	        	return registros;
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return registros=null;
	    }
	    

}

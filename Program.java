import java.sql.*;
import java.util.concurrent.Timeout;
public class Program {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://10.0.10.3:3306/javadatabase";

	static final String USER = "dfijalek";
	static final String PASS = "password";
   
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
        try{
 		Class.forName("com.mysql.cj.jdbc.Driver");
		
		try{
			TimeUnit.SECONDS.sleep(25);
		}catch{InterruptedException e}
			Thread.CurrentThread().interrupt();
		}

		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		stmt = conn.createStatement();
		stmt.executeUpdate("DROP TABLE IF EXIST Posts");
      		stmt.executeUpdate("CREATE TABLE Posts (id int, name varchar(255), text varchar(255))");
       		stmt.executeUpdate("INSERT INTO Posts (id, name, text) VALUES (1, 'Pierwszy post', 'Tresc posta 1')");
		stmt.executeUpdate("INSERT INTO Posts (id, name, text) VALUES (2, 'Drugi post', 'Tresc posta 2')");
		stmt.executeUpdate("INSERT INTO Posts (id, name, text) VALUES (3, 'Trzeci post', 'Tresc posta 2')");


      String sql;
      sql = "SELECT id, name, text FROM Posts";
      ResultSet rs = stmt.executeQuery(sql);

      while(rs.next()){
         int id  = rs.getInt("id");
         String first = rs.getString("name");
         String last = rs.getString("text");
         System.out.println("ID: " + id);
         System.out.println(", Nazwa posta: " + first);
         System.out.println(", Tekst: " + last);
      }
      rs.close();
      stmt.close();
      conn.close();
	
   }catch(SQLException se){
      se.printStackTrace();
   }catch(Exception e){
      e.printStackTrace();
   }finally{
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }
 }
}


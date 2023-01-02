package dbconnection;
import java.sql.*; 

public class DBConnection {

    
    public static void main(String[] args) {
      
      try{
            
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/travel_agency","root","4655");
        Statement statement = connection.createStatement();
        System.out.println("Connected successfully");
        
PreparedStatement selectworker = connection.prepareStatement("SELECT * FROM worker where wrk_AT like ?");
selectworker.setString(1,"ΑΒ155854");              //testing 

ResultSet rs = selectworker.executeQuery();
   ResultSetMetaData rsmd = rs.getMetaData();
   System.out.println("querying SELECT * FROM worker");
   int columnsNumber = rsmd.getColumnCount();
   while (rs.next()) {
       for (int i = 1; i <= columnsNumber; i++) {
           if (i > 1) System.out.print(",  ");
           String columnValue = rs.getString(i);
           System.out.print(columnValue + " " );
       }
       System.out.println("");
   }
        
        }catch (SQLException e){           
                 System.out.println("Error while connecting to db");
        } 
            
         }
        
    }
  
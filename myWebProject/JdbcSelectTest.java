import java.sql.*;   // Use 'Connection', 'Statement' and 'ResultSet' classes in java.sql package
 
// JDK 1.7 and above
public class JdbcSelectTest {   // Save as "JdbcSelectTest.java"
   public static void main(String[] args) {
      try (
         // Step 1: Allocate a database 'Connection' object
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/ebookshop?useSSL=false&serverTimezone=UTC", "myuser", "xxxx");
               // MySQL: "jdbc:mysql://hostname:port/databaseName", "username", "password"
 
         // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
      ) {
         // Step 3: Execute a SQL SELECT query, the query result
         //  is returned in a 'ResultSet' object.
         //String strSelect = "select * from books";
		 //String strSelect = "select title, price, qty from books";
		 String strSelect = "select title, author, price, qty from books where author = 'Tan Ah Teck' or price >= 30 order by price desc, id asc";
         System.out.println("The SQL query is: " + strSelect); // Echo For debugging
         System.out.println();
 
         ResultSet rset = stmt.executeQuery(strSelect);
 
         // Step 4: Process the ResultSet by scrolling the cursor forward via next().
         //  For each row, retrieve the contents of the cells with getXxx(columnName).
         System.out.println("The records selected are:");
         int rowCount = 0;
         while(rset.next()) {   // Move the cursor to the next row, return false if no more row
            String title = rset.getString("title");
            double price = rset.getDouble("price");
            int    qty   = rset.getInt("qty");
			//int id = rset.getInt("id");
			String author = rset.getString("author");
			
            System.out.println(title + ", " + author + ", " + price + ", " + qty);
            ++rowCount;
         }
         System.out.println("Total number of records = " + rowCount);
 
      } catch(SQLException ex) {
         ex.printStackTrace();
      }
      // Step 5: Close the resources - Done automatically by try-with-resources
   }
}
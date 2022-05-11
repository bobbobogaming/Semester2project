package Database;

import java.sql.*;

public class DatabaseWrapper {

  private DatabaseWrapper() {}

  static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres?currentSchema=\"Sep2Test\"";
  static final String USER = "postgres";
  static final String PASS = "1234";

  public static String getAllCharacters() {
    String characters = "";

    try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
      String query = "select * from character";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
        characters += "First name: " + rs.getString("fname")
            + ", Last name: " + rs.getString("lname") + "\n";
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return characters;
  }

  public static void addCharacter(String fname, String lname) {
    try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
      String query = "insert into character values (?, ?)";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, fname);
      stmt.setString(2, lname);

      stmt.execute();

    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    //    DatabaseWrapper.addCharacter("Anders", "H");
    //    DatabaseWrapper.addCharacter("Simon", "L");
    DatabaseWrapper.addCharacter("Simon", "C");
    System.out.println(DatabaseWrapper.getAllCharacters());

  }
}

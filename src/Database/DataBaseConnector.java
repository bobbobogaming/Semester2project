package Database;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseConnector implements IDatabaseConnector {

  public DataBaseConnector() {}

  static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres?currentSchema=\"dnd\"";
  static final String USER = "postgres";
  static final String PASS = "5432";

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

  public void addDataToDataBase(String table, ArrayList<Object> data){
    try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
      String query = "insert into " + table +" values (";

      for (int i = 0; i < data.size(); i++) {
        if(i!=0) {
          query += ", ?";
        }
        else {
          query += "?";
        }
      }
      query +=")";
      PreparedStatement stmt = conn.prepareStatement(query);
      //stmt.setObject(1, table);
      for (int i = 0; i < data.size() ; i++) {
        stmt.setObject(i+1,data.get(i));
      }


      stmt.execute();

    } catch (SQLException e) {
      e.printStackTrace();
    }

  }
  public static void main(String[] args) {
    //    DatabaseWrapper.addCharacter("Anders", "H");
    //    DatabaseWrapper.addCharacter("Simon", "L");
    ArrayList<String> player = new ArrayList<>();

    player.add("Morten");
    //DataBaseConnector.addDataToDataBase("Player",player);

    ArrayList<Object> data = new ArrayList<>();
    data.add(10);
    data.add(11);
    data.add(12);
    data.add(13);
    data.add(14);
    data.add(15);
    data.add(player.get(0));
    data.add("BOB");
    data.add(1);
    data.add("class");
    data.add(50);
    DataBaseConnector dataBaseConnector = new DataBaseConnector();
    dataBaseConnector.addDataToDataBase("Character",data);
    //System.out.println(DataBaseConnector.getAllCharacters());

  }
}

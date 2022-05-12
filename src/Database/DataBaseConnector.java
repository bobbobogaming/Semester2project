package Database;

import Application.Client.UserID;
import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.character.Stats;

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

      //first we make the number of elements that we need to be able to
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

  @Override
  public void deleteDataFromDataBase(String table, ArrayList<String> coloumns, ArrayList<Object> equals) {
    try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
      if (coloumns.size() == equals.size()) {
        String query = "Delete from " + table + " where ";


        //first we make the number of elements that we need to be able to
        for (int i = 0; i < coloumns.size(); i++) {
          if (i != 0) {
            query += " and "+ coloumns.get(i) +" = ? ";
          } else {
            query += coloumns.get(i) + " = ?";
          }
        }

        PreparedStatement stmt = conn.prepareStatement(query);
        //stmt.setObject(1, table);
        for (int i = 0; i < coloumns.size(); i++) {
          stmt.setObject(i + 1, equals.get(i));
        }

        stmt.execute();


      }
    } catch(SQLException e){
      e.printStackTrace();
    }
  }

  @Override
  public void selectAllDataFromTableDatabase(String table) {

    try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
      String query = "select * from " + table;

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);


      while (rs.next()) {
        System.out.println(rs.getArray(1));
        //System.out.println(rs.);
        //characters += "First name: " + rs.getString("fname")
        //        + ", Last name: " + rs.getString("lname") + "\n";
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public ArrayList<Character> getAllCharacterFromUser(UserID userID){
    ArrayList<Character> arrayListCharacter = new ArrayList<>();
    try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {

      String query = "select * from character where playerid = " + "'" + userID.getName()+
              "'" ;

      Statement stmt = conn.createStatement();

      ResultSet rs = stmt.executeQuery(query);


      while (rs.next()) {
        int constitution = rs.getInt("constitution");
        int wisdom = rs.getInt("wisdom");
        int charisma = rs.getInt("charisma");
        int intelligence = rs.getInt("intelligence");
        int strength = rs.getInt("strength") ;
        int dexterity = rs.getInt("Dexterity");
        String characterName = rs.getString("characterName");

        int level = rs.getInt("level");
        String clas = rs.getString("class");
        int maxHp = rs.getInt("maxHp");

        Stats stats = new Stats(strength,dexterity,constitution,intelligence,wisdom,charisma);
        arrayListCharacter.add(new Character(stats,characterName));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return arrayListCharacter;
  }

  @Override
  public ArrayList selectColumnsFromTableDataBase(ArrayList<String> columnsName, String table) {
    ArrayList<Object> returnvalue = new ArrayList<>();

    try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {

      String query = "select * from " + table;

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);


      while (rs.next()) {
        for (String columns:columnsName
             ) {
          returnvalue.add(rs.getObject(columns));
        }
        //System.out.println(rs.);
        //characters += "First name: " + rs.getString("fname")
        //        + ", Last name: " + rs.getString("lname") + "\n";
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return returnvalue;
  }


}

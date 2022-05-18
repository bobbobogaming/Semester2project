package Database.Adapters;

import Application.Client.UserID;
import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.character.Stats;
import Database.DataBaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectAllCharacterFromTableDatabase {
    private Database.IDatabaseConnector iDatabaseConnector;

    public SelectAllCharacterFromTableDatabase() {
        iDatabaseConnector = new DataBaseConnector();
    }

    public ArrayList<Character> getAllCharacterFromTableDatabase(UserID userID){

        ArrayList<Character> arrayListCharacter = new ArrayList<>();

        ResultSet rs = null;
        try {
            rs = iDatabaseConnector.selectAllDataFromTable("viewcharacter",userID);
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

                Stats stats = new Stats(strength,dexterity,constitution,intelligence,wisdom,charisma, maxHp);
                arrayListCharacter.add(new Character(stats,characterName));
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return arrayListCharacter;

    }

}

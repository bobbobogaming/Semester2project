package Database.Adapters;

import Application.Client.UserID;
import Application.MVVM.Model.character.Character;
import Database.DataBaseConnector;

import java.util.ArrayList;

public class CharacterDeleteFromDatabase {
    private Database.IDatabaseConnector iDatabaseConnector;

    public CharacterDeleteFromDatabase() {
        iDatabaseConnector = new DataBaseConnector();
    }

    public void deleteCharacterFromDatabase(UserID userID, String characterName){
        String table = "character";

        String userName = userID.getName();
       // String characterName = character.getName(); // just in case we want to use character instead

        ArrayList<Object> conditions = new ArrayList<>(2);
        ArrayList<String> collumns = new ArrayList<>(2);

        conditions.add(userName);
        conditions.add(characterName);

        collumns.add("playerid");
        collumns.add("charactername");



        iDatabaseConnector.deleteDataFromDataBase(table,collumns,conditions);
    }
}

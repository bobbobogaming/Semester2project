package Database.Adapters;

import Application.Client.UserID;
import Application.MVVM.Model.character.Character;
import Database.DataBaseConnector;

import java.util.ArrayList;

public class SelectAllCharacterFromTableDatabase {
    private Database.IDatabaseConnector iDatabaseConnector;

    public SelectAllCharacterFromTableDatabase() {
        iDatabaseConnector = new DataBaseConnector();
    }

    public ArrayList<Character> getAllCharacterFromTableDatabase(UserID userID){

        return iDatabaseConnector.getAllCharacterFromUser(userID);

    }

}

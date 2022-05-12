package Database;

import Application.Client.UserID;
import Application.MVVM.Model.character.Character;

import java.util.ArrayList;

public interface IDatabaseConnector {

    void addDataToDataBase(String table, ArrayList<Object> database);

    void deleteDataFromDataBase(String table,ArrayList<String> coloumns, ArrayList<Object> equals);

    void selectAllDataFromTableDatabase(String table);

    ArrayList selectColumnsFromTableDataBase(ArrayList<String> columnsName, String table);

    ArrayList<Character> getAllCharacterFromUser(UserID userID);



}

package Database.Adapters;

import Application.Client.UserID;
import Application.MVVM.Model.character.Character;
import Database.DataBaseConnector;
import Database.IDatabaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectAllUserName {
    public ArrayList<String> getAllUserName(){

        IDatabaseConnector iDatabaseConnector = new DataBaseConnector();

        ArrayList<String> arrayListUsers = new ArrayList<>();

        ResultSet rs = null;
        try {
            rs = iDatabaseConnector.selectAllDataFromTable("player");
            while (rs.next()) {
                String username = rs.getString("playerid");

                arrayListUsers.add(username);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return arrayListUsers;
    }
}

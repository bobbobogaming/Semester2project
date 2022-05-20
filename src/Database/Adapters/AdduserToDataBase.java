package Database.Adapters;

import Application.Client.UserID;
import Database.DataBaseConnector;
import Database.IDatabaseConnector;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdduserToDataBase {
    public void addUser(UserID userID) throws SQLException {
        IDatabaseConnector dataBaseConnector = new DataBaseConnector();


        String username;
        boolean isDM;

        ArrayList<Object> insert = new ArrayList<>();

        username = userID.getName();

        insert.add(username);

        //gets all the user so we can check if the person already is in the database

        SelectAllUserName selectAllUserName = new SelectAllUserName();

        ArrayList<String> userNameList= selectAllUserName.getAllUserName();

        if (!userNameList.contains(username)){
            dataBaseConnector.addDataToDataBase("player", insert);
        }

    }
}

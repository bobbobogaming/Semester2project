package Database;

import Application.Client.UserID;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IDatabaseConnector {

    void addDataToDataBase(String table, ArrayList<Object> database);

    void deleteDataFromDataBase(String table,ArrayList<String> coloumns, ArrayList<Object> equals);

    void selectAllDataFromTableDatabase(String table);


    ResultSet selectAllDataFromTable(String table,UserID userID) throws SQLException;

    ResultSet selectAllDataFromTable(String table);



}

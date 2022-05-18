package Database;

import Application.Client.UserID;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IDatabaseConnector {

    void addDataToDataBase(String table, ArrayList<Object> database) throws SQLException;

    void deleteDataFromDataBase(String table,ArrayList<String> coloumns, ArrayList<Object> equals);



    ResultSet selectAllDataFromTable(String table,UserID userID) throws SQLException;

    ResultSet selectAllDataFromTable(String table) throws SQLException;



}

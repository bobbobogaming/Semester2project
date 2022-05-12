package Database;

import java.util.ArrayList;

public interface IDatabaseConnector {

    void addDataToDataBase(String table, ArrayList<Object> database);

    void deleteDataFromDataBase(String table,ArrayList<String> coloumns, ArrayList<Object> equals);




}

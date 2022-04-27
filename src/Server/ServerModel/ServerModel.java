package Server.ServerModel;

import MVVM.Model.Charachter;
import Server.ServerConnector.JDBC;

public class ServerModel implements IServerModel {

    JDBC jdbc;
    public ServerModel(){
        jdbc = new JDBC();
    }

    @Override
    public void makeCharcter(Character character) {
        JDBC.makeCharecter(character);
    }

    @Override
    public Charachter getCharcter(String name) {
        return JDBC.getCharecter(name);
    }
}

package Server;

import MVVM.Model.Charachter;
import Server.ServerModel.IServerModel;
import Server.ServerModel.ServerModel;
import Util.Iserver;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Remote, Iserver {
    IServerModel serverModel;
    public Server() throws RemoteException {
        UnicastRemoteObject.exportObject(this,0);

        serverModel = new ServerModel();
    }

    @Override
    public void makeCharacter(Character character) {
        serverModel.makeCharcter(character);
    }

    @Override
    public Charachter getCharacter(String name) {
        return serverModel.getCharcter(name);
    }
}

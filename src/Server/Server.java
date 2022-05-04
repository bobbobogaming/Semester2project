package Server;

import Application.MVVM.Model.Character;
import Server.ServerModel.IServerModel;
import Server.ServerModel.ServerModel;
import Util.IServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Remote, IServer
{
    IServerModel serverModel;
    public Server() throws RemoteException {
        UnicastRemoteObject.exportObject(this,0);

        serverModel = new ServerModel();
    }

    @Override
    public void makeCharacter(Character character) {
        serverModel.makeCharacter(character);
    }

    @Override
    public Character getCharacter(String name) {
        return serverModel.getCharacter(name);
    }
}

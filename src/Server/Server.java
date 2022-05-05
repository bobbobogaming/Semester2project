package Server;

import Application.MVVM.Model.character.Character;
import Server.ServerModel.IServerModel;
import Server.ServerModel.ServerModel;
import Util.IServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements IServer
{
    IServerModel serverModel;
    public Server() throws RemoteException {
        UnicastRemoteObject.exportObject(this,0);

        serverModel = new ServerModel();
    }

    @Override
    public void saveCharacter(Character character) {
        serverModel.saveCharacter(character);
    }

    @Override
    public Character getCharacter(String name) {
        return serverModel.getCharacter(name);
    }
}

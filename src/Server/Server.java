package Server;

import Application.MVVM.Model.character.Character;
import Util.IClientModel;
import Util.IServerModel;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server implements IServerModel {

    private final ArrayList<Lobby> lobbies;

    public Server() throws RemoteException {
        lobbies = new ArrayList<>();
        UnicastRemoteObject.exportObject(this,0);
    }

    @Override public void createLobby(IClientModel lobbyCreator) {
        lobbies.add(new Lobby(lobbyCreator));
    }

    @Override public void connectToLobby(int lobbyId, IClientModel client) {
        lobbies.get(0).addPlayer(client);
    }

    public void saveCharacter(Character character) throws RemoteException {

    }

    public Character getCharacter(String name) throws RemoteException {
        return null;
    }
}

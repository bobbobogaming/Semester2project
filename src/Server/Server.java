package Server;

import Application.MVVM.Model.character.Character;
import Shared.IClientModel;
import Shared.IServerModel;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.HashMap;

public class Server implements IServerModel {

    private final Map<Integer, Lobby> lobbies;
    private int nextLobbyId;

    public Server() throws RemoteException {
        nextLobbyId = 0;
        lobbies = new HashMap<>();
        UnicastRemoteObject.exportObject(this,0);
    }

    @Override public int createLobby(IClientModel lobbyCreator) {
        Lobby lobby = new Lobby(nextLobbyId, lobbyCreator);
        lobbies.put(nextLobbyId++, lobby);
        return lobby.getLobbyId();
    }

    @Override public void connectToLobby(int lobbyId, IClientModel client) {
        lobbies.get(lobbyId).addPlayer(client);
    }

    public void saveCharacter(Character character) throws RemoteException {
        System.out.println(character);
    }

    public Character getCharacter(String name) throws RemoteException {
        return null;
    }
}

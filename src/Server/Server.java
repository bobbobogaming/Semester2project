package Server;

import Application.Client.UserID;
import Application.MVVM.Model.character.Stats;
import Application.MVVM.Model.initWrapper.InitWrapper;
import Application.MVVM.Model.character.Character;
import Database.Adapters.AdduserToDataBase;
import Application.MVVM.Model.monster.Monster;
import Database.Adapters.CharacterInsertIntoDatabase;
import Database.Adapters.GetMonsterFromDataBaseView;
import Shared.IClientModel;
import Shared.IServerModel;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.*;

public class Server implements IServerModel {

    private final Map<Integer, Lobby> lobbies;
    private int nextLobbyId;
    private ArrayList<Character> characters; //TODO remove

    public Server() throws RemoteException {
        nextLobbyId = 0;
        lobbies = new HashMap<>();
        UnicastRemoteObject.exportObject(this,0);

        characters = new ArrayList<>();
        characters.add(new Character(new Stats(13,14,15,13,10,9,100),"Per"));
        characters.add(new Character(new Stats(17,10,12,14,12,1,125),"Anders"));
        characters.add(new Character(new Stats(15,11,12,14,16,2,1),"Michael"));
        characters.add(new Character(new Stats(13,10,13,18,14,8,9999),"Morten"));
    }

    @Override public int createLobby(IClientModel lobbyCreator) {
        Lobby lobby = new Lobby(nextLobbyId, lobbyCreator);
        lobbies.put(nextLobbyId++, lobby);
        return lobby.getLobbyId();
    }

    @Override public void connectToLobby(int lobbyId, IClientModel client) {
        lobbies.get(lobbyId).addPlayer(client);
    }

    @Override
    public ArrayList<Monster> getMonsters() {
        GetMonsterFromDataBaseView getMonsterFromDataBaseView = new GetMonsterFromDataBaseView();

        return getMonsterFromDataBaseView.getData();
    }

    @Override
    public void saveUser(UserID userID) throws RemoteException, SQLException {
        AdduserToDataBase adduserToDataBase = new AdduserToDataBase();
        adduserToDataBase.addUser(userID);
    }
  
    @Override public void addInitiative(InitWrapper initiative, int lobbyId) throws RemoteException {
        lobbies.get(lobbyId).addInitiative(initiative);
    }

    @Override public void removeInitiative(InitWrapper initiative, int lobbyId) throws RemoteException {
        lobbies.get(lobbyId).removeInitiative(initiative);
    }

    @Override public void updateInitiative(InitWrapper initiative, int lobbyId) throws RemoteException {
        lobbies.get(lobbyId).updateInitiative(initiative);
    }

    public void saveCharacter(Character character, UserID userID) throws RemoteException, SQLException {
        System.out.println(character);
        characters.add(character);
        //CharacterInsertIntoDatabase insertData = new CharacterInsertIntoDatabase();
        //insertData.InsertCharacterIntoDatabase(character,userID);
    }

    public ArrayList<Character> getCharacters(UserID userID) throws RemoteException {
        characters.sort(Comparator.comparing(Character::getName));
        return characters;

    }
}

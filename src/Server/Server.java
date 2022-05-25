package Server;

import Application.Client.UserID;
import Application.MVVM.Model.character.Stats;
import Application.MVVM.Model.initWrapper.InitWrapper;
import Application.MVVM.Model.character.Character;
import Database.Adapters.*;
import Application.MVVM.Model.monster.Monster;
import Shared.IClientModel;
import Shared.IServerModel;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.*;

public class Server implements IServerModel {

    private final Map<Integer, Lobby> lobbies;
    private int nextLobbyId;
//    private ArrayList<Character> characters; //TODO remove

    public Server() throws RemoteException {
        nextLobbyId = 0;
        lobbies = new HashMap<>();
        UnicastRemoteObject.exportObject(this,0);

        /*
        characters = new ArrayList<>();
        characters.add(new Character(new Stats(13,14,15,13,10,9,100),"Per",1,"Monk"));
        characters.add(new Character(new Stats(17,10,12,14,12,1,125),"Anders",2,"Something"));
        characters.add(new Character(new Stats(15,11,12,14,16,2,1),"Michael",3,"test"));
        characters.add(new Character(new Stats(13,10,13,18,14,8,9999),"Morten",4,"ss"));

         */
    }

    @Override public int createLobby(IClientModel lobbyCreator) {
        Lobby lobby = new Lobby(nextLobbyId, lobbyCreator);
        lobbies.put(nextLobbyId++, lobby);
        return lobby.getLobbyId();
    }

    @Override public boolean connectToLobby(int lobbyId, IClientModel client) {
        try {
            return lobbies.get(lobbyId).addPlayer(client);
        }
        catch (NullPointerException e) {
            return false;
        }
    }
    @Override public void disconnectFromLobby(int lobbyId, IClientModel client){
        lobbies.get(lobbyId).removePlayer(client);
    }

    @Override public void switchCombatState(int lobbyId) {
        lobbies.get(lobbyId).switchCombatState();
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

    @Override public boolean isLobbyStarted(int lobbyId) {
        return lobbies.get(lobbyId).isCombatStarted();
    }

    public void saveCharacter(Character character, UserID userID) throws RemoteException, SQLException {

       // characters.add(character);
        CharacterInsertIntoDatabase insertData = new CharacterInsertIntoDatabase();
        insertData.InsertCharacterIntoDatabase(character,userID);
    }

    @Override public void deleteCharacter(Character character, UserID userID)
        throws RemoteException, SQLException {
        CharacterDeleteFromDatabase deleteCharacter = new CharacterDeleteFromDatabase();
        deleteCharacter.deleteCharacterFromDatabase(userID,character.getName());

    }

    public ArrayList<Character> getCharacters(UserID userID) throws RemoteException {
        SelectAllCharacterFromTableDatabase getlistCharcter = new SelectAllCharacterFromTableDatabase();
        ArrayList<Character> characters;
        characters = getlistCharcter.getAllCharacterFromTableDatabase(userID);
        characters.sort(Comparator.comparing(Character::getName));

        return characters;

    }
}

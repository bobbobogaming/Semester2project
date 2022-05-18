package Server.ServerModel;

import Application.Client.UserID;
import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.character.Stats;
//import Database.DatabaseWrapper;
import Database.Adapters.AdduserToDataBase;
import Application.MVVM.Model.monster.Monster;
import Database.Adapters.CharacterInsertIntoDatabase;
import Server.Lobby;
import Shared.IClientModel;
import Shared.IServerModel;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServerModelOld //implements IServerModel
    {
    private final ArrayList<Lobby> lobbies;

    private UserID user;

    public ServerModelOld(){
        lobbies = new ArrayList<>();
    }

    public ServerModelOld(ArrayList<Lobby> lobbies) {
        this.lobbies = lobbies;
    }

    //@Override
    public void saveCharacter(Character character , UserID user) throws SQLException {
        System.out.println("test");
        CharacterInsertIntoDatabase characterInsertIntoDatabase = new CharacterInsertIntoDatabase();
        characterInsertIntoDatabase.InsertCharacterIntoDatabase(character,user);
        System.out.println(character.getName());
        //DatabaseWrapper.addCharacter(character.getName(),"n/a");
    }

    //@Override
    public Character getCharacter(String name) {
  //      DatabaseWrapper.getAllCharacters();
        return  new Character(new Stats(1,2,3,4,5,6,14),"yo");
    }

    //@Override
    public int createLobby(IClientModel dungeonMaster) {
        //lobbies.add(new Lobby(dungeonMaster));
        return 1;
    }

    //@Override
    public void connectToLobby(int lobbyId, IClientModel client) {
        lobbies.get(lobbyId).addPlayer(client);
    }

    //@Override
    public void saveUser(UserID userID) throws RemoteException, SQLException {
        AdduserToDataBase adduserToDataBase = new AdduserToDataBase();
        adduserToDataBase.addUser(userID);
    }

    //@Override
    public void addMonster(Monster monster, int lobbyId)
        throws RemoteException {

    }

    //@Override
    public ArrayList<Monster> getMonsters(int lobbyId)
        throws RemoteException {
        return null;
    }
}

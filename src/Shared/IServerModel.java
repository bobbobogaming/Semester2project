package Shared;

import Application.Client.UserID;
import Application.MVVM.Model.initWrapper.InitWrapper;
import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.monster.Monster;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IServerModel extends Remote
{
    void saveCharacter(Character character, UserID userID) throws RemoteException, SQLException;
    Character getCharacter(String name) throws RemoteException;
    int createLobby(IClientModel lobbyCreator) throws RemoteException;
    void connectToLobby(int lobbyId, IClientModel client) throws RemoteException;


    ArrayList<Monster> getMonsters() throws RemoteException, SQLException;
    void saveUser(UserID userID) throws RemoteException, SQLException;
    void addInitiative(InitWrapper initiative, int lobbyId) throws RemoteException;
    void removeInitiative(InitWrapper initiative, int lobbyId) throws RemoteException;
    void updateInitiative(InitWrapper initiative, int lobbyId) throws RemoteException;
}

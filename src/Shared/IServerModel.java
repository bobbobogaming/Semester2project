package Shared;

import Application.Client.UserID;
import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.monster.Monster;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IServerModel extends Remote
{
    void saveCharacter(Character character, UserID userID) throws RemoteException;
    Character getCharacter(String name) throws RemoteException;
    int createLobby(IClientModel lobbyCreator) throws RemoteException;
    void connectToLobby(int lobbyId, IClientModel client) throws RemoteException;

    void saveUser(UserID userID) throws RemoteException;
    void addMonster(Monster monster, int lobbyId) throws RemoteException;
    void removeMonster(Monster monster, int lobbyId) throws RemoteException;
}

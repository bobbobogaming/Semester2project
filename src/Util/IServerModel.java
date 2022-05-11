package Util;

import Application.MVVM.Model.character.Character;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServerModel extends Remote
{
    void saveCharacter(Character character) throws RemoteException;
    Character getCharacter(String name) throws RemoteException;
    void createLobby(IClientModel lobbyCreator) throws RemoteException;
    void connectToLobby(int lobbyId, IClientModel client) throws RemoteException;
}

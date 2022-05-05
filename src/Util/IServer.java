package Util;

import Application.MVVM.Model.character.Character;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote
{
    void saveCharacter(Character character) throws RemoteException;
    Character getCharacter(String name) throws RemoteException;
}

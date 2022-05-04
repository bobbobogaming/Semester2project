package Util;

import Application.MVVM.Model.Character;

import java.rmi.RemoteException;

public interface IServer
{
    void makeCharacter(Character character) throws RemoteException;
    Character getCharacter(String name) throws RemoteException;
}

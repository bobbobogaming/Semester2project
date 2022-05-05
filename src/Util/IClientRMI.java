package Util;

import Application.MVVM.Model.character.Character;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClientRMI extends Remote
{
  void makeCharacter(Character character) throws RemoteException;
  Character getCharacter(String name) throws RemoteException;
}

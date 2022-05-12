package Shared;

import Application.MVVM.Model.character.Character;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClientModel extends Remote
{
  void makeCharacter(Character character) throws RemoteException;
  Character getCharacter(String name) throws RemoteException;
}
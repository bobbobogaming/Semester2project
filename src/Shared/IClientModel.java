package Shared;

import Application.MVVM.Model.InitWrapper;
import Application.MVVM.Model.character.Character;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IClientModel extends Remote
{
  void makeCharacter(Character character) throws RemoteException;
  Character getCharacter(String name) throws RemoteException;
  String getUsername() throws RemoteException;
  void updateInitiativeTable(ArrayList<InitWrapper> initWrappers) throws RemoteException;
}

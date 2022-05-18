package Shared;

import Application.MVVM.Model.initWrapper.InitWrapper;
import Application.MVVM.Model.character.Character;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IClientModel extends Remote
{
  void makeCharacter(Character character) throws RemoteException, SQLException;
  Character getCharacter(String name) throws RemoteException;
  String getUsername() throws RemoteException;
  void updateInitiativeTable(ArrayList<InitWrapper> initWrappers) throws RemoteException;
}

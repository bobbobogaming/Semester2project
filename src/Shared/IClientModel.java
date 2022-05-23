package Shared;

import Application.MVVM.Model.initWrapper.InitWrapper;
import Application.MVVM.Model.character.Character;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IClientModel extends Remote
{
  String getUsername() throws RemoteException;
  void updateInitiativeTable(ArrayList<InitWrapper> initWrappers) throws RemoteException;

  void combatStateChanged(boolean isStarted) throws RemoteException;
}

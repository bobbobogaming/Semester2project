package Shared;

import Application.Client.UserID;
import Application.MVVM.Model.initWrapper.InitWrapper;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IClientModel extends Remote
{
  UserID getUserID() throws RemoteException;
  void updateInitiativeTable(ArrayList<InitWrapper> initWrappers) throws RemoteException;

  void combatStateChanged(boolean isStarted) throws RemoteException;
  void modifyDMCharacterViews(boolean isStarted, ArrayList<UserID> userIDS) throws RemoteException;
}

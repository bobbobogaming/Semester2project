package Application.Client;

import Application.MVVM.Model.character.Character;
import Shared.IClientModel;
import Shared.IServerModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client implements IClientModel, ClientLogin, ClientLobby
{
  private final IServerModel server;
  private PropertyChangeSupport support;
  private UserID userID;

  public Client() throws RemoteException, NotBoundException
  {
    UnicastRemoteObject.exportObject(this,0);
    Registry registry = LocateRegistry.getRegistry("localhost",1099);
    server = (IServerModel) registry.lookup("Server");
    support = new PropertyChangeSupport(this);
  }

  @Override public void makeCharacter(Character character)
      throws RemoteException
  {
    server.saveCharacter(character);
  }

  @Override public Character getCharacter(String name) throws RemoteException
  {
    return server.getCharacter(name);
  }

  @Override public void createLobby()
  {
    try
    {
      support.firePropertyChange("connectAsDM",null,server.createLobby(this));
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void connectToLobby(int lobbyId) {
    try
    {
      server.connectToLobby(lobbyId, this);
      support.firePropertyChange("connectAsDM",null,lobbyId);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void setUserID(UserID userID)
  {
    this.userID = userID;
    support.firePropertyChange("Tabs",null,null);
    System.out.println(this.userID);
  }

  @Override public void onExit()
  {
    try
    {
      UnicastRemoteObject.unexportObject(this,true);
    }
    catch (NoSuchObjectException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }
}

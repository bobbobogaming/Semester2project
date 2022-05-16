package Application.Client;

import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.character.Stats;
import Application.MVVM.Model.monster.Action;
import Application.MVVM.Model.monster.Monster;
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
import java.util.ArrayList;

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
    System.out.println(character);
    server.saveCharacter(character, userID);
  }

  @Override public Character getCharacter(String name) throws RemoteException
  {
    return server.getCharacter(name);
  }

  @Override public String getUsername() throws RemoteException {
    return userID.getName();
  }

  @Override public void updateMonsterTable(ArrayList<Monster> monsters) throws RemoteException {
    support.firePropertyChange("UpdateMonsterTable", null, monsters);
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
      support.firePropertyChange("connectAsPlayer",null,lobbyId);
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
  }

  @Override public void getMonsters() {
    ArrayList<String> arrayList = new ArrayList<>();
    arrayList.add("cat");
    arrayList.add("dog");
    arrayList.add("simonC");
    arrayList.add("simonL");
    support.firePropertyChange("MonsterView",null,arrayList);
  }

  @Override public void removeMonsterFromLobby(Monster monster) {
    try {
      server.removeMonster(monster, userID.getLobbyId());
    }
    catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  @Override public void addMonsterToLobby(Monster monster) {
    try {
      server.addMonster(monster, userID.getLobbyId());
    }
    catch (RemoteException e) {
      throw new RuntimeException(e);
    }
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

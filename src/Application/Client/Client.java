package Application.Client;

import Application.MVVM.Model.character.Stats;
import Application.MVVM.Model.initWrapper.InitWrapper;
import Application.MVVM.Model.character.Character;
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
import java.sql.SQLException;
import java.util.ArrayList;

public class Client implements IClientModel, ClientLogin, ClientLobby, ClientAddMonster, ClientChooseCharacter
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
          throws RemoteException, SQLException {
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

  @Override public void updateInitiativeTable(ArrayList<InitWrapper> monsters) throws RemoteException {
    support.firePropertyChange("UpdateInitiativeTable", null, monsters);
  }

  @Override public void createLobby()
  {
    try
    {
      int lobbyId = server.createLobby(this);
      userID.setLobbyId(lobbyId);
      support.firePropertyChange("connectAsDM",null,lobbyId);
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
      userID.setLobbyId(lobbyId);
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
    ArrayList<Monster> arrayList = new ArrayList<>();

    arrayList.add(new Monster(new Stats(10,10,10,10,10,10,10),10,"10","per",new ArrayList<>()));
    arrayList.add(new Monster(new Stats(10,10,10,10,10,10,20),10,"10","cat",new ArrayList<>()));
    arrayList.add(new Monster(new Stats(10,10,10,10,10,10,10),10,"10","dog",new ArrayList<>()));
    arrayList.add(new Monster(new Stats(10,10,10,10,10,10,10),10,"10","simon",new ArrayList<>()));

    /*
    try {
      arrayList = server.getMonsters();
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    */
    support.firePropertyChange("MonsterView",null,arrayList);
  }

  @Override public void removeInitiativeFromLobby(InitWrapper initiative) {
    try {
      server.removeInitiative(initiative, userID.getLobbyId());
    }
    catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  @Override public void updateInitList(InitWrapper initiative){
    try {
      server.updateInitiative(initiative, userID.getLobbyId());
    }
    catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  };

  @Override
  public void addInitiativeToLobby(InitWrapper initiative) {
    try {
      server.addInitiative(initiative, userID.getLobbyId());
    }
    catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  @Override public void joinCombatAsCharacter() {

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

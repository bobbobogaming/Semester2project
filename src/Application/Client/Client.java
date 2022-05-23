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

public class Client implements IClientModel, ClientLogin, ClientLobby, ClientAddMonster, ClientChooseCharacter, ClientCharacterSheet
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

  @Override public void makeCharacter(Character character) throws SQLException {
    //System.out.println(character);
    try {
      server.saveCharacter(character, userID);
    }
    catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  @Override public void setCurrentCharacter(Character character) {
    userID.setCurrentCharacter(character);
  }

  @Override public ArrayList<Character> getCharacters()
  {
    try {
      return server.getCharacters(userID);
    }
    catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  @Override public String getUsername() throws RemoteException {
    return userID.getName();
  }

  @Override public UserID getUserID() {
    return userID;
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

  @Override public boolean connectToLobby(int lobbyId) {
    try
    {
      boolean connected = server.connectToLobby(lobbyId, this);
      if (connected) {
        userID.setLobbyId(lobbyId);
        support.firePropertyChange("connectAsPlayer",null,lobbyId);
      }
      return connected;
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return false;
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
    if (userID.getCurrentCharacter() == null) {
      //An error message is passed through newValue
      support.firePropertyChange("joinCombatFailed", null,
          "Please select a character before joining combat"
              + "\n(Character Sheet -> Select character -> Play as character)");
    }
    else if (!userID.isInLobby()) {
      try {
        server.addInitiative(new InitWrapper(userID.getCurrentCharacter()),userID.getLobbyId());
        userID.setInLobby(true);
        support.firePropertyChange("joinCombatSuccess", null, null);
      }
      catch (RemoteException e) {
        throw new RuntimeException(e);
      }
    }
    else {
      try {
        server.removeInitiative(new InitWrapper(userID.getCurrentCharacter()), userID.getLobbyId());
        userID.setInLobby(false);
        support.firePropertyChange("leaveCombatSuccess", null, null);
      }
      catch (RemoteException e) {
        throw new RuntimeException(e);
      }
    }
  }

  @Override public void switchCombatState() {
    try {
      server.switchCombatState(userID.getLobbyId());
    }
    catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  @Override public void combatStateChanged(boolean isStarted) {
    if (isStarted) {
      support.firePropertyChange("combatStarted", null, null);
    }
    else {
      support.firePropertyChange("combatEnded", null, null);
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

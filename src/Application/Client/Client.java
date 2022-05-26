package Application.Client;

import Application.MVVM.Model.character.Stats;
import Application.MVVM.Model.initWrapper.InitWrapper;
import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.monster.Monster;
import Shared.IClientModel;
import Shared.IServerModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class Client implements IClientModel, ClientLogin, ClientLobby, ClientLobbyDM, ClientLobbyPlayer, ClientAddMonster, ClientChooseCharacter, ClientCharacterSheet
{
  private final IServerModel server;
  private final PropertyChangeSupport support;
  private UserID userID;

  public Client() throws RemoteException, NotBoundException
  {
    UnicastRemoteObject.exportObject(this,0);
    Registry registry = LocateRegistry.getRegistry("localhost",1099);
    server = (IServerModel) registry.lookup("Server");
    support = new PropertyChangeSupport(this);
  }

  @Override public void makeCharacter(String name,int str, int dex, int con, int intel,
      int wis, int cha, int lvl, String cClass, int maxHp) {
    try {
      server.saveCharacter(new Character(new Stats(str,dex,con,intel,wis,cha,maxHp),name,lvl,cClass), userID);
    }
    catch (RemoteException | SQLException e) {
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

  @Override public void deleteCharacter(Character character) {
    try {
      server.deleteCharacter(character, userID);
    }
    catch (RemoteException | SQLException e) {
      throw new RuntimeException(e);
    }
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
        userID.setInLobby(true);
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
    try {
      server.saveUser(userID);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    support.firePropertyChange("Tabs",null,null);
  }

  @Override public void getMonsters() {
    ArrayList<Monster> arrayList = new ArrayList<>();
/*
    arrayList.add(new Monster(new Stats(10,10,10,10,10,10,10),10,"0,5","per",new ArrayList<>()));
    arrayList.add(new Monster(new Stats(10,10,10,10,10,10,20),10,"0,125","cat",new ArrayList<>()));
    arrayList.add(new Monster(new Stats(10,10,10,10,10,10,10),10,"0,25","dog",new ArrayList<>()));
    arrayList.add(new Monster(new Stats(10,10,10,10,10,10,10),10,"10","simon",new ArrayList<>()));

 */


    try {
      arrayList = server.getMonsters();
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

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
  }

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
    boolean isLobbyStarted;

    try {
      isLobbyStarted = server.isLobbyStarted(userID.getLobbyId());
    }
    catch (RemoteException e) {
      throw new RuntimeException(e);
    }

    if (userID.getCurrentCharacter() == null) {
      //An error message is passed through newValue
      support.firePropertyChange("joinCombatFailed", null,
          "Please select a character before joining combat"
              + "\n(Character Sheet -> Select character -> Play as character)");
    }
    else if (isLobbyStarted) {
      //An error message is passed through newValue
      support.firePropertyChange("joinCombatFailed", null,
          "Can't join combat. A combat is currently ongoing");
    }
    else if (!userID.isInCombat()) {
      try {
        server.addInitiative(new InitWrapper(userID.getCurrentCharacter()),userID.getLobbyId());
        userID.setInCombat(true);
        support.firePropertyChange("joinCombatSuccess", null, null);
      }
      catch (RemoteException e) {
        throw new RuntimeException(e);
      }
    }
    else {
      try {
        server.removeInitiative(new InitWrapper(userID.getCurrentCharacter()), userID.getLobbyId());
        userID.setInCombat(false);
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

  @Override public void modifyDMCharacterViews(boolean isStarted, ArrayList<UserID> userIDS) {
    if (isStarted) {
      support.firePropertyChange("generateCharacterViews", null, userIDS);
    }
    else {
      support.firePropertyChange("clearCharacterViews", null, null);
    }
  }

  @Override public void onExit()
  {
    try
    {
      if (userID != null){
        if (userID.isInLobby()) {
          server.disconnectFromLobby(userID.getLobbyId(), this);
        }
      }
      UnicastRemoteObject.unexportObject(this,true);
    }
    catch (RemoteException e)
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

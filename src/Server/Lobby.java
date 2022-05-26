package Server;

import Application.Client.UserID;
import Application.MVVM.Model.initWrapper.InitWrapper;
import Shared.IClientModel;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Lobby {
  private final int lobbyId;
  private final ArrayList<IClientModel> players;
  private final ArrayList<InitWrapper> initiative;
  private IClientModel dungeonMaster;
  private boolean isCombatStarted;

  public Lobby(int lobbyId, IClientModel dungeonMaster) {
    this.lobbyId = lobbyId;
    this.players = new ArrayList<>();
    this.initiative = new ArrayList<>();
    this.dungeonMaster = dungeonMaster;
  }

  public boolean addPlayer(IClientModel client) {
    if (!isCombatStarted){
      players.add(client);
      try {
        client.updateInitiativeTable(initiative);
      }
      catch (RemoteException e) {
        throw new RuntimeException(e);
      }
    return true;
    } else return false;
  }

  public void removePlayer(IClientModel client) {
    if (client == dungeonMaster)
      setDungeonMaster(null);
    else players.remove(client);
  }

  public void switchCombatState(){
    isCombatStarted = !isCombatStarted;
    try {
      dungeonMaster.combatStateChanged(isCombatStarted);
      ArrayList<UserID> userIDS = new ArrayList<>();
      players.forEach((player)->{
        try {
          if (player.getUserID().isInCombat()) userIDS.add(player.getUserID());
        }
        catch (RemoteException e) {
          throw new RuntimeException(e);
        }
      });
      dungeonMaster.modifyDMCharacterViews(isCombatStarted,userIDS);
    }
    catch (RemoteException e) {
      throw new RuntimeException(e);
    }
    players.forEach((player) -> {
      try {
        player.combatStateChanged(isCombatStarted);
      }
      catch (RemoteException e) {
        throw new RuntimeException(e);
      }
    });
  }

  public void addInitiative(InitWrapper initiative) {
    this.initiative.add(initiative);
    this.initiative.sort(InitWrapper::compareTo);
    try {
      dungeonMaster.updateInitiativeTable(this.initiative);
      players.forEach((player) -> {
        try {
          player.updateInitiativeTable(this.initiative);
        }
        catch (RemoteException e) {
          throw new RuntimeException(e);
        }
      });
    }
    catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public void removeInitiative(InitWrapper initiative) {
    this.initiative.remove(initiative);
    try {
      dungeonMaster.updateInitiativeTable(this.initiative);
      players.forEach((player) -> {
        try {
          player.updateInitiativeTable(this.initiative);
        }
        catch (RemoteException e) {
          throw new RuntimeException(e);
        }
      });
    }
    catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public void updateInitiative(InitWrapper initiative){
    this.initiative.remove(initiative);
    this.initiative.add(initiative);
    this.initiative.sort(InitWrapper::compareTo);
    try {
      dungeonMaster.updateInitiativeTable(this.initiative);
      players.forEach((player) -> {
        try {
          player.updateInitiativeTable(this.initiative);
        }
        catch (RemoteException e) {
          throw new RuntimeException(e);
        }
      });
    }
    catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public int getLobbyId()
  {
    return lobbyId;
  }

  public boolean isCombatStarted() {
    return isCombatStarted;
  }

  public IClientModel getDungeonMaster() {
    return dungeonMaster;
  }

  public void setDungeonMaster(IClientModel dungeonMaster) {
    this.dungeonMaster = dungeonMaster;
  }
}
package Server;

import Application.MVVM.Model.InitWrapper;
import Shared.IClientModel;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Lobby {
  private final int lobbyId;
  private final ArrayList<IClientModel> players;
  private final ArrayList<InitWrapper> initiative;
  private IClientModel dungeonMaster;

  public Lobby(int lobbyId, IClientModel dungeonMaster) {
    this.lobbyId = lobbyId;
    this.players = new ArrayList<>();
    this.initiative = new ArrayList<>();
    this.dungeonMaster = dungeonMaster;
    try {
      System.out.println(dungeonMaster.getUsername() + " created a new lobby [LobbyID: " + lobbyId + "]");
    }
    catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public void addPlayer(IClientModel client) {
    players.add(client);
    try {
      client.updateInitiativeTable(initiative);
      System.out.println(client.getUsername() + " joined lobby " + lobbyId);
    }
    catch (RemoteException e) {
      throw new RuntimeException(e);
    }
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

  public void removeMonster(Monster monster) {
    monsters.remove(monster);
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

  public void removePlayer(IClientModel client) {
    players.remove(client);
  }

  public int getLobbyId()
  {
    return lobbyId;
  }

  public IClientModel getDungeonMaster() {
    return dungeonMaster;
  }

  public void setDungeonMaster(IClientModel dungeonMaster) {
    this.dungeonMaster = dungeonMaster;
  }
}
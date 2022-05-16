package Server;

import Application.MVVM.Model.monster.Monster;
import Shared.IClientModel;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.UUID;

public class Lobby {
  private final int lobbyId;
  private final ArrayList<IClientModel> players;
  private final ArrayList<Monster> monsters;
  private IClientModel dungeonMaster;

  public Lobby(int lobbyId, IClientModel dungeonMaster) {
    this.lobbyId = lobbyId;
    this.players = new ArrayList<>();
    this.monsters = new ArrayList<>();
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
      System.out.println(client.getUsername() + " joined lobby " + lobbyId);
    }
    catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public void addMonster(Monster monster) {
    monsters.add(monster);
    try {
      dungeonMaster.updateMonsterTable(monsters);
      players.forEach((player) -> {
        try {
          player.updateMonsterTable(monsters);
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
      dungeonMaster.updateMonsterTable(monsters);
      players.forEach((player) -> {
        try {
          player.updateMonsterTable(monsters);
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
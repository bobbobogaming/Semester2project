package Server;

import Util.IClientModel;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Lobby {
  private final ArrayList<IClientModel> players;
  private IClientModel dungeonMaster;

  public Lobby(IClientModel dungeonMaster) {
    this.players = new ArrayList<>();
    this.dungeonMaster = dungeonMaster;
  }

  public void addPlayer(IClientModel client) {
    players.add(client);
    try {
      System.out.println(client.getUsername() + " was added to the lobby");
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  public void removePlayer(IClientModel client) {
    players.remove(client);
  }

  public IClientModel getDungeonMaster() {
    return dungeonMaster;
  }

  public void setDungeonMaster(IClientModel dungeonMaster) {
    this.dungeonMaster = dungeonMaster;
  }
}
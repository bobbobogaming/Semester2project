package Server;

import Shared.IClientModel;

import java.util.ArrayList;
import java.util.UUID;

public class Lobby {
  private final int lobbyId;
  private final ArrayList<IClientModel> players;
  private IClientModel dungeonMaster;

  public Lobby(int lobbyId, IClientModel dungeonMaster) {
    this.lobbyId = lobbyId;
    this.players = new ArrayList<>();
    this.dungeonMaster = dungeonMaster;
  }

  public void addPlayer(IClientModel client) {
    players.add(client);
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
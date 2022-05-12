package Application.MVVM.View.Lobby.Root;

import Application.Client.ClientLobby;
import Util.PropertyChangeSubject;

import java.beans.PropertyChangeListener;

public class LobbyViewModel implements PropertyChangeSubject
{
  private ClientLobby lobby;
  public LobbyViewModel(ClientLobby clientLobby)
  {
    this.lobby = clientLobby;
  }

  public void createLobby()
  {
    lobby.createLobby();
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    lobby.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    lobby.removePropertyChangeListener(listener);
  }

  public void onExit(){
    lobby.onExit();
  }

  public void joinLobby(int lobbyId) {
    lobby.connectToLobby(lobbyId);
  }
}

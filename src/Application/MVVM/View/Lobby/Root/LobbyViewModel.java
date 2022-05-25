package Application.MVVM.View.Lobby.Root;

import Application.Client.ClientLobby;
import Util.PropertyChangeSubject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class LobbyViewModel implements PropertyChangeSubject
{
  private final StringProperty lobbyErrorProperty;
  private final StringProperty lobbyIdTextProperty;
  private final ClientLobby lobby;
  public LobbyViewModel(ClientLobby clientLobby)
  {
    lobbyErrorProperty = new SimpleStringProperty("");
    lobbyIdTextProperty = new SimpleStringProperty("");
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
    boolean connected = lobby.connectToLobby(lobbyId);
    if (!connected) {
      lobbyErrorProperty.set("Could not connect to lobby");
    }
    else {
      lobbyErrorProperty.set("");
    }
  }

  public StringProperty lobbyErrorProperty() {
    return lobbyErrorProperty;
  }

  public StringProperty lobbyIdTextProperty() {
    return lobbyIdTextProperty;
  }
}

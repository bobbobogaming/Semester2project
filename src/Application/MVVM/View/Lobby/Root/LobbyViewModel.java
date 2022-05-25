package Application.MVVM.View.Lobby.Root;

import Application.Client.ClientLobby;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class LobbyViewModel
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

  public void onExit(){
    lobby.onExit();
  }

  public void joinLobby() {
    if (!lobbyIdTextProperty.get().isEmpty()){
      boolean connected = lobby.connectToLobby(Integer.parseInt(lobbyIdTextProperty.get()));
      if (!connected) {
        lobbyErrorProperty.set("Could not connect to lobby");
      }
      else {
        lobbyErrorProperty.set("");
      }
    }
  }

  public StringProperty lobbyErrorProperty() {
    return lobbyErrorProperty;
  }

  public StringProperty lobbyIdTextProperty() {
    return lobbyIdTextProperty;
  }
}

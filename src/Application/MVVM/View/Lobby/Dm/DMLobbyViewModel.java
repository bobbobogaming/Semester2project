package Application.MVVM.View.Lobby.Dm;

import Application.Client.Client;
import Application.Client.ClientLobby;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DMLobbyViewModel
{
  private StringProperty lobbyIdProperty;

  public ClientLobby client;

  public DMLobbyViewModel(Client clientRMI)
  {
    client = clientRMI;
    lobbyIdProperty = new SimpleStringProperty();
  }

  public StringProperty lobbyIdPropertyProperty()
  {
    return lobbyIdProperty;
  }

  public void setLobbyId(String lobbyId)
  {
    lobbyIdProperty.setValue("Lobby id: " + lobbyId);
  }
}

package Application.MVVM.View.Lobby.Dm;

import Application.Client.Client;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DMLobbyViewModel
{
  private StringProperty lobbyIdProperty;

  public Client client;

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
    lobbyIdProperty.setValue(lobbyId);
  }
}

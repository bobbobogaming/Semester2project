package Application.MVVM.View.Lobby.Player;

import Application.Client.Client;
import Application.Client.ClientLobby;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class PlayerLobbyViewModel implements PropertyChangeListener {

  private StringProperty lobbyIdProperty;
  private ListProperty<Monster> initList;

  public ClientLobby client; //TODO should be chanced to a more fitting interface

  public PlayerLobbyViewModel(Client client)
  {
    this.client = client;
    client.addPropertyChangeListener(this);
    lobbyIdProperty = new SimpleStringProperty();
    initList = new SimpleListProperty<>(
        FXCollections.observableArrayList(new ArrayList<>()));
  }

  public StringProperty lobbyIdProperty()
  {
    return lobbyIdProperty;
  }

  public void setLobbyId(String lobbyId)
  {
    lobbyIdProperty.setValue("Lobby id: " + lobbyId);
  }
}

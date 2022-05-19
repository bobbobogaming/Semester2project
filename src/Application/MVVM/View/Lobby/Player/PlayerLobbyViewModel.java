package Application.MVVM.View.Lobby.Player;

import Application.Client.Client;
import Application.Client.ClientLobby;
import Application.MVVM.Model.initWrapper.InitWrapper;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class PlayerLobbyViewModel implements PropertyChangeListener {

  private StringProperty lobbyIdProperty;
  private ListProperty<InitWrapper> initList;

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

  @Override public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("UpdateInitiativeTable")) {
      initList.clear();
      initList.addAll((ArrayList<InitWrapper>) evt.getNewValue());
    }
  }

  public ListProperty<InitWrapper> initListProperty() {
    return initList;
  }

  public void joinCombat() {
    client.joinCombatAsCharacter();
  }
}

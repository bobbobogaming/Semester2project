package Application.MVVM.View.Lobby.Player;

import Application.Client.Client;
import Application.Client.ClientLobby;
import Application.MVVM.Model.initWrapper.InitWrapper;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class PlayerLobbyViewModel implements PropertyChangeListener {

  private final StringProperty lobbyIdProperty;
  private final ListProperty<InitWrapper> initList;

  private final StringProperty lobbyStatusMessageText;
  private final ObjectProperty<Paint> lobbyStatusMessageColor;
  private final StringProperty joinCombatButtonText;

  public ClientLobby client; //TODO should be chanced to a more fitting interface

  public PlayerLobbyViewModel(Client client)
  {
    this.client = client;
    client.addPropertyChangeListener(this);
    lobbyIdProperty = new SimpleStringProperty();
    initList = new SimpleListProperty<>(
        FXCollections.observableArrayList(new ArrayList<>()));
    lobbyStatusMessageText = new SimpleStringProperty("");
    lobbyStatusMessageColor = new SimpleObjectProperty<>(Color.BLACK);
    joinCombatButtonText = new SimpleStringProperty("Join Combat");
  }

  public StringProperty lobbyIdProperty()
  {
    return lobbyIdProperty;
  }

  public StringProperty lobbyStatusMessageTextProperty() {
    return lobbyStatusMessageText;
  }

  public ObjectProperty<Paint> lobbyStatusMessageColorProperty() {
    return lobbyStatusMessageColor;
  }

  public StringProperty joinCombatButtonTextProperty() {
    return joinCombatButtonText;
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
    if (evt.getPropertyName().equals("joinCombatSuccess")) {
      lobbyStatusMessageColor.set(Color.GREEN);
      lobbyStatusMessageText.set("Joined as " + client.getUserID().getCurrentCharacter().getName());
      joinCombatButtonText.set("Leave Combat");

    }
    if (evt.getPropertyName().equals("leaveCombatSuccess")) {
      lobbyStatusMessageText.set("");
      joinCombatButtonText.set("Join Combat");
    }
    if (evt.getPropertyName().equals("joinCombatFailed")) {
      lobbyStatusMessageColor.set(Color.RED);
      lobbyStatusMessageText.set((String) evt.getNewValue());
    }

  }

  public ListProperty<InitWrapper> initListProperty() {
    return initList;
  }

  public void joinCombat() {
    client.joinCombatAsCharacter();
  }
}

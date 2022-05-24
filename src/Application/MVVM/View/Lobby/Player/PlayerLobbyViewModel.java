package Application.MVVM.View.Lobby.Player;

import Application.Client.Client;
import Application.Client.ClientLobby;
import Application.MVVM.Model.initWrapper.InitWrapper;
import javafx.application.Platform;
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
    Platform.runLater(()->{
      if (evt.getPropertyName().equals("UpdateInitiativeTable")) {
        initList.clear();
        initList.addAll((ArrayList<InitWrapper>) evt.getNewValue());
        if (client.getUserID().getCurrentCharacter() != null){
          boolean isInList = true;
          for (InitWrapper initWrapper : initList) {
            if (initWrapper.getName().equals(client.getUserID().getCurrentCharacter().getName())){
              isInList = false;
            }
          }
          if (isInList) {
            lobbyStatusMessageText.setValue("");
            joinCombatButtonText.setValue("Join Combat");
            client.getUserID().setInCombat(false);
          }
        }
      }
      else if (evt.getPropertyName().equals("joinCombatSuccess")) {
        lobbyStatusMessageColor.setValue(Color.GREEN);
        lobbyStatusMessageText.setValue("Joined as " + client.getUserID().getCurrentCharacter().getName());
        joinCombatButtonText.setValue("Leave Combat");

      }
      else if (evt.getPropertyName().equals("leaveCombatSuccess")) {
        lobbyStatusMessageText.setValue("");
        joinCombatButtonText.setValue("Join Combat");
      }
      else if (evt.getPropertyName().equals("joinCombatFailed")) {
        lobbyStatusMessageColor.setValue(Color.RED);
        lobbyStatusMessageText.setValue((String) evt.getNewValue());
      }
      else if (evt.getPropertyName().equals("combatStarted")) {
        lobbyStatusMessageColor.setValue(Color.BLACK);
        lobbyStatusMessageText.setValue("Ongoing combat");
      }
      else if (evt.getPropertyName().equals("combatEnded")) {
        lobbyStatusMessageColor.setValue(Color.BLACK);
        lobbyStatusMessageText.setValue("");
      }
    });
  }

  public ListProperty<InitWrapper> initListProperty() {
    return initList;
  }

  public void joinCombat() {
    client.joinCombatAsCharacter();
  }
}

package Application.MVVM.View.Lobby.Dm;

import Application.Client.Client;
import Application.Client.ClientLobby;
import Application.MVVM.Model.initWrapper.InitWrapper;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class DMLobbyViewModel implements PropertyChangeListener
{
  private StringProperty lobbyIdProperty;
  private ListProperty<InitWrapper> initList;

  private ClientLobby client; //TODO should be chanced to a more fitting interface
  private StringProperty combatLockProperty;

  public DMLobbyViewModel(Client client)
  {
    this.client = client;
    client.addPropertyChangeListener(this);
    lobbyIdProperty = new SimpleStringProperty();
    initList = new SimpleListProperty<>(
        FXCollections.observableArrayList(new ArrayList<>()));

    combatLockProperty = new SimpleStringProperty("Begin combat");
  }

  public StringProperty lobbyIdProperty()
  {
    return lobbyIdProperty;
  }

  public void setLobbyId(String lobbyId)
  {
    lobbyIdProperty.setValue("Lobby id: " + lobbyId);
  }


  public void openMonsterList() {
    client.getMonsters();
  }

  @Override public void propertyChange(PropertyChangeEvent evt) {
    Platform.runLater(() -> {
      if (evt.getPropertyName().equals("UpdateInitiativeTable")) {
        initList.clear();
        initList.addAll((ArrayList<InitWrapper>) evt.getNewValue());
      }
      if (evt.getPropertyName().equals("combatStarted")) {
        combatLockProperty.setValue("End combat");
      }
      if (evt.getPropertyName().equals("combatEnded")) {
        combatLockProperty.setValue("Start combat");
      }
    });
  }

  public ListProperty<InitWrapper> initListProperty() {
    return initList;
  }

  public void removeMonster(InitWrapper initWrapper) {
    client.removeInitiativeFromLobby(initWrapper);
  }

  public void lowerHealth(InitWrapper selectedItem,String amount) {
    int subtractionAmount = Integer.parseInt(amount);
    selectedItem.setHp(selectedItem.getHp() - subtractionAmount);
    client.updateInitList(selectedItem);
  }

  public void switchCombatState() {
    client.switchCombatState();
  }


  public StringProperty combatLockProperty() {
    return combatLockProperty;
  }
}

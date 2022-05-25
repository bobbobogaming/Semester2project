package Application.MVVM.View.Lobby.Dm;

import Application.Client.Client;
import Application.Client.ClientLobby;
import Application.MVVM.Model.initWrapper.InitWrapper;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class DMLobbyViewModel implements PropertyChangeListener
{
  private final StringProperty lobbyIdProperty;
  private final ListProperty<InitWrapper> initList;

  private final IntegerProperty indexProperty;
  private int localIndexCrossMethodeValue;
  private boolean fromLowerHealth;

  private final ClientLobby client; //TODO should be chanced to a more fitting interface
  private final StringProperty combatLockProperty;

  public DMLobbyViewModel(Client client)
  {
    this.client = client;
    client.addPropertyChangeListener(this);
    lobbyIdProperty = new SimpleStringProperty();
    initList = new SimpleListProperty<>(
        FXCollections.observableArrayList(new ArrayList<>()));

    combatLockProperty = new SimpleStringProperty("Begin combat");
    indexProperty = new SimpleIntegerProperty();
  }

  public StringProperty lobbyIdProperty()
  {
    return lobbyIdProperty;
  }

  public IntegerProperty indexProperty() {
    return indexProperty;
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
        if (fromLowerHealth){
          indexProperty.set(localIndexCrossMethodeValue);
          fromLowerHealth = false;
        }
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
    localIndexCrossMethodeValue = indexProperty.get();
    fromLowerHealth = true;
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

  public void bindBidirectionalIndexProperty(
      TableView.TableViewSelectionModel<InitWrapper> selectionModel){
    selectionModel.selectedIndexProperty().addListener((observableValue, oldIndex, newIndex) -> {
      if (newIndex.intValue() != indexProperty.get()) indexProperty.setValue(newIndex.intValue());
    });

    indexProperty.addListener((observableValue, oldNumber, newNumber) -> {
      if (selectionModel.getSelectedIndex() != newNumber.intValue()) selectionModel.clearAndSelect(newNumber.intValue());
    });
  }
}

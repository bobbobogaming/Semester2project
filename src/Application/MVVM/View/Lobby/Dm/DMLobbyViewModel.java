package Application.MVVM.View.Lobby.Dm;

import Application.Client.Client;
import Application.Client.ClientLobbyDM;
import Application.MVVM.Model.initWrapper.InitWrapper;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class DMLobbyViewModel implements PropertyChangeListener
{

  private int localIndexCrossMethodeValue;
  private boolean fromLowerHealth;

  private final ClientLobbyDM client;

  private final StringProperty lobbyIdProperty;
  private final ListProperty<InitWrapper> initList;
  private final IntegerProperty indexProperty;
  private final StringProperty combatLockProperty;
  private final BooleanProperty removeInitButtonDisableProperty;
  private final StringProperty statsInfoTextProperty;
  private final BooleanProperty statsInfoVisibleProperty;
  private final StringProperty subtractHealthTextProperty;
  private final BooleanProperty subtractHealthPaneVisibleProperty;

  public DMLobbyViewModel(Client client)
  {
    this.client = client;
    client.addPropertyChangeListener(this);
    lobbyIdProperty = new SimpleStringProperty();
    initList = new SimpleListProperty<>(
        FXCollections.observableArrayList(new ArrayList<>()));

    combatLockProperty = new SimpleStringProperty("Begin combat");
    indexProperty = new SimpleIntegerProperty();
    removeInitButtonDisableProperty = new SimpleBooleanProperty(false);
    statsInfoTextProperty = new SimpleStringProperty();
    statsInfoVisibleProperty = new SimpleBooleanProperty(false);
    subtractHealthPaneVisibleProperty = new SimpleBooleanProperty(false);
    subtractHealthTextProperty = new SimpleStringProperty("");
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

  public void removeInitiative(InitWrapper initWrapper) {
    client.removeInitiativeFromLobby(initWrapper);
  }

  public void lowerHealth(InitWrapper selectedItem) {
    if (!subtractHealthTextProperty.get().isEmpty() && !subtractHealthTextProperty.get().equals("-")) {
      localIndexCrossMethodeValue = indexProperty.get();
      fromLowerHealth = true;
      int subtractionAmount = Integer.parseInt(subtractHealthTextProperty.get());
      selectedItem.setHp(selectedItem.getHp() - subtractionAmount);
      client.updateInitList(selectedItem);
    }
  }

  public void switchCombatState() {
    client.switchCombatState();
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

  public void onTableSelectionChanged(ObservableValue<? extends InitWrapper> observableValue, InitWrapper oldSelection, InitWrapper newSelection) {
    if (newSelection != null) {
      removeInitButtonDisableProperty.set(false);
      statsInfoTextProperty.set(newSelection.getFormattedStats());
      statsInfoVisibleProperty.set(true);
      subtractHealthPaneVisibleProperty.set(true);
    } else {
      removeInitButtonDisableProperty.set(true);
      statsInfoVisibleProperty.set(false);
      subtractHealthPaneVisibleProperty.set(false);
    }
  }

  public StringProperty lobbyIdProperty()
  {
    return lobbyIdProperty;
  }

  public ListProperty<InitWrapper> initListProperty() {
    return initList;
  }

  public StringProperty combatLockProperty() {
    return combatLockProperty;
  }

  public BooleanProperty removeInitButtonDisableProperty() {
    return removeInitButtonDisableProperty;
  }

  public StringProperty statsInfoTextProperty() {
    return statsInfoTextProperty;
  }

  public BooleanProperty statsInfoVisibleProperty() {
    return statsInfoVisibleProperty;
  }

  public BooleanProperty subtractHealthPaneVisibleProperty() {
    return subtractHealthPaneVisibleProperty;
  }

  public StringProperty subtractHealthTextProperty() {
    return subtractHealthTextProperty;
  }
}

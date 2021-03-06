package Application.MVVM.View.TabPane;

import Application.Client.UserID;
import Application.MVVM.Core.ViewModelFactory;
import Application.MVVM.View.Lobby.Dm.DMLobbyViewController;
import Application.MVVM.View.Lobby.Dm.charactersheet.DMCharacterSheetViewController;
import Application.MVVM.View.Lobby.Dm.charactersheet.DMCharacterSheetViewFactory;
import Application.MVVM.View.Lobby.Player.PlayerLobbyViewController;
import Util.PropertyChangeSubject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;

public class TabViewModel implements PropertyChangeListener,
    PropertyChangeSubject {

  private final ViewModelFactory viewModelFactory;

  private final ObjectProperty<Node> lobbyTabProperty;
  private final PropertyChangeSupport support;

  public TabViewModel(ViewModelFactory viewModelFactory,PropertyChangeSubject client) {
    this.viewModelFactory = viewModelFactory;
    lobbyTabProperty = new SimpleObjectProperty<>();

    support = new PropertyChangeSupport(this);
    client.addPropertyChangeListener(this);
  }

  public ObjectProperty<Node> lobbyTabProperty() {
    return lobbyTabProperty;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if (evt.getPropertyName().equals("connectAsDM")){
      setTabDmLobby(evt.getNewValue() + "");
    } else if (evt.getPropertyName().equals("connectAsPlayer")){
      setTabPlayerLobby(evt.getNewValue() + "");
    } else if (evt.getPropertyName().equals("generateCharacterViews")) {
      ArrayList<UserID> userIDS = (ArrayList<UserID>) evt.getNewValue();
      userIDS.forEach(this::addCharacterSheetTab);
    }else if (evt.getPropertyName().equals("clearCharacterViews")) {
      clearCharacterSheetTaps();
    }
  }

  private void addCharacterSheetTab(UserID userID) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(
        "/Application/MVVM/View/Lobby/Dm/charactersheet/DMCharacterSheetView.fxml"));
    try
    {
      Tab tab = new Tab();
      tab.setContent(loader.load());
      tab.setText(userID.getCurrentCharacter().getName());

      DMCharacterSheetViewController DMCharacterSheetViewController = loader.getController();
      DMCharacterSheetViewController.init(DMCharacterSheetViewFactory.getInstance()
          .getCharacterSheetViewModelInstance(userID));

      support.firePropertyChange("addCharacterSheetTabs",null, tab);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private void clearCharacterSheetTaps(){
    support.firePropertyChange("clearCharacterSheetTabs",null,null);
  }

  private void setTabPlayerLobby(String lobbyId) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(
        "/Application/MVVM/View/Lobby/Player/PlayerLobbyView.fxml"));
    try
    {
      lobbyTabProperty.setValue(loader.load());

      PlayerLobbyViewController playerLobbyViewController = loader.getController();
      playerLobbyViewController.init(viewModelFactory.getPlayerLobbyViewModel());
      viewModelFactory.getPlayerLobbyViewModel().setLobbyId(lobbyId);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private void setTabDmLobby(String lobbyId){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(
        "/Application/MVVM/View/Lobby/Dm/DMLobbyView.fxml"));
    try
    {
      lobbyTabProperty.setValue(loader.load());

      DMLobbyViewController dmLobbyViewController = loader.getController();
      dmLobbyViewController.init(viewModelFactory.getDmLobbyViewModel());
      viewModelFactory.getDmLobbyViewModel().setLobbyId(lobbyId);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener) {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener) {
    support.removePropertyChangeListener(listener);
  }
}

package Application.MVVM.View.TabPane;

import Application.MVVM.Core.ViewModelFactory;
import Application.MVVM.View.Lobby.Dm.DMLobbyViewController;
import Application.MVVM.View.Lobby.Player.PlayerLobbyViewController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class TabViewModel implements PropertyChangeListener {

  private final ViewModelFactory viewModelFactory;

  private ObjectProperty<Node> lobbyTabProperty;

  public TabViewModel(ViewModelFactory viewModelFactory) {
    this.viewModelFactory = viewModelFactory;
    lobbyTabProperty = new SimpleObjectProperty<>();
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
    }
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
      playerLobbyViewController.setLobbyId(lobbyId);
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
      dmLobbyViewController.setLobbyId(lobbyId);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}

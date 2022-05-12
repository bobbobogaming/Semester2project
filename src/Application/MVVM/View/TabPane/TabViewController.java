package Application.MVVM.View.TabPane;

import Application.MVVM.Core.ViewModelFactory;
import Application.MVVM.View.CharacterSheet.CharacterViewController;
import Application.MVVM.View.Lobby.Dm.DMLobbyViewController;
import Application.MVVM.View.Lobby.Root.LobbyViewController;
import Application.MVVM.View.Lobby.Root.LobbyViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class TabViewController implements PropertyChangeListener
{
  @FXML Tab tab1;
  @FXML Tab tab2;

  @FXML LobbyViewController lobbyViewController;
  @FXML CharacterViewController characterViewController;

  private ViewModelFactory viewModelFactory;

  public void init(ViewModelFactory viewModelFactory){
    this.viewModelFactory = viewModelFactory;
    LobbyViewModel lobbyViewModel = viewModelFactory.getLobbyViewModel();
    lobbyViewController.init(lobbyViewModel);
    lobbyViewModel.addPropertyChangeListener(this);
    characterViewController.init(viewModelFactory.getCharacterViewModel());
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if (evt.getPropertyName().equals("connectAsDM")){
      System.out.println(evt.getNewValue());
      setTabDmLobby(evt.getNewValue() + "");
    }
  }

  private void setTabDmLobby(String lobbyId){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(
        "/Application/MVVM/View/Lobby/Dm/DMLobbyView.fxml"));
    try
    {
      tab1.setContent(loader.load());

      DMLobbyViewController dmLobbyViewController = loader.getController();
      dmLobbyViewController.init(viewModelFactory.getDmLobbyViewModel());
      dmLobbyViewController.setLobbyId(lobbyId);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void onExit()
  {
    lobbyViewController.onExit();
  }
}

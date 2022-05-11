package Application.MVVM.View.TabPane;

import Application.MVVM.View.CharacterSheet.CharacterViewController;
import Application.MVVM.View.CharacterSheet.CharacterViewModel;
import Application.MVVM.View.Lobby.LobbyViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

public class TabViewController
{
  @FXML Tab tab2;

  @FXML LobbyViewController lobbyViewController;
  @FXML CharacterViewController characterViewController;

  public void init(CharacterViewModel characterViewModel){
    lobbyViewController.init();
    characterViewController.init(characterViewModel);
  }
}

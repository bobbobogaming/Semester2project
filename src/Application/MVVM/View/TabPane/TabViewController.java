package Application.MVVM.View.TabPane;

import Application.MVVM.View.CharacterSheet.CharacterViewController;
import Application.MVVM.View.CharacterSheet.CharacterViewModel;
import Application.MVVM.View.Lobby.Root.LobbyViewController;
import Application.MVVM.View.Lobby.Root.LobbyViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

public class TabViewController
{
  @FXML private Tab lobbyTab;
  @FXML private Tab characterTab;

  @FXML private LobbyViewController lobbyViewController;
  @FXML private CharacterViewController characterViewController;

  private TabViewModel viewModel;

  public void init(LobbyViewModel lobbyViewModel, CharacterViewModel characterViewModel,TabViewModel tabViewModel){
    viewModel = tabViewModel;

    lobbyViewController.init(lobbyViewModel);
    lobbyViewModel.addPropertyChangeListener(tabViewModel);
    characterViewController.init(characterViewModel);

    viewModel.lobbyTabProperty().setValue(lobbyTab.getContent());
    lobbyTab.contentProperty().bind(viewModel.lobbyTabProperty());
  }

  public void onExit()
  {
    lobbyViewController.onExit();
  }
}

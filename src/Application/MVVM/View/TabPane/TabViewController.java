package Application.MVVM.View.TabPane;

import Application.MVVM.View.CharacterSheet.CharacterViewController;
import Application.MVVM.View.CharacterSheet.CharacterViewModel;
import Application.MVVM.View.Lobby.Root.LobbyViewController;
import Application.MVVM.View.Lobby.Root.LobbyViewModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TabViewController implements PropertyChangeListener
{
  @FXML private TabPane tabPane;
  @FXML private Tab lobbyTab;
  @FXML private Tab characterTab;

  @FXML private LobbyViewController lobbyViewController;
  @FXML private CharacterViewController characterViewController;

  private TabViewModel viewModel;

  public void init(LobbyViewModel lobbyViewModel, CharacterViewModel characterViewModel,TabViewModel tabViewModel){
    viewModel = tabViewModel;
    viewModel.addPropertyChangeListener(this);

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

  @Override public void propertyChange(PropertyChangeEvent evt) {
    Platform.runLater(() -> {
      if (evt.getPropertyName().equals("addCharacterSheetTabs")) {
        tabPane.getTabs().add((Tab) evt.getNewValue());
      }
      else if (evt.getPropertyName().equals("clearCharacterSheetTabs")){
        tabPane.getTabs().remove(2,tabPane.getTabs().size());
      }
    });
  }
}

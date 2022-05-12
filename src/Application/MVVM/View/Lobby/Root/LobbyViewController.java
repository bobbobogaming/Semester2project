package Application.MVVM.View.Lobby.Root;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.beans.PropertyChangeEvent;
import java.io.IOException;

public class LobbyViewController
{
  @FXML TextField lobbyId;
  private LobbyViewModel lobbyViewModel;

  public void init(LobbyViewModel lobbyViewModel)
  {
    this.lobbyViewModel = lobbyViewModel;
  }

  public void createLobby(ActionEvent actionEvent)
  {
    lobbyViewModel.createLobby();
  }

  public void joinLobby(ActionEvent actionEvent)
  {

  }

  public void onExit(){
    lobbyViewModel.onExit();
  }
}

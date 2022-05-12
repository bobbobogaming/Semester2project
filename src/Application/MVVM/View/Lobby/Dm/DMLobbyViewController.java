package Application.MVVM.View.Lobby.Dm;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DMLobbyViewController
{
  @FXML Label lobbyId;

  private DMLobbyViewModel viewModel;

  public void init(DMLobbyViewModel dmLobbyViewModel){
    viewModel = dmLobbyViewModel;

    lobbyId.textProperty().bind(viewModel.lobbyIdPropertyProperty());
  }

  public void setLobbyId(String lobbyId){
    viewModel.setLobbyId(lobbyId);
  }
}

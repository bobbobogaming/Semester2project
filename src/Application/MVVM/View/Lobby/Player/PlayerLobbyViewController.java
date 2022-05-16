package Application.MVVM.View.Lobby.Player;

import Application.MVVM.Model.monster.Monster;
import Application.MVVM.View.Lobby.Dm.DMLobbyViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PlayerLobbyViewController {

  @FXML private Label lobbyId;

  private PlayerLobbyViewModel viewModel;

  public void init(PlayerLobbyViewModel playerLobbyViewModel){
    viewModel = playerLobbyViewModel;
    lobbyId.textProperty().bind(viewModel.lobbyIdProperty());
    initList.itemsProperty().bind(viewModel.initListProperty());
    initList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("monsterName"));
  }

  public void setLobbyId(String lobbyId){
    viewModel.setLobbyId(lobbyId);
  }
}

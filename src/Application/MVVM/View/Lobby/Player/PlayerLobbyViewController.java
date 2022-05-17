package Application.MVVM.View.Lobby.Player;

import Application.MVVM.Model.InitWrapper;
import Application.MVVM.Model.monster.Monster;
import Application.MVVM.View.Lobby.Dm.DMLobbyViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PlayerLobbyViewController {

  @FXML private TableView<InitWrapper> initList;
  @FXML private Label lobbyId;

  private PlayerLobbyViewModel viewModel;

  public void init(PlayerLobbyViewModel playerLobbyViewModel){
    viewModel = playerLobbyViewModel;
    lobbyId.textProperty().bind(viewModel.lobbyIdProperty());
    initList.itemsProperty().bind(viewModel.initListProperty());
    initList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("init"));
    initList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
    initList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("hp"));
    initList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("ac"));
  }

  public void setLobbyId(String lobbyId){
    viewModel.setLobbyId(lobbyId);
  }
}

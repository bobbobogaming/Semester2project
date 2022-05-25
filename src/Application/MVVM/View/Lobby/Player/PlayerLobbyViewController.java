package Application.MVVM.View.Lobby.Player;

import Application.MVVM.Model.initWrapper.InitWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PlayerLobbyViewController {

  @FXML private Label lobbyStatusMessage;
  @FXML private TableView<InitWrapper> initList;
  @FXML private Label lobbyId;
  @FXML private Button joinCombatButton;

  private PlayerLobbyViewModel viewModel;

  public void init(PlayerLobbyViewModel playerLobbyViewModel){
    viewModel = playerLobbyViewModel;

    lobbyId.textProperty().bind(viewModel.lobbyIdProperty());
    initList.itemsProperty().bind(viewModel.initListProperty());
    initList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("init"));
    initList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
    initList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("hp"));
    initList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("ac"));

    lobbyStatusMessage.textProperty().bind(viewModel.lobbyStatusMessageTextProperty());
    lobbyStatusMessage.textFillProperty().bind(viewModel.lobbyStatusMessageColorProperty());
    joinCombatButton.textProperty().bind(viewModel.joinCombatButtonTextProperty());

    initList.setSelectionModel(null);
  }

  public void joinCombat(ActionEvent actionEvent) {
    viewModel.joinCombat();
  }
}

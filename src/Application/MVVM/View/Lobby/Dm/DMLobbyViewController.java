package Application.MVVM.View.Lobby.Dm;

import Application.MVVM.Model.monster.Monster;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class DMLobbyViewController
{
  @FXML private TableView<Monster> initList;
  @FXML private Label lobbyId;

  private DMLobbyViewModel viewModel;

  public void init(DMLobbyViewModel dmLobbyViewModel){
    viewModel = dmLobbyViewModel;

    lobbyId.textProperty().bind(viewModel.lobbyIdProperty());
    initList.itemsProperty().bind(viewModel.initListProperty());
    initList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("monsterName"));
  }

  public void setLobbyId(String lobbyId){
    viewModel.setLobbyId(lobbyId);
  }

  public void openMonsterList(ActionEvent actionEvent) {
    viewModel.openMonsterList();
  }

  public void onRemoveMonsterClick(ActionEvent actionEvent) {
    viewModel.removeMonster(initList.getSelectionModel().getSelectedItem());
  }
}

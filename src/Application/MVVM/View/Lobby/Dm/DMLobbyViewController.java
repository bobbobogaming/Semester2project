package Application.MVVM.View.Lobby.Dm;

import Application.MVVM.Model.InitWrapper;
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
  @FXML private TableView<InitWrapper> initList;
  @FXML private Label lobbyId;

  private DMLobbyViewModel viewModel;

  public void init(DMLobbyViewModel dmLobbyViewModel){
    viewModel = dmLobbyViewModel;

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

  public void openMonsterList(ActionEvent actionEvent) {
    viewModel.openMonsterList();
  }

  public void onRemoveMonsterClick(ActionEvent actionEvent) {
    viewModel.removeMonster(initList.getSelectionModel().getSelectedItem());
  }
}

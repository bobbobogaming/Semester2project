package Application.MVVM.View.Lobby.Dm;

import Application.MVVM.Model.initWrapper.InitWrapper;
import Util.textfieldfilter.NegativeNumberStrategy;
import Util.textfieldfilter.UnaryFilterContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class DMLobbyViewController
{
  @FXML private Button combatButton;
  @FXML private Button removeInitButton;
  @FXML private Text statsInfo;
  @FXML private TableView<InitWrapper> initList;
  @FXML private Label lobbyId;
  @FXML private TextField subtractHealth;
  @FXML private Pane subtractHealthPane;

  private DMLobbyViewModel viewModel;

  public void init(DMLobbyViewModel dmLobbyViewModel){
    viewModel = dmLobbyViewModel;

    combatButton.textProperty().bind(viewModel.combatLockProperty());
    lobbyId.textProperty().bind(viewModel.lobbyIdProperty());
    initList.itemsProperty().bind(viewModel.initListProperty());
    initList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("init"));
    initList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
    initList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("hp"));
    initList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("ac"));

    viewModel.bindBidirectionalIndexProperty(initList.getSelectionModel());

    removeInitButton.disableProperty().bind(viewModel.removeInitButtonDisableProperty());
    statsInfo.textProperty().bind(viewModel.statsInfoTextProperty());
    statsInfo.visibleProperty().bind(viewModel.statsInfoVisibleProperty());
    subtractHealthPane.visibleProperty().bind(
        viewModel.subtractHealthPaneVisibleProperty());

    subtractHealth.textProperty().bindBidirectional(viewModel.subtractHealthTextProperty());

    subtractHealth.setTextFormatter(new TextFormatter<>(new UnaryFilterContext(new NegativeNumberStrategy(8))));

    initList.getSelectionModel().selectedItemProperty().addListener(viewModel::onTableSelectionChanged);
  }

  public void setLobbyId(String lobbyId){
    viewModel.setLobbyId(lobbyId);
  }

  public void onLowerHealth(ActionEvent actionEvent){
    viewModel.lowerHealth(initList.getSelectionModel().getSelectedItem());
  }

  public void openMonsterList(ActionEvent actionEvent) {
    viewModel.openMonsterList();
  }

  public void onRemoveMonsterClick(ActionEvent actionEvent) {
    viewModel.removeInitiative(initList.getSelectionModel().getSelectedItem());
  }

  public void onStartCombat(ActionEvent actionEvent) {
    viewModel.switchCombatState();
  }
}

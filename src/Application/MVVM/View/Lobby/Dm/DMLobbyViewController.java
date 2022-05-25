package Application.MVVM.View.Lobby.Dm;

import Application.MVVM.Model.initWrapper.InitWrapper;
import Util.textfieldfilter.NegativeNumberStrategy;
import Util.textfieldfilter.UnaryFilterContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class DMLobbyViewController
{
  @FXML private Button combatButton;
  @FXML private Label subtractHealthLabel;
  @FXML private Button proceedButton;
  @FXML private Button removeMonsterButton;
  @FXML private Text textArea;
  @FXML private TableView<InitWrapper> initList;
  @FXML private Label lobbyId;
  @FXML private TextField subtractHealth;

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

    initList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
      if (newSelection != null) {
        removeMonsterButton.setDisable(false);
        textArea.setText(newSelection.getFormattedStats());
        textArea.setVisible(true);
        subtractHealth.setVisible(true);
        proceedButton.setVisible(true);
        subtractHealthLabel.setVisible(true);
      } else {
        removeMonsterButton.setDisable(true);
        textArea.setVisible(false);
        subtractHealth.setVisible(false);
        proceedButton.setVisible(false);
        subtractHealthLabel.setVisible(false);
      }
    });

    textArea.setVisible(false);
    subtractHealth.setVisible(false);
    proceedButton.setVisible(false);
    subtractHealthLabel.setVisible(false);

    subtractHealth.setTextFormatter(new TextFormatter<>(new UnaryFilterContext(new NegativeNumberStrategy(8))));
  }

  public void setLobbyId(String lobbyId){
    viewModel.setLobbyId(lobbyId);
  }

  public void onLowerHealth(ActionEvent actionEvent){
    if (!subtractHealth.getText().isEmpty() && !subtractHealth.getText().equals("-")){
      //int index = initList.getSelectionModel().selectedIndexProperty().get();
      viewModel.lowerHealth(initList.getSelectionModel().getSelectedItem(),subtractHealth.getText());
      //initList.getSelectionModel().clearAndSelect(index);
    }
  }

  public void openMonsterList(ActionEvent actionEvent) {
    viewModel.openMonsterList();
  }

  public void onRemoveMonsterClick(ActionEvent actionEvent) {
    viewModel.removeMonster(initList.getSelectionModel().getSelectedItem());
  }

  public void onStartCombat(ActionEvent actionEvent) {
    viewModel.switchCombatState();
  }
}

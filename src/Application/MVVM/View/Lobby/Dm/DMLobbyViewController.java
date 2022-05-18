package Application.MVVM.View.Lobby.Dm;

import Application.MVVM.Model.initWrapper.InitWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class DMLobbyViewController
{
  @FXML private Label subtractHealthLabel;
  @FXML private Button proceedButton;
  @FXML private Button removeMonsterButton;
  @FXML private Text textArea;
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

    DecimalFormat format = new DecimalFormat("#");
    UnaryOperator<TextFormatter.Change> filter = c -> {
      if (c.getControlNewText().isEmpty()){
        return c;
      }

      if (c.getControlNewText().length() > 8){
        return null;
      }

      if (c.getControlNewText().equals("-")){
        return c;
      }

      ParsePosition parsePosition = new ParsePosition(0);
      Object object = format.parse(c.getControlNewText(),parsePosition);

      if ((object == null) || ((parsePosition.getIndex()) < (c.getControlNewText().length()))){
        return null;
      } else {
        return c;
      }
    };
    subtractHealth.setTextFormatter(new TextFormatter<>(filter));
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

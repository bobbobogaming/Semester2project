package Application.MVVM.View.Lobby.Dm.MonsterSearch;

import Application.MVVM.Model.monster.Monster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SelectMonsterViewController {

  @FXML private TextField searchBar;
  @FXML private ListView<Monster> monsterList;

  private SelectMonsterViewModel selectMonsterViewModel;

  public void init(SelectMonsterViewModel selectMonsterViewModel, ArrayList<Monster> monsters) {
    this.selectMonsterViewModel = selectMonsterViewModel;
    selectMonsterViewModel.setMonsterListItems(monsters);
    monsterList.itemsProperty().bind(selectMonsterViewModel.getMonsterListProperty());
  }

  public void onSearchBarKeyTyped(KeyEvent keyEvent) {
    selectMonsterViewModel.onSearchBarKeyTyped(searchBar.getText());
  }

  public void onButtonAddMonster(ActionEvent actionEvent) {
    if (monsterList.getSelectionModel().getSelectedItem() != null) {
      selectMonsterViewModel.addMonster(monsterList.getSelectionModel().getSelectedItem());
    }
    ((Stage) monsterList.getScene().getWindow()).close();
  }

  public void onSearchBarEnterPressed(ActionEvent actionEvent) {
    if (!monsterList.getItems().isEmpty()) {
      monsterList.getSelectionModel().select(0);
      onButtonAddMonster(null);
    }
  }

  public void onMonsterListEnterKeyPressed(KeyEvent keyEvent) {
    if (keyEvent.getCode().equals(KeyCode.ENTER)) {
      onButtonAddMonster(null);
    }
  }
}

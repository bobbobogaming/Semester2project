package Application.MVVM.View.Lobby.Dm.MonsterSearch;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SelectMonsterViewController {

  @FXML private TextField searchBar;
  @FXML private ListView<String> monsterList;

  private SelectMonsterViewModel selectMonsterViewModel;

  public void init(SelectMonsterViewModel selectMonsterViewModel, ArrayList<String> monsters) {
    this.selectMonsterViewModel = selectMonsterViewModel;
    selectMonsterViewModel.setMonsterListItems(monsters);
    monsterList.itemsProperty().bind(selectMonsterViewModel.getMonsterListProperty());
  }


  public void onSearchBarKeyTyped(KeyEvent keyEvent) {
    selectMonsterViewModel.onSearchBarKeyTyped(searchBar.getText());
  }
}

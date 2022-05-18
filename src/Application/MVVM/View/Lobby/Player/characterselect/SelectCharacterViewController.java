package Application.MVVM.View.Lobby.Player.characterselect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SelectCharacterViewController {
  @FXML private TextField searchBar;
  @FXML private ListView<Character> characterList;

  private SelectCharacterViewModel selectCharacterViewModel;

  public void init(SelectCharacterViewModel selectCharacterViewModel, ArrayList<Character> characters) {
    this.selectCharacterViewModel = selectCharacterViewModel;
  }

  public void onSearchBarKeyTyped(KeyEvent keyEvent) {
    selectCharacterViewModel.onSearchBarKeyTyped(searchBar.getText());
  }

  public void onSearchBarEnterPressed(ActionEvent actionEvent) {
    if (!characterList.getItems().isEmpty()) {
      characterList.getSelectionModel().select(0);
      onButtonChooseCharacter(null);
    }
  }

  public void onCharacterListEnterKeyPressed(KeyEvent keyEvent) {
    if (keyEvent.getCode().equals(KeyCode.ENTER)) {
      onButtonChooseCharacter(null);
    }
  }

  public void onButtonChooseCharacter(ActionEvent actionEvent) {
    if (characterList.getSelectionModel().getSelectedItem() != null) {
      //selectCharacterViewModel.chooseCharacter(characterList.getSelectionModel().getSelectedItem());
    }

    ((Stage) characterList.getScene().getWindow()).close();
  }
}

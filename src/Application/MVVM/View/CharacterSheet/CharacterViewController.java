package Application.MVVM.View.CharacterSheet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class CharacterViewController
{
  @FXML Label strMod;
  @FXML TextField strField;
  @FXML Label dexMod;
  @FXML TextField dexField;
  @FXML Label conMod;
  @FXML TextField conField;
  @FXML Label intMod;
  @FXML TextField intField;
  @FXML Label wisMod;
  @FXML TextField wisField;
  @FXML Label charMod;
  @FXML TextField charField;

  private CharacterViewModel viewModel;

  public void init(CharacterViewModel characterViewModel){
    viewModel = characterViewModel;

    strMod.textProperty().bind(viewModel.strModProperty());
    dexMod.textProperty().bind(viewModel.dexModProperty());
    conMod.textProperty().bind(viewModel.conModProperty());
    intMod.textProperty().bind(viewModel.intModProperty());
    wisMod.textProperty().bind(viewModel.wisModProperty());
    charMod.textProperty().bind(viewModel.charModProperty());
  }

  public void onStatEnter(ActionEvent actionEvent)
  {
    viewModel.setStat((TextField) actionEvent.getSource());
  }

  public void onStatInput(KeyEvent keyEvent)
  {
    if (!keyEvent.getCharacter().matches("\\d"))
      ((TextField)keyEvent.getSource()).setText(((TextField)keyEvent.getSource()).getText().replaceAll("[^\\d]",""));
  }
}

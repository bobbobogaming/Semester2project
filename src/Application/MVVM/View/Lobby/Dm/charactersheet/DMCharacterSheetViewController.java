package Application.MVVM.View.Lobby.Dm.charactersheet;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DMCharacterSheetViewController {

  @FXML private Label playerName;
  @FXML private TextField nameField;
  @FXML private TextField classField;
  @FXML private TextField levelField;
  @FXML private TextField maxHp;
  @FXML private Label strMod;
  @FXML private TextField strField;
  @FXML private Label dexMod;
  @FXML private TextField dexField;
  @FXML private Label conMod;
  @FXML private TextField conField;
  @FXML private Label intMod;
  @FXML private TextField intField;
  @FXML private Label wisMod;
  @FXML private TextField wisField;
  @FXML private Label charMod;
  @FXML private TextField charField;

  private DMCharacterSheetViewModel viewModel;

  public void init(DMCharacterSheetViewModel characterSheetViewModel){
    viewModel = characterSheetViewModel;

    playerName.textProperty().bind(viewModel.playerNameProperty());
    nameField.textProperty().bind(viewModel.characterNameProperty());
    classField.textProperty().bind(viewModel.characterClassProperty());
    levelField.textProperty().bind(viewModel.levelProperty());
    maxHp.textProperty().bind(viewModel.maxHpProperty());
    strField.textProperty().bind(viewModel.strengthProperty());
    strMod.textProperty().bind(viewModel.strengthModProperty());
    dexField.textProperty().bind(viewModel.dexterityProperty());
    dexMod.textProperty().bind(viewModel.dexterityModProperty());
    conField.textProperty().bind(viewModel.constitutionProperty());
    conMod.textProperty().bind(viewModel.constitutionModProperty());
    intField.textProperty().bind(viewModel.intelligenceProperty());
    intMod.textProperty().bind(viewModel.intelligenceModProperty());
    wisField.textProperty().bind(viewModel.wisdomProperty());
    wisMod.textProperty().bind(viewModel.wisdomModProperty());
    charField.textProperty().bind(viewModel.charismaProperty());
    charMod.textProperty().bind(viewModel.charismaModProperty());
  }
}

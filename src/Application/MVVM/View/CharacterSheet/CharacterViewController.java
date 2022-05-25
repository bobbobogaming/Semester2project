package Application.MVVM.View.CharacterSheet;

import Application.MVVM.Model.character.Character;
import Util.textfieldfilter.PosetiveNumberStrategy;
import Util.textfieldfilter.UnaryFilterContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.function.UnaryOperator;

public class CharacterViewController
{
  @FXML private Pane characterInfo;
  @FXML private ListView<Character> characterList;
  @FXML private Button removeCharacterButton;
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
  @FXML private Button playAsCharacter;
  @FXML private Label saveStatus;

  private CharacterViewModel viewModel;

  public void init(CharacterViewModel characterViewModel){
    viewModel = characterViewModel;

    nameField.textProperty().bindBidirectional(viewModel.characterNameProperty());
    classField.textProperty().bindBidirectional(viewModel.characterClassProperty());
    levelField.textProperty().bindBidirectional(viewModel.levelProperty());
    maxHp.textProperty().bindBidirectional(viewModel.maxHpProperty());
    strField.textProperty().bindBidirectional(viewModel.strengthProperty());
    strMod.textProperty().bind(viewModel.strengthModProperty());
    dexField.textProperty().bindBidirectional(viewModel.dexterityProperty());
    dexMod.textProperty().bind(viewModel.dexterityModProperty());
    conField.textProperty().bindBidirectional(viewModel.constitutionProperty());
    conMod.textProperty().bind(viewModel.constitutionModProperty());
    intField.textProperty().bindBidirectional(viewModel.intelligenceProperty());
    intMod.textProperty().bind(viewModel.intelligenceModProperty());
    wisField.textProperty().bindBidirectional(viewModel.wisdomProperty());
    wisMod.textProperty().bind(viewModel.wisdomModProperty());
    charField.textProperty().bindBidirectional(viewModel.charismaProperty());
    charMod.textProperty().bind(viewModel.charismaModProperty());

    saveStatus.textProperty().bind(viewModel.saveStatusTextProperty());
    saveStatus.textFillProperty().bind(viewModel.saveStatusColorProperty());

    characterList.itemsProperty().bind(viewModel.charactersProperty());

    playAsCharacter.disableProperty().bind(
        viewModel.isPlayAsCharacterDisabledProperty());
    playAsCharacter.textProperty().bind(viewModel.playAsCharacterTextProperty());
    characterInfo.setVisible(false);
    playAsCharacter.setVisible(false);

    viewModel.bindBidirectionalIndexProperty(characterList.getSelectionModel());

    characterList.getSelectionModel().selectedItemProperty().addListener((obs,oldValue,newValue) -> {
      if (newValue != null){
        removeCharacterButton.setDisable(false);
        viewModel.updatePlayAsCharacterButton(newValue);
        characterInfo.setVisible(true);
        viewModel.updateCharacterInfo(newValue);
        playAsCharacter.setVisible(true);
      } else {
        removeCharacterButton.setDisable(true);
        characterInfo.setVisible(false);
        playAsCharacter.setVisible(false);
      }
    });

    UnaryOperator<TextFormatter.Change> filter = new UnaryFilterContext(new PosetiveNumberStrategy(2));

    strField.setTextFormatter(new TextFormatter<>(filter));
    dexField.setTextFormatter(new TextFormatter<>(filter));
    conField.setTextFormatter(new TextFormatter<>(filter));
    intField.setTextFormatter(new TextFormatter<>(filter));
    wisField.setTextFormatter(new TextFormatter<>(filter));
    charField.setTextFormatter(new TextFormatter<>(filter));
    levelField.setTextFormatter(new TextFormatter<>(new UnaryFilterContext(new PosetiveNumberStrategy(2))));
    maxHp.setTextFormatter(new TextFormatter<>(new UnaryFilterContext(new PosetiveNumberStrategy(4))));
  }

  public void onSaveCharacterButton(ActionEvent actionEvent)
  {
    if (characterList.getSelectionModel().getSelectedItem() == null) {
      viewModel.createCharacterSheet();
    } else {
      //viewModel.saveCharacterSheet(); Implementeres aldrig
    }
  }

  public void onCreateCharacterButton(ActionEvent actionEvent) {
    //characterList.getSelectionModel().select(-1);
    viewModel.clearCharacterInfo();
    characterInfo.setVisible(true);
    //playAsCharacter.setVisible(false);
  }

  public void onRemoveCharacterButton(ActionEvent actionEvent) {
    if (characterList.getSelectionModel().getSelectedItem() != null){
      viewModel.removeCharacter(characterList.getSelectionModel().getSelectedItem());
    }
  }

  public void onPlayAsCharacterButton(ActionEvent actionEvent) {
    if (characterList.getSelectionModel().getSelectedItem() != null) {
      viewModel.playAsCharacter(characterList.getSelectionModel().getSelectedItem());
      viewModel.updatePlayAsCharacterButton(characterList.getSelectionModel().getSelectedItem());
    }
  }

  public void testMethod(KeyEvent keyEvent) {
    if (keyEvent.getCode().equals(KeyCode.F)) {
      System.out.println(characterList.getSelectionModel().getSelectedIndex());
      System.out.println(characterList.getSelectionModel().getSelectedItem());
    }
  }
}

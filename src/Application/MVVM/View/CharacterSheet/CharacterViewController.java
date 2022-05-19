package Application.MVVM.View.CharacterSheet;

import Application.MVVM.Model.character.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.function.UnaryOperator;

public class CharacterViewController
{
  @FXML private Pane characterInfo;
  @FXML private ListView<Character> characterList;
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
  @FXML private TextField nameField;
  @FXML private TextField maxHp;

  private CharacterViewModel viewModel;

  public void init(CharacterViewModel characterViewModel){
    viewModel = characterViewModel;

    nameField.textProperty().bindBidirectional(viewModel.characterNameProperty());
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

    characterList.itemsProperty().bind(viewModel.charactersProperty());

    characterInfo.setVisible(false);

    characterList.getSelectionModel().selectedItemProperty().addListener((obs,oldValue,newValue) -> {
      if (newValue != null){
        characterInfo.setVisible(true);
        viewModel.updateCharacterInfo(newValue);
      } else {
        characterInfo.setVisible(false);
      }
    });

    DecimalFormat format = new DecimalFormat("#");
    UnaryOperator<TextFormatter.Change> filter = c -> {
      if (c.getControlNewText().isEmpty()){
        return c;
      }

      if (c.getControlNewText().length() > 2){
        return null;
      }

      ParsePosition parsePosition = new ParsePosition(0);
      Object object = format.parse(c.getControlNewText(),parsePosition);

      if ((object == null) || ((parsePosition.getIndex()) < (c.getControlNewText().length()))){
        return null;
      } else {
        return c;
      }
    };

    UnaryOperator<TextFormatter.Change> filter2 = c -> {
      if (c.getControlNewText().isEmpty()){
        return c;
      }

      if (c.getControlNewText().length() > 4){
        return null;
      }

      ParsePosition parsePosition = new ParsePosition(0);
      Object object = format.parse(c.getControlNewText(),parsePosition);

      if ((object == null) || ((parsePosition.getIndex()) < (c.getControlNewText().length()))){
        return null;
      } else {
        return c;
      }
    };

    strField.setTextFormatter(new TextFormatter<>(filter));
    dexField.setTextFormatter(new TextFormatter<>(filter));
    conField.setTextFormatter(new TextFormatter<>(filter));
    intField.setTextFormatter(new TextFormatter<>(filter));
    wisField.setTextFormatter(new TextFormatter<>(filter));
    charField.setTextFormatter(new TextFormatter<>(filter));
    maxHp.setTextFormatter(new TextFormatter<>(filter2));
  }

  public void onSaveCharacterButton(ActionEvent actionEvent)
  {
    if (characterList.getSelectionModel().getSelectedItem() == null) {
      viewModel.createCharacterSheet(
          nameField.getText(),
          intField.getText(),
          dexField.getText(),
          conField.getText(),
          intField.getText(),
          wisField.getText(),
          charField.getText(),
          maxHp.getText());
      //todo fix den her lorte løsning senere, måske eller bare lad være
      for (int i = 0; i < characterList.getItems().size()-1; i++) {
        if (characterList.getItems().get(i).getName().equals(nameField.getText())) {
          characterList.getSelectionModel().select(i);
        break;
        }
      }
    }
    else {
      //viewModel.saveCharacterSheet(); Implementeres senere
    }
  }

  public void onCreateCharacterButton(ActionEvent actionEvent) {
    characterList.getSelectionModel().select(-1);
    viewModel.clearCharacterInfo();
    characterInfo.setVisible(true);
  }

  public void onRemoveCharacterButton(ActionEvent actionEvent) {
  }

  public void onPlayAsCharacterButton(ActionEvent actionEvent) {
    if (characterList.getSelectionModel().getSelectedItem() != null) {
      viewModel.playAsCharacter(characterList.getSelectionModel().getSelectedItem());
    }
  }
}

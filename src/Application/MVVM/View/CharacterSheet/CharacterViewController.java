package Application.MVVM.View.CharacterSheet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.function.UnaryOperator;

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

  @FXML TextField nameField;

  private CharacterViewModel viewModel;

  public void init(CharacterViewModel characterViewModel){
    viewModel = characterViewModel;

    strMod.textProperty().bind(viewModel.strModProperty());
    dexMod.textProperty().bind(viewModel.dexModProperty());
    conMod.textProperty().bind(viewModel.conModProperty());
    intMod.textProperty().bind(viewModel.intModProperty());
    wisMod.textProperty().bind(viewModel.wisModProperty());
    charMod.textProperty().bind(viewModel.charModProperty());

    DecimalFormat format = new DecimalFormat("#");
    UnaryOperator<TextFormatter.Change> filter = c -> {
      if (c.getControlNewText().isEmpty()){
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

    strField.setTextFormatter(new TextFormatter<>(filter));
    dexField.setTextFormatter(new TextFormatter<>(filter));
    conField.setTextFormatter(new TextFormatter<>(filter));
    intField.setTextFormatter(new TextFormatter<>(filter));
    wisField.setTextFormatter(new TextFormatter<>(filter));
    charField.setTextFormatter(new TextFormatter<>(filter));
  }

  public void onStatEnter(ActionEvent actionEvent)
  {
    viewModel.setStat((TextField) actionEvent.getSource());
  }

  public void onCreate(ActionEvent actionEvent)
  {
    viewModel.createCharacterSheet(nameField.getText(),intField.getText(),dexField.getText(),conField.getText(),intField.getText(),wisField.getText(),charField.getText());
  }
}

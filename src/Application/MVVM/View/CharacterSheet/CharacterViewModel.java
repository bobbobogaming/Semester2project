package Application.MVVM.View.CharacterSheet;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;

public class CharacterViewModel
{
  private StringProperty strMod;
  private StringProperty dexMod;
  private StringProperty conMod;
  private StringProperty intMod;
  private StringProperty wisMod;
  private StringProperty charMod;

  public CharacterViewModel()
  {
    strMod = new SimpleStringProperty();
    dexMod = new SimpleStringProperty();
    conMod = new SimpleStringProperty();
    intMod = new SimpleStringProperty();
    wisMod = new SimpleStringProperty();
    charMod = new SimpleStringProperty();
  }

  public StringProperty strModProperty()
  {
    return strMod;
  }

  public StringProperty dexModProperty()
  {
    return dexMod;
  }

  public StringProperty conModProperty()
  {
    return conMod;
  }

  public StringProperty intModProperty()
  {
    return intMod;
  }

  public StringProperty wisModProperty()
  {
    return wisMod;
  }

  public StringProperty charModProperty()
  {
    return charMod;
  }

  public void setStat(TextField source)
  {
    switch (source.getId()){
      case "strField" ->
          strMod.setValue((Integer.parseInt(source.getText())-10)/2 + "");
      case "dexField" ->
          dexMod.setValue((Integer.parseInt(source.getText())-10)/2 + "");
      case "conField" ->
          conMod.setValue((Integer.parseInt(source.getText())-10)/2 + "");
      case "intField" ->
          intMod.setValue((Integer.parseInt(source.getText())-10)/2 + "");
      case "wisField" ->
          wisMod.setValue((Integer.parseInt(source.getText())-10)/2 + "");
      case "charField" ->
          charMod.setValue((Integer.parseInt(source.getText())-10)/2 + "");
    }

    System.out.println(source.getId());
  }
}

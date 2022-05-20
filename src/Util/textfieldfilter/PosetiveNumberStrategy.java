package Util.textfieldfilter;

import javafx.scene.control.TextFormatter;

import java.text.DecimalFormat;
import java.text.ParsePosition;

public class PosetiveNumberStrategy implements FilterStrategy {

  private int length;
  private DecimalFormat format;

  public PosetiveNumberStrategy(int length) {
    this.length = length;
    format = DecimalFormatSingletonFactory.getInstance().getFormat();
  }

  @Override public TextFormatter.Change filter(TextFormatter.Change change) {
    if (change.getControlNewText().isEmpty()){
      return change;
    }

    if (change.getControlNewText().length() > length){
      return null;
    }

    ParsePosition parsePosition = new ParsePosition(0);
    Object object = format.parse(change.getControlNewText(),parsePosition);

    if ((object == null) || ((parsePosition.getIndex()) < (change.getControlNewText().length()))){
      return null;
    } else {
      return change;
    }
  }
}

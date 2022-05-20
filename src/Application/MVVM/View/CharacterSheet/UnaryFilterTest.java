package Application.MVVM.View.CharacterSheet;

import javafx.scene.control.TextFormatter;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class UnaryFilterTest implements UnaryOperator<TextFormatter.Change> {

  private final DecimalFormat format;

  private int length;

  public UnaryFilterTest(int length) {
    this.length = length;
    format = new DecimalFormat("#");
  }

  @Override public TextFormatter.Change apply(TextFormatter.Change change) {
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

  @Override public <V> Function<V, TextFormatter.Change> compose(
      Function<? super V, ? extends TextFormatter.Change> before) {
    return UnaryOperator.super.compose(before);
  }

  @Override public <V> Function<TextFormatter.Change, V> andThen(
      Function<? super TextFormatter.Change, ? extends V> after) {
    return UnaryOperator.super.andThen(after);
  }
}

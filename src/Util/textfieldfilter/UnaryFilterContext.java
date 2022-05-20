package Util.textfieldfilter;

import javafx.scene.control.TextFormatter;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class UnaryFilterContext implements UnaryOperator<TextFormatter.Change> {
  private FilterStrategy strategy;

  public UnaryFilterContext(FilterStrategy strategy){
    this.strategy = strategy;
  }

  @Override public TextFormatter.Change apply(TextFormatter.Change change) {
    return strategy.filter(change);
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

package Util.textfieldfilter;

import javafx.scene.control.TextFormatter;

public interface FilterStrategy {
  TextFormatter.Change filter(TextFormatter.Change change);
}

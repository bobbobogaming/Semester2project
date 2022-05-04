package Application;

import Application.MVVM.Core.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class DndApp extends Application
{
  @Override public void start(Stage stage) throws Exception
  {
    ViewHandler viewHandler = ViewHandler.getInstance(stage);
    viewHandler.start("test");
  }
}
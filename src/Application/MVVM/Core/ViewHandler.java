package Application.MVVM.Core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ViewHandler
{
  private Scene currentScene;
  private Stage currentStage;
  private ViewModelFactory viewModelFactory;
  private static ViewHandler instance;
  private static Lock lock = new ReentrantLock();

  public static ViewHandler getInstance(Stage stage)

  {
    if (instance == null){
      synchronized (lock){
        if (instance == null) instance = new ViewHandler(stage,
            ViewModelFactory.getInstance());
      }
    }
    return instance;
  }

  private ViewHandler(Stage stage, ViewModelFactory viewModelFactory)
  {
    currentStage = stage;
    this.viewModelFactory = viewModelFactory;

  }

  public void start(String viewToOpen) throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    Parent root = null;
    if (viewToOpen.equals("chat")){
      loader.setLocation(getClass().getResource("/view/chat/ChatView.fxml"));
      root = loader.load();
      currentStage.setTitle("");

      currentScene = new Scene(root);

      currentStage.setOnCloseRequest(e ->{});

      currentStage.setScene(currentScene);
      currentStage.show();
    }
  }
}
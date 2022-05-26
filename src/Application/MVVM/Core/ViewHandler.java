package Application.MVVM.Core;

import Application.MVVM.Model.monster.Monster;
import Application.MVVM.View.Lobby.Dm.MonsterSearch.SelectMonsterViewController;
import Application.MVVM.View.Login.LoginViewController;
import Application.MVVM.View.TabPane.TabViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ViewHandler implements PropertyChangeListener
{
  private Scene currentScene;
  private final Stage currentStage;
  private final ViewModelFactory viewModelFactory;
  private static ViewHandler instance;
  private static final Lock lock = new ReentrantLock();

  public static ViewHandler getInstance(Stage stage) {
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
    currentStage.setResizable(false);
    this.viewModelFactory = viewModelFactory;
  }

  public void start() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    Parent root = null;
    loader.setLocation(getClass().getResource("/Application/MVVM/View/Login/LoginView.fxml"));
    root = loader.load();
    LoginViewController loginViewController = loader.getController();
    loginViewController.init(viewModelFactory.getLoginViewModel());
    viewModelFactory.getLoginViewModel().addPropertyChangeListener(this);
    currentStage.setTitle("Dnd support");

    currentScene = new Scene(root);
    currentStage.setOnCloseRequest(e ->loginViewController.onExit());
    currentStage.setScene(currentScene);
    currentStage.show();
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if (evt.getPropertyName().equals("Tabs")){
      startTab();
    }
    else if (evt.getPropertyName().equals("MonsterView")) {
      showSelectMonsterView((ArrayList<Monster>) evt.getNewValue());
    }
  }

  private void startTab(){
    FXMLLoader loader = new FXMLLoader();
    Parent root = null;
    loader.setLocation(getClass().getResource("/Application/MVVM/View/TabPane/TabView.fxml"));
    try
    {
      root = loader.load();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    TabViewController tabViewController = loader.getController();
    tabViewController.init(viewModelFactory.getLobbyViewModel(),
        viewModelFactory.getCharacterViewModel(), viewModelFactory.getTabViewModel());
    viewModelFactory.getCharacterViewModel().initCharactersList();
    currentStage.setTitle("Dnd support");

    currentScene = new Scene(root);
    currentStage.setOnCloseRequest(e -> tabViewController.onExit());
    currentStage.setScene(currentScene);
    currentStage.show();
  }

  private void showSelectMonsterView(ArrayList<Monster> monsters) {
    FXMLLoader loader = new FXMLLoader();
    Parent root = null;
    loader.setLocation(getClass().getResource("/Application/MVVM/View/Lobby/Dm/MonsterSearch/SelectMonsterView.fxml"));
    try
    {
      root = loader.load();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    Stage localStage = new Stage();

    SelectMonsterViewController controller = loader.getController();
    controller.init(viewModelFactory.getSelectMonsterViewModel(), monsters);

    localStage.setTitle("Select monster");
    Scene localScene = new Scene(root);
    localStage.setScene(localScene);
    localStage.setResizable(false);
    localStage.initModality(Modality.WINDOW_MODAL);
    localStage.initOwner(currentScene.getWindow());
    localStage.show();
  }
}

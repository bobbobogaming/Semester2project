package Application.MVVM.View.TabPane;

import Application.MVVM.View.CharacterSheet.CharacterViewController;
import Application.MVVM.View.CharacterSheet.CharacterViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

public class TabPaneController
{
  @FXML Tab tab1;

  @FXML CharacterViewController characterViewController;

  public void init(CharacterViewModel characterViewModel){
    characterViewController.init(characterViewModel);
  }
}

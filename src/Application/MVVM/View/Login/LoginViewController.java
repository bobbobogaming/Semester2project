package Application.MVVM.View.Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class LoginViewController
{
  @FXML private Button confirmButton;
  @FXML private TextField userNameField;
  private LoginViewModel loginViewModel;

  public void init(LoginViewModel loginViewModel)
  {
    userNameField.textProperty().bindBidirectional(loginViewModel.userInputProperty());
    confirmButton.disableProperty().bind(loginViewModel.confirmAvailabilityProperty());
    this.loginViewModel = loginViewModel;
  }

  public void onUserInput(KeyEvent keyEvent){
    loginViewModel.checkField();
  }

  public void onConfirm(ActionEvent actionEvent)
  {
    loginViewModel.login();
  }

  public void onExit()
  {
    loginViewModel.onExit();
  }
}

package Application.MVVM.View.Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginViewController
{
  @FXML TextField userNameField;
  private LoginViewModel loginViewModel;

  public void init(LoginViewModel loginViewModel)
  {
    this.loginViewModel = loginViewModel;
  }

  public void onConfirm(ActionEvent actionEvent)
  {
    loginViewModel.login(userNameField.getText());
  }

  public void onExit()
  {
    loginViewModel.onExit();
  }
}

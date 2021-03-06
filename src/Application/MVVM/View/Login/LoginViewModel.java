package Application.MVVM.View.Login;

import Application.Client.ClientLogin;
import Application.Client.UserID;
import Util.PropertyChangeSubject;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeListener;

public class LoginViewModel implements PropertyChangeSubject
{
  private final StringProperty userInput;
  private final BooleanProperty confirmAvailability;
  private final ClientLogin client;

  public LoginViewModel(ClientLogin client)
  {
    this.client = client;

    userInput = new SimpleStringProperty("");
    confirmAvailability = new SimpleBooleanProperty(true);
  }

  public StringProperty userInputProperty() {
    return userInput;
  }

  public BooleanProperty confirmAvailabilityProperty() {
    return confirmAvailability;
  }

  public void checkField() {
    confirmAvailability.setValue(userInput.getValue().isEmpty());
  }

  public void login(){
    if (!confirmAvailability.getValue()) client.setUserID(new UserID(userInput.getValue()));
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    client.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    client.removePropertyChangeListener(listener);
  }

  public void onExit()
  {
    if (client != null)
      client.onExit();
  }
}

package Application.MVVM.View.Login;

import Application.Client.ClientLogin;
import Application.Client.UserID;
import Util.PropertyChangeSubject;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel implements PropertyChangeSubject
{
  private StringProperty userInput;
  private BooleanProperty confirmAvailability;

  private ClientLogin client;
  private PropertyChangeSupport support;

  public LoginViewModel(ClientLogin clientRMI)
  {
    this.client = clientRMI;
    support = new PropertyChangeSupport(this);

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
    System.out.println(userInput.getValue());
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

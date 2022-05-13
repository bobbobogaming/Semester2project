package Application.MVVM.View.Login;

import Application.Client.ClientLogin;
import Application.Client.UserID;
import Util.PropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel implements PropertyChangeSubject
{
  private ClientLogin clientRMI;
  private PropertyChangeSupport support;

  public LoginViewModel(ClientLogin clientRMI)
  {
    this.clientRMI = clientRMI;
    support = new PropertyChangeSupport(this);
  }

  public void login(String name){
    clientRMI.setUserID(new UserID(name));
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    clientRMI.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    clientRMI.removePropertyChangeListener(listener);
  }

  public void onExit()
  {
    if (clientRMI != null)
    clientRMI.onExit();
  }
}

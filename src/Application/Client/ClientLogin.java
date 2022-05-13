package Application.Client;

import Util.PropertyChangeSubject;

public interface ClientLogin extends PropertyChangeSubject
{
  void setUserID(UserID userID);
  void onExit();
}

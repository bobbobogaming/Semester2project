package Application.Client;

import Application.MVVM.Model.initWrapper.InitWrapper;
import Util.PropertyChangeSubject;

public interface ClientLobby extends PropertyChangeSubject
{
  void createLobby();
  boolean connectToLobby(int lobbyId);
  void onExit();
}

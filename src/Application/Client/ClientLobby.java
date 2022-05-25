package Application.Client;

import Util.PropertyChangeSubject;

public interface ClientLobby extends PropertyChangeSubject
{
  void createLobby();
  boolean connectToLobby(int lobbyId);
  void onExit();
}

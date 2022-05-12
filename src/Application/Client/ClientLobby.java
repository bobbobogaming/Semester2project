package Application.Client;

import Shared.IClientModel;
import Util.PropertyChangeSubject;

public interface ClientLobby extends PropertyChangeSubject
{
  void createLobby();
  void connectToLobby(int lobbyId, IClientModel client);
  void onExit();
}

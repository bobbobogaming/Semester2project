package Application.Client;

import Application.MVVM.Model.initWrapper.InitWrapper;
import Util.PropertyChangeSubject;

public interface ClientLobby extends PropertyChangeSubject
{
  void createLobby();
  void connectToLobby(int lobbyId);
  void getMonsters();
  void removeInitiativeFromLobby(InitWrapper initWrapper);
  void onExit();
}

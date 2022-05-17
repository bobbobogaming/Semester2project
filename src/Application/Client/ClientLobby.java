package Application.Client;

import Application.MVVM.Model.InitWrapper;
import Application.MVVM.Model.monster.Monster;
import Util.PropertyChangeSubject;

public interface ClientLobby extends PropertyChangeSubject
{
  void createLobby();
  void connectToLobby(int lobbyId);
  void getMonsters();
  void removeInitiativeFromLobby(InitWrapper initWrapper);
  void onExit();
}

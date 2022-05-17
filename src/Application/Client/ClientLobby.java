package Application.Client;

import Application.MVVM.Model.monster.Monster;
import Util.PropertyChangeSubject;

public interface ClientLobby extends PropertyChangeSubject
{
  void createLobby();
  void connectToLobby(int lobbyId);
  void getMonsters();
  void removeMonsterFromLobby(Monster monster);
  void onExit();
}

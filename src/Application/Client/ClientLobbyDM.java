package Application.Client;

import Application.MVVM.Model.initWrapper.InitWrapper;

public interface ClientLobbyDM {
  void getMonsters();
  void removeInitiativeFromLobby(InitWrapper initWrapper);
  void updateInitList(InitWrapper initiative);
  void switchCombatState();
}

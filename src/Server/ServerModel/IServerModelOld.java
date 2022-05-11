package Server.ServerModel;

import Application.MVVM.Model.character.Character;
import Util.IClientModel;

public interface IServerModelOld {
    void saveCharacter(Character character);
    Character getCharacter(String name);
    void createLobby(IClientModel lobbyCreator);
    void connectToLobby(int lobbyId, IClientModel client);
}
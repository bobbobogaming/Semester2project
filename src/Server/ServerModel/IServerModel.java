package Server.ServerModel;

import Application.MVVM.Model.character.Character;

public interface IServerModel {
    void saveCharacter(Character character);
    Character getCharacter(String name);
}

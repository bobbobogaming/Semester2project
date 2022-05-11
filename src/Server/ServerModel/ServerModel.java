package Server.ServerModel;

import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.character.Stats;
import Database.DataBaseConnector;

public class ServerModel implements IServerModel {

    public ServerModel(){}

    @Override
    public void saveCharacter(Character character) {
        System.out.println(character.getName());
        //DatabaseWrapper.addCharacter(character.getName(),"n/a");
    }

    @Override
    public Character getCharacter(String name) {
        DataBaseConnector.getAllCharacters();
        return  new Character(new Stats(1,2,3,4,5,6),"yo");
    }
}

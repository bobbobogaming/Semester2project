package Server.ServerConnector;

import Application.MVVM.Model.Character;
import Application.MVVM.Model.character.Stats;

public class JDBCRedacted
{
    public static void makeCharacter(Character character) {
    }

    public static Character getCharacter(String name) {
        return new Character(new Stats(10,10,01,10,10,10),"");
    }
}

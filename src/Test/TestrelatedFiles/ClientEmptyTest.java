package Test.TestrelatedFiles;

import Application.Client.ClientCharacterSheet;
import Application.Client.UserID;
import Application.MVVM.Model.character.Character;

import java.sql.SQLException;
import java.util.ArrayList;

public class ClientEmptyTest implements ClientCharacterSheet {
    @Override
    public void makeCharacter(Character character) throws SQLException {

    }

    @Override
    public void setCurrentCharacter(Character character) {

    }

    @Override
    public UserID getUserID() {
        return null;
    }

    @Override
    public ArrayList<Character> getCharacters() {
        return null;
    }
}

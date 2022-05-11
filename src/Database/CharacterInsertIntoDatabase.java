package Database;

import Application.MVVM.Model.character.Character;

public class CharacterInsertIntoDatabase {

    private IDatabaseConnector IDatabaseConnector;

    public CharacterInsertIntoDatabase() {
        IDatabaseConnector = new DataBaseConnector();
    }

    public void InsertCharacterIntoDatabase(Character character, String user)
    {

    }
}

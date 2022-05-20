package Application.Client;

import Application.MVVM.Model.character.Character;

import java.io.Serializable;

public class UserID implements Serializable
{
  private final String name;
  private boolean isInLobby = false;
  private int lobbyId;
  private Character currentCharacter;

  public UserID(String name)
  {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getLobbyId() {
    return lobbyId;
  }

  public void setLobbyId(int lobbyId) {
    this.lobbyId = lobbyId;
  }

  public boolean isInLobby() {
    return isInLobby;
  }

  public void setInLobby(boolean inLobby) {
    isInLobby = inLobby;
  }

  public Character getCurrentCharacter() {
    return currentCharacter;
  }

  public void setCurrentCharacter(Character currentCharacter) {
    this.currentCharacter = currentCharacter;
  }

  @Override public String toString()
  {
    return "UserID{name='" + name + '\'' + '}';
  }
}

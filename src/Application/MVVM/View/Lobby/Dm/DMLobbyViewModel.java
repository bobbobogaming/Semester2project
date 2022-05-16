package Application.MVVM.View.Lobby.Dm;

import Application.Client.Client;
import Application.Client.ClientLobby;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DMLobbyViewModel
{
  private StringProperty lobbyIdProperty;

  public ClientLobby client; //TODO should be chanced to a more fitting interface

  public DMLobbyViewModel(Client clientRMI)
  {
    client = clientRMI;
    lobbyIdProperty = new SimpleStringProperty();
  }

  public StringProperty lobbyIdProperty()
  {
    return lobbyIdProperty;
  }

  public void setLobbyId(String lobbyId)
  {
    lobbyIdProperty.setValue("Lobby id: " + lobbyId);
  }

  public void openMonsterList() {
    client.getMonsters();
  }

  @Override public void propertyChange(PropertyChangeEvent evt) {

    if (evt.getPropertyName().equals("UpdateMonsterTable")) {
      initList.clear();
      initList.addAll((ArrayList<Monster>) evt.getNewValue());
    }
  }

  public ListProperty<Monster> initListProperty() {
    return initList;
  }

  public void removeMonster(Monster monster) {
    client.removeMonsterFromLobby(monster);
  }
}

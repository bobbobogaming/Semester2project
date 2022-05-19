package Application.MVVM.View.Lobby.Player.characterselect;

import Application.Client.ClientChooseCharacter;
import Application.MVVM.Model.character.Character;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class SelectCharacterViewModel {

  private final ListProperty<Character> characterList;
  private final ArrayList<Character> characterListItems;

  private final ClientChooseCharacter clientChooseCharacter;

  public SelectCharacterViewModel(ClientChooseCharacter clientChooseCharacter) {
    this.clientChooseCharacter = clientChooseCharacter;
    characterListItems = new ArrayList<>();
    characterList = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));
  }

  public void onSearchBarKeyTyped(String query) {
    ObservableList<Character> filteredCharacterList = FXCollections.observableArrayList(new ArrayList<>());
    for (Character characterListItem : characterListItems) {
      if (characterListItem.getName().toLowerCase().contains(query.toLowerCase())) filteredCharacterList.add(characterListItem);
    }
    characterList.setValue(filteredCharacterList);
  }

}

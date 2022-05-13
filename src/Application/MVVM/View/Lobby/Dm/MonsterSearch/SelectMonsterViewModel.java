package Application.MVVM.View.Lobby.Dm.MonsterSearch;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.Locale;

public class SelectMonsterViewModel {

  private ListProperty<String> monsterList;
  private ArrayList<String> monsterListItems;

  public SelectMonsterViewModel() {
    monsterListItems = new ArrayList<>();
    monsterList = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));
  }

  public ListProperty<String> getMonsterListProperty() {
    return monsterList;
  }

  public void onSearchBarKeyTyped(String query) {
    ObservableList<String> filteredMonsterList = FXCollections.observableArrayList(new ArrayList<>());
    for (String monsterListItem : monsterListItems) {
      if (monsterListItem.toLowerCase().contains(query.toLowerCase())) filteredMonsterList.add(monsterListItem);
    }
    monsterList.setValue(filteredMonsterList);
  }

  public void setMonsterListItems(ArrayList<String> monsterListItems) {
    this.monsterListItems = monsterListItems;
    monsterList.clear();
    monsterList.addAll(monsterListItems);
  }
}

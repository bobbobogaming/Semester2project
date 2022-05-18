package Application.MVVM.View.Lobby.Dm.MonsterSearch;

import Application.Client.ClientAddMonster;
import Application.MVVM.Model.initWrapper.InitWrapper;
import Application.MVVM.Model.monster.Monster;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class SelectMonsterViewModel {

  private ListProperty<Monster> monsterList;
  private ArrayList<Monster> monsterListItems;
  private ClientAddMonster clientAddMonster;

  public SelectMonsterViewModel(ClientAddMonster clientAddMonster) {
    this.clientAddMonster = clientAddMonster;

    monsterListItems = new ArrayList<>();
    monsterList = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));
  }

  public ListProperty<Monster> getMonsterListProperty() {
    return monsterList;
  }

  public void onSearchBarKeyTyped(String query) {
    ObservableList<Monster> filteredMonsterList = FXCollections.observableArrayList(new ArrayList<>());
    for (Monster monsterListItem : monsterListItems) {
      if (monsterListItem.getMonsterName().toLowerCase().contains(query.toLowerCase())) filteredMonsterList.add(monsterListItem);
    }
    monsterList.setValue(filteredMonsterList);
  }

  public void setMonsterListItems(ArrayList<Monster> monsterListItems) {
    this.monsterListItems = monsterListItems;
    monsterList.clear();
    monsterList.addAll(monsterListItems);
  }
  public void addMonster(Monster monster){
    clientAddMonster.addInitiativeToLobby(new InitWrapper(monster));
  }
}

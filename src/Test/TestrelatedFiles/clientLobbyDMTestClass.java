package Test.TestrelatedFiles;

import Application.Client.ClientLobbyDM;
import Application.MVVM.Model.initWrapper.InitWrapper;
import Application.MVVM.Model.monster.Monster;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class clientLobbyDMTestClass implements ClientLobbyDM {

    ArrayList<Monster> monsterArrayList;

    private final PropertyChangeSupport support;

    public clientLobbyDMTestClass() {
        support = new PropertyChangeSupport(this);
    }

    @Override
    public void getMonsters() {
        support.firePropertyChange("MonsterView",null,monsterArrayList);
    }

    @Override
    public void removeInitiativeFromLobby(InitWrapper initWrapper) {

    }

    @Override
    public void updateInitList(InitWrapper initiative) {

    }

    @Override
    public void switchCombatState() {

    }

    public void addmonster(Monster monster){
        monsterArrayList.add(monster);
    }
}

package Test.TestrelatedFiles;

import Application.Client.ClientLobby;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ClientLobbyTest implements ClientLobby {

    private PropertyChangeSupport support;
    ArrayList<Integer> arrayList;

    int lobbyNumber = 0;
    public ClientLobbyTest() {
        this.arrayList = new ArrayList<>();
        support = new PropertyChangeSupport(this);
    }

    @Override
    public void createLobby() {
        arrayList.add(lobbyNumber++);

    }

    @Override
    public boolean connectToLobby(int lobbyId) {
        return arrayList.contains(lobbyId);
    }

    @Override
    public void onExit() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}

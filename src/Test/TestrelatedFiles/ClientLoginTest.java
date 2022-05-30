package Test.TestrelatedFiles;

import Application.Client.ClientLogin;
import Application.Client.UserID;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ClientLoginTest implements ClientLogin {



    private UserID userID;

    private PropertyChangeSupport support;

    public ClientLoginTest() {
        support = new PropertyChangeSupport(this);;
    }

    public UserID getUserID() {
        return userID;
    }

    @Override
    public void setUserID(UserID userID) {
            this.userID = userID;

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

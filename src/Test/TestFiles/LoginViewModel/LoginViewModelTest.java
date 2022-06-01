package Test.TestFiles.LoginViewModel;

import Application.Client.ClientLogin;
import Application.Client.UserID;
import Application.MVVM.View.Login.LoginViewModel;
import Test.TestrelatedFiles.ClientLoginTest;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginViewModelTest {

    private LoginViewModel loginViewModel;

    private ClientLoginTest clientLogin;
    private StringProperty userNameField;
    private BooleanProperty confirmAvailability;


    @BeforeEach
    void setUp() {
        clientLogin = new ClientLoginTest();
        loginViewModel = new LoginViewModel(clientLogin);
        userNameField = new SimpleStringProperty("");
        userNameField.bindBidirectional(loginViewModel.userInputProperty());
        confirmAvailability = new SimpleBooleanProperty(true);
        confirmAvailability.bindBidirectional(loginViewModel.confirmAvailabilityProperty());
    }

    @Test
    void testForNoUserSet(){
        loginViewModel.login();
        assertEquals(clientLogin.getUserID(),null);
    }

    @Test
    void testAddOneUserThreeLetter(){
        confirmAvailability.set(false);
        userNameField.setValue("BOB");
        loginViewModel.login();
        assertEquals(clientLogin.getUserID().getName(),"BOB");
    }
}
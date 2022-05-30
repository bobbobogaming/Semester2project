package Test.TestFiles.LobbyViewModel;

import Application.Client.Client;
import Application.Client.ClientLobby;
import Application.Client.ClientLobbyDM;
import Application.MVVM.View.Lobby.Root.LobbyViewModel;
import Test.TestrelatedFiles.ClientLobbyTest;
import Util.textfieldfilter.PosetiveNumberStrategy;
import Util.textfieldfilter.UnaryFilterContext;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LobbyViewModelTest {

    private LobbyViewModel lobbyViewModel;
    private ClientLobby clientLobby;
    private StringProperty lobbyError;
    private StringProperty lobbyId;

    @BeforeEach
    void setUp() {
        clientLobby = new ClientLobbyTest();
        lobbyViewModel = new LobbyViewModel(clientLobby);


        lobbyError = new SimpleStringProperty("");
        lobbyId = new SimpleStringProperty("");

        lobbyViewModel.lobbyIdTextProperty().bindBidirectional(lobbyId);
        lobbyViewModel.lobbyErrorProperty().bindBidirectional(lobbyError);
    }

    @Test
    void connectToLobbyWhereNoLobbyNumberIsInsertedNothinghappens(){
        lobbyViewModel.joinLobby();
        assertTrue(lobbyError.toString().equals("StringProperty [value: ]"));
    }

    @Test
    void connectToANoneExcstedLobby(){
        lobbyId.setValue("1");
        lobbyViewModel.joinLobby();
        assertTrue(lobbyError.toString().equals("StringProperty [value: Could not connect to lobby]"));
    }

    @Test
    void testJoinIfYouCanJoinASingleLobby(){
        lobbyViewModel.createLobby();
        lobbyId.setValue("0");

        lobbyViewModel.joinLobby();

        assertTrue(lobbyError.toString().equals("StringProperty [value: ]"));
    }

    @Test
    void testJoinWhereThereIsMultipleLobbies(){
        //allign

        for (int i = 0; i <10 ; i++) {
            lobbyViewModel.createLobby();
        }

        for (int i = 0; i < 10; i++) {
            lobbyId.setValue(Integer.toString(i));
            lobbyViewModel.joinLobby();

            assertTrue(lobbyError.toString().equals("StringProperty [value: ]"));
        }

    }
}
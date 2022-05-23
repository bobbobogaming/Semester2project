package Application.MVVM.View.Lobby.Root;

import Util.textfieldfilter.PosetiveNumberStrategy;
import Util.textfieldfilter.UnaryFilterContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.function.UnaryOperator;

public class LobbyViewController
{
  @FXML private TextField lobbyId;
  @FXML private Label lobbyError;
  private LobbyViewModel lobbyViewModel;

  public void init(LobbyViewModel lobbyViewModel)
  {
    this.lobbyViewModel = lobbyViewModel;

    lobbyId.setTextFormatter(new TextFormatter<>(new UnaryFilterContext(new PosetiveNumberStrategy(8))));
    lobbyError.textProperty().bind(lobbyViewModel.lobbyErrorPropertyProperty());
  }

  public void createLobby(ActionEvent actionEvent)
  {
    lobbyViewModel.createLobby();
  }

  public void joinLobby(ActionEvent actionEvent)
  {
    lobbyViewModel.joinLobby(Integer.parseInt(lobbyId.getText()));
  }

  public void onExit(){
    lobbyViewModel.onExit();
  }
}

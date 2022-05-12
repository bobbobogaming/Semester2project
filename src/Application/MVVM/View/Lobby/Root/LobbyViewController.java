package Application.MVVM.View.Lobby.Root;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
  private LobbyViewModel lobbyViewModel;

  public void init(LobbyViewModel lobbyViewModel)
  {
    this.lobbyViewModel = lobbyViewModel;

    DecimalFormat format = new DecimalFormat("#");
    UnaryOperator<TextFormatter.Change> filter = c -> {
      if (c.getControlNewText().isEmpty()){
        return c;
      }

      ParsePosition parsePosition = new ParsePosition(0);
      Object object = format.parse(c.getControlNewText(),parsePosition);

      if ((object == null) || ((parsePosition.getIndex()) < (c.getControlNewText().length()))){
        return null;
      } else {
        return c;
      }
    };

    lobbyId.setTextFormatter(new TextFormatter<>(filter));
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

package Application.MVVM.View.Lobby.Root;

import Util.textfieldfilter.PosetiveNumberStrategy;
import Util.textfieldfilter.UnaryFilterContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class LobbyViewController
{
  @FXML private TextField lobbyId;
  @FXML private Label lobbyError;
  private LobbyViewModel viewModel;

  public void init(LobbyViewModel lobbyViewModel)
  {
    this.viewModel = lobbyViewModel;

    lobbyId.textProperty().bindBidirectional(viewModel.lobbyIdTextProperty());
    lobbyId.setTextFormatter(new TextFormatter<>(new UnaryFilterContext(new PosetiveNumberStrategy(8))));
    lobbyError.textProperty().bind(lobbyViewModel.lobbyErrorProperty());
  }

  public void createLobby(ActionEvent actionEvent)
  {
    viewModel.createLobby();
  }

  public void joinLobby(ActionEvent actionEvent)
  {
    viewModel.joinLobby();
  }

  public void onExit(){
    viewModel.onExit();
  }
}

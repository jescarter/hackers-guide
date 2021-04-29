package viewLogic;

/*
 * Controller for the game recommendation fxml, that will show games
 * last updated 04/18/2021
 * Author(s) Ian Holder,
 */

import gameGenie.GameGenieController;
import resources.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class GameRecommendationLogic {
    private Game currentGame;

    @FXML
    private Label gamePlatforms;
    @FXML
    private Label gameTitleLabel;
    @FXML
    private Label gameReleaseDate;
    @FXML
    private Label gameGenre;
    @FXML
    private Label gameMetacritic;
    @FXML
    private Label recommendationGameTitle;
    @FXML
    private ImageView recommendationGameCoverArt;
    @FXML
    private Button doneWithRecommendationButton;

    @FXML public void initialize(){
        currentGame = GameGenieController.getRecommendation();
        recommendationGameTitle.setText(currentGame.getTitle());
        recommendationGameCoverArt.setImage(new Image("File:" + currentGame.getCoverFilePath().toString()));
        gameTitleLabel.setText("Title: " + currentGame.getTitle());
        gameReleaseDate.setText("Release Date: " + currentGame.getReleaseDate());
        gameGenre.setText("Genres: " + currentGame.getGameGenreString());
        gamePlatforms.setText("Platforms: " + currentGame.getGamePlatformString());
        gameMetacritic.setText("Metacritic Score: " + currentGame.getMetacriticScore());
    }

    public void doneClicked(){
        GameGenieController.getInstance().changeSceneIntoGamePicker();
    }
}


package controller;

import game_genie.GameGenieController;
import model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import resources.Load;

/**
 * Controller for the game recommendation fxml, that will show games
 * last updated 04/18/2021
 * Author(s) Ian Holder,
 */

public class GameRecommendationController {
    private Game currentGame;

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
        currentGame = Load.getRecommendation();
        recommendationGameTitle.setText(currentGame.getTitle());
        recommendationGameCoverArt.setImage(new Image("File:" + currentGame.getCoverFilePath().toString()));
        gameTitleLabel.setText("Title: " + currentGame.getTitle());
        gameReleaseDate.setText("Release Date: " + currentGame.getReleaseDate());
        gameGenre.setText("Genre: " + currentGame.getGameGenreString());
        gameMetacritic.setText("Metacritic Score: " + currentGame.getMetacriticScore());
    }

    public void doneClicked(){
        GameGenieController.getInstance().changeSceneIntoGamePicker();
    }
}


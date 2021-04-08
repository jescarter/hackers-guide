package game_genie;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * last updated 03/25/2021
 * Author(s) Ian Holder,
 */

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
        //GameInfoTranslator.getVideoGameInfo();
        //GenreTranslator.getGenres();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /**
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/startScreen.fxml"));
            Parent root = myLoader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
        }
         */
        GameGenieController.getInstance().setPrimaryStage(primaryStage);


    }

}
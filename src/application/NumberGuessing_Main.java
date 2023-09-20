package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class NumberGuessing_Main extends Application {
	static Pane game, setting;

	@Override
	public void start(Stage primaryStage) {
		try {
			Pane container = new Pane();
			game = FXMLLoader.load(getClass().getResource("game.fxml"));
			setting = FXMLLoader.load(getClass().getResource("setting.fxml"));
//			setting.setVisible(false);
			container.getChildren().addAll(game, setting);
//			Button button=new Button("submit");
//			container.getChildren().add(button);
			Scene scene = new Scene(container, 600, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

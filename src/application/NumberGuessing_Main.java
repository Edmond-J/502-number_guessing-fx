package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class NumberGuessing_Main extends Application {
//	static Pane game, setting;
	Parent game;
	Parent setting;
	private int min = 0, max = 20, attempts = 5, yourGuess, target;

	@Override
	public void start(Stage primaryStage) {
		target = (int)(min+Math.random()*(max-min));
		try {
			Pane container = new Pane();
			game = FXMLLoader.load(getClass().getResource("game.fxml"));
			setting = FXMLLoader.load(getClass().getResource("setting.fxml"));
			setting.setVisible(false);
			container.getChildren().addAll(game, setting);
			System.out.println(game.toString());
			TextField startText = (TextField)setting.lookup("#startText");
			startText.setPromptText(""+min);
			TextField rangeText = (TextField)setting.lookup("#rangeText");
			rangeText.setPromptText(""+(max-min));
			TextField attamptsText = (TextField)setting.lookup("#attamptsText");
			attamptsText.setPromptText(""+attempts);
			Button applyButton = (Button)setting.lookup("#applyButton");
			applyButton.setOnMouseClicked(e -> {
				if (startText.getText().length() > 0)
					min = Integer.valueOf(startText.getText());
				if (rangeText.getText().length() > 0)
					max = min+Integer.valueOf(rangeText.getText());
				if (attamptsText.getText().length() > 0)
					attempts = Integer.valueOf(attamptsText.getText());
				setting.setVisible(false);
			});
			Label result = (Label)game.lookup("#result");
			Button settingButton = (Button)game.lookup("#settingButton");
			settingButton.setOnMouseClicked(e -> {
				setting.setVisible(true);
			});
			Button submitButton = (Button)game.lookup("#submitButton");
			submitButton.setOnMouseClicked(e -> {
				TextField guessText = (TextField)game.lookup("#guessText");

				if (guessText.getText().length() <= 0 || !onlyNumber(guessText.getText())) {
					result.setText("invalid input!");
					return;
				}
				yourGuess = Integer.valueOf(guessText.getText());
				if (yourGuess > target) {
					attempts--;
					result.setText("too big! "+attempts+" left");
				} else if (yourGuess < target) {
					attempts--;
					result.setText("too small! "+attempts+" left");
				} else result.setText("bingo! the answer is: "+target);
				
				if (attempts <= 0) {
					result.setText("you lose!");
					return;
				}
			});
			Scene scene = new Scene(container, 600, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean onlyNumber(String s) {
		for (char c : s.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		launch(args);
	}
}

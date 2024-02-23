package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class NumberGuessing_Main extends Application {
//	static Pane game, setting;
	private Parent game;
	private Parent setting;
	private int min = 0, max = 20, attempts = 5, yourGuess, target;
	@FXML
	private TextField startText, rangeText, attamptsText, guessText;
	@FXML
	private Button applyButton, settingButton, submitButton;

	public void setupInitialValue() {
		target = (int)(min+Math.random()*(max-min));
		min = 0;
		max = 20;
		attempts = 5;
	}

	@Override
	public void start(Stage primaryStage) {
		setupInitialValue();
		try {
			Pane container = new Pane();
			FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("game.fxml"));
			gameLoader.setController(this);
			game = gameLoader.load();
//			setting = FXMLLoader.load(getClass().getResource("setting.fxml"));
			FXMLLoader settingLoader = new FXMLLoader(getClass().getResource("setting.fxml"));
			settingLoader.setController(this);
			setting = settingLoader.load();
			setting.setVisible(false);
			container.getChildren().addAll(game, setting);
			System.out.println(game.toString());
//			TextField startText = (TextField)setting.lookup("#startText");
			startText.setPromptText(""+min);
//			TextField rangeText = (TextField)setting.lookup("#rangeText");
			rangeText.setPromptText(""+(max-min));
//			TextField attamptsText = (TextField)setting.lookup("#attamptsText");
			attamptsText.setPromptText(""+attempts);
//			Button applyButton = (Button)setting.lookup("#applyButton");
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
//			Button settingButton = (Button)game.lookup("#settingButton");
			settingButton.setOnMouseClicked(e -> {
				if (setting.isVisible())
					setting.setVisible(false);
				else setting.setVisible(true);
			});
//			Button submitButton = (Button)game.lookup("#submitButton");
			submitButton.setOnMouseClicked(e -> {
//				TextField guessText = (TextField)game.lookup("#guessText");
				if (guessText.getText().length() <= 0 || !onlyNumber(guessText.getText())) {
					result.setText("invalid input!");
					return;
				}
				yourGuess = Integer.valueOf(guessText.getText());
				if (yourGuess < min || yourGuess > max) {
					result.setText("The range is "+min+" ~ "+max);
					return;
				}
				if (yourGuess > target) {
					attempts--;
					result.setText("Too big, "+attempts+" times left");
				} else if (yourGuess < target) {
					attempts--;
					result.setText("Too small, "+attempts+" times left");
				} else {
					result.setText("Bingo! the answer is: "+target);
					setupInitialValue();
				}
				if (attempts <= 0) {
					result.setText("you lose");
					return;
				}
//				System.out.println(target);
			});
			Scene scene = new Scene(container, 600, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setResizable(false);
			primaryStage.setTitle("Number Guessing Game");
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

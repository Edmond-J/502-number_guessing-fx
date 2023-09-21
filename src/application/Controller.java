package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Controller {
	private int min = 0, max = 20, attempts = 5, yourGuess, target;
	@FXML
	private TextField startText, rangeText, attamptsText, guessText;
	@FXML
	private Label result;

	public void initialize() {
		target = (int)(min+Math.random()*(max-min));
		
	}

	public void applySetting(ActionEvent e) {
		if (startText.getText().length() > 0)
			min = Integer.valueOf(startText.getText());
		if (rangeText.getText().length() > 0)
			max = min+Integer.valueOf(rangeText.getText());
		if (attamptsText.getText().length() > 0)
			attempts = Integer.valueOf(attamptsText.getText());
//		setting.setVisible(false);
		NumberGuessing_Main.setting.setVisible(false);
		System.out.println(min);
		System.out.println(max);
		System.out.println(attempts);
		System.out.println(target);
	}

	public void showSetting() throws IOException {
//		startText.setPromptText(""+min);
//		rangeText.setPromptText(""+(max-min));
//		attamptsText.setPromptText(""+attempts);
//		NumberGuessing_Main.setting = FXMLLoader.load(getClass().getResource("setting.fxml"));
//		fxmlLoader.setController(this);
//		settingPane.getChildren().add(fxmlLoader.load());
		NumberGuessing_Main.setting.setVisible(true);
		// setting.setVisible(true);
	}

	public void submitGuessing() {
		
		if (attempts <= 0) {
			result.setText("you lose!");
			return;
		}
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
		System.out.println(target);
		System.out.println(yourGuess);
	}

	public boolean onlyNumber(String s) {
		for (char c : s.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	public Controller() {
	}
}

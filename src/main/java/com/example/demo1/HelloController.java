package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private final Random random = new Random();
    private int randomNumber;
    private int guessCount = 0;
    private int correctNumber = 0;
    private boolean isGivenUp = false;
    private boolean isWin = false;

    @FXML
    private TextField guess;
    @FXML
    private Text guessCounterText;
    @FXML
    private Text correctNumberText;
    @FXML
    private ImageView upArrow;
    @FXML
    private ImageView downArrow;
    @FXML
    private ImageView correct;
    @FXML
    private ImageView emoji;
    @FXML
    private Button playButton;
    @FXML
    private Button giveUpButton;
    @FXML
    private Button enterButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(guess.getProperties().values());

        randomNumber = random.nextInt(100);
        System.out.println(randomNumber);
        correctNumber = randomNumber;
        downArrow.setVisible(false);
        upArrow.setVisible(false);
        correct.setVisible(false);
        playButton.setVisible(false);
        giveUpButton.setVisible(true);

        guess.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                guess.clear();
            }
        });

        enterButton.setOnMouseClicked(event -> {
            if(guess.equals("[]")){ return;}
                guess.clear();
        });
    }

    @FXML
    void checkGuess(ActionEvent event) {
        String guessText = guess.getText().trim(); // Get and trim the text
        if (guessText.isEmpty()) {
            // The guess field is empty, return early or show an error message
            showErrorAlert("Error", "Empty Guess", "Please enter a guess before checking.");
            return;
        }

        try {

            isGivenUp = false;
            if (Integer.parseInt(guess.getText()) == randomNumber) {
                isWin = true;
                downArrow.setVisible(false);
                upArrow.setVisible(false);
                correct.setVisible(true);
                playButton.setVisible(true);
                giveUpButton.setVisible(false);
                guess.setDisable(true);
                enterButton.setDisable(true);
            } else if (Integer.parseInt(guess.getText()) > randomNumber) {
                downArrow.setVisible(true);
                upArrow.setVisible(false);
                correct.setVisible(false);
            } else if (Integer.parseInt(guess.getText()) < randomNumber) {
                downArrow.setVisible(false);
                upArrow.setVisible(true);
                correct.setVisible(false);
            }
            guessCount++;
            guessCounterText.setText("Guesses: " + guessCount);
        } catch(NumberFormatException e){
            // Handle the case where the input is not a valid number
            showErrorAlert("Error", "Invalid Input", "Please enter a valid number.");
        }

    }

    @FXML
    void reset(ActionEvent event) {
        correctNumberText.setText("");
        randomNumber = random.nextInt(100);
        System.out.println(randomNumber);
        correctNumber = randomNumber;
        isGivenUp = false;
        downArrow.setVisible(false);
        upArrow.setVisible(false);
        correct.setVisible(false);
        guessCount = 0;
        guessCounterText.setText("Guesses: " + guessCount);
        playButton.setVisible(false);
        giveUpButton.setVisible(true);
        guess.setDisable(false);
        enterButton.setDisable(false);
        emoji.setVisible(false);

    }

    @FXML
    void showNumber(ActionEvent event) {
        downArrow.setVisible(false);
        upArrow.setVisible(false);
        correct.setVisible(false);
        guessCount = 0;
        playButton.setVisible(true);
        giveUpButton.setVisible(false);
        guess.setDisable(true);
        enterButton.setDisable(true);
        emoji.setVisible(true);

        // Display the correct number
        isGivenUp = true;
        correctNumberText.setText("Correct Number: " + correctNumber);

    }

    private void showErrorAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }



}


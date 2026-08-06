package com.example.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.List;

import com.example.factory.MaGoFactory;
import com.example.factory.MaGoPopulationGenerator;
import com.example.interfaces.MessageStrategy;
import com.example.models.MaGo;
import com.example.models.MaGoPopulation;
import com.example.models.Message;
import com.example.strategy.MaGoAnyStrategy;
import com.example.strategy.MaGoCircleStrategy;
import com.example.strategy.MaGoRandomStrategy;
import com.example.strategy.MaGoSquareStrategy;
import com.example.utils.MessageReader;
import com.example.view.MaGoControlView;
import com.example.view.MainView;

import javafx.stage.Stage;


/**
 * This classe Controls the whole application.
 * Creates the population, the main view and coordinates the interactions between the model and the view.
 */
public class MainController {

    private static final String FILE_NAME  = "mess.msg";
    private static final int MAX_COUNTDOWN = 10;
    private static final int SECONDE = 1;
    private static final int ZERO = 0;
    
    private MaGoFactory factory;
    private MaGoPopulationGenerator populationGenerator;
    private MaGoPopulation population;
    private MessageReader messageReader;
    private MainView view;

    private MessageStrategy strategy;
    private final MessageStrategy randomStrategy;
    private final MessageStrategy circleStrategy;
    private final MessageStrategy squareStrategy;
    private final MessageStrategy anyStrategy;

    private Timeline timer;
    private int countdown = MAX_COUNTDOWN;


    /**
     * Creates the application controller.
     * Initializes the population, the main view,
     * the message reader and the timer.
     *
     * @param primaryStage application's main window
     */
    public MainController(Stage primaryStage) {
        this.populationGenerator = new MaGoPopulationGenerator();
        this.population = populationGenerator.createRandomPopulation();
        this.view = new MainView(primaryStage, population);
        this.messageReader = new MessageReader(FILE_NAME);

        this.randomStrategy = new MaGoRandomStrategy();
        this.circleStrategy = new MaGoCircleStrategy();
        this.squareStrategy = new MaGoSquareStrategy();
        this.anyStrategy = new MaGoAnyStrategy();

        population.attach(view);

        initializeTimer();
        setActions();
    }

    /**
     * Initializes and starts the countdown timer.
     * Every second the countdown is updated.
     * When it reaches zero, the next message is processed.
     */
    private void initializeTimer() {
        view.updateCountdown(countdown);
        timer = new Timeline(new KeyFrame(Duration.seconds(SECONDE), e -> {
            countdown--;
            view.updateCountdown(countdown);

            if (countdown <= ZERO) {
                showNextMessage();
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }
    
    /**
     * Registers all user actions.
     * Handles the Next button and the click on each MaGo card.
     */
    private void setActions() {        
        view.getNextButton().setOnAction(e -> { showNextMessage(); });
        
        for (int i = ZERO; i < population.getMaGoList().size(); i++) {
            MaGo maGo = population.getMaGoList().get(i);
            view.getMaGoCard(i).setOnMouseClicked(e -> openMaGoControl(maGo));
        }
    }


    /**
     * Displays the next message and applies it
     * to the selected MaGo according to the current strategy.
     * Stops the timer when there are no more messages.
     */
    private void showNextMessage() {

        if (!messageReader.getMessagesList().isEmpty()) {

            updateStrategy();

            List<Message> messagesList;
            messagesList = messageReader.getMessagesList();

            Message message = strategy.chooseMessage(messagesList,population);
            messageReader.removeMessage(message);

            population.reactToMessage(message);

            view.updateMessage(message.getText());
            countdown = MAX_COUNTDOWN;

            view.updateCountdown(countdown);
        } else {
            timer.stop();
            view.getNextButton().setDisable(true);
        }
    }

    /**
     * Updates the current message strategy
     * according to the selected radio button.
     */
    private void updateStrategy() {

        if (view.isRandomSelected()) {
            strategy = randomStrategy;
        }
        else if (view.isCircleSelected()) {
            strategy = circleStrategy;
        }
        else if (view.isSquareSelected()) {
            strategy = squareStrategy;
        }
        else {
            strategy = anyStrategy;
        }
    }

    /**
     * Opens the control window for the selected MaGo.
     * @param maGo selected MaGo
     */
    private void openMaGoControl(MaGo maGo) {
        timer.pause();
        MaGoControlView controlView = new MaGoControlView(this.view.getStage(), maGo);
        controlView.getStage().setOnHidden(e -> timer.play());
        new MaGoControlController(controlView, maGo, population);
    }
}
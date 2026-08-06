package com.example.view;


import com.example.models.MaGo;
import com.example.utils.MyGlobals;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Displays the control window used to modify a MaGo status.
 * The window allows the user to enter a new status value
 * and confirm the modification.
 */
public class MaGoControlView {
    private static final int WIDTH = 420;
    private static final int HEIGHT = 170;
    private static final int MIN_WIDTH = 350;
    private static final int SPACING = 10;
    private static final int PADDING = 20;

    private static final String BUTTON_CONFIR_TEXT = "Confirm New Value";
    private static final String STATUS_LABEL_TEXT = "Actual Status:  ";
    private static final String NEW_VALUE_LABEL_TEXT = "New Value in [" + MyGlobals.MIN_STATUS + ":" + MyGlobals.MAX_STATUS + "]";

    private Button btnConfirm;
    private Label labelStatusText;
    private Label labelNewValueText;
    private Label labelCurrentStatus;
    private TextField textFieldNewValue;
    private HBox firstLine;
    private HBox secondLine;
    private Scene scene;
    private VBox layout;
    private final Stage owner;
    private Stage windown;
    private MaGo maGo;

    /**
     * Creates the control window for the selected MaGo.
     *
     * @param owner parent window
     * @param maGo selected MaGo
     */
    public MaGoControlView(Stage owner, MaGo maGo) {
        this.owner = owner;
        this.maGo = maGo;
        initializeComponent(owner);
        initializeControlView(); 
    }

    //GETTERS
    public Button geButtonConfirm() { return btnConfirm; }
    public TextField getTextFieldNewValue() { return textFieldNewValue;}
    public String getNewValue() { return textFieldNewValue.getText(); }
    public void close() { windown.close(); }
    public Stage getStage() { return windown; }

    /**
     * Creates all JavaFX components.
     *
     * @param owner parent window
     */
    private void initializeComponent(Stage owner){
        this.windown = new Stage();
        windown.initOwner(owner);
        this.layout = new VBox(SPACING);
        this.scene = new Scene(layout, WIDTH, HEIGHT);

        this.btnConfirm = new Button(BUTTON_CONFIR_TEXT);
        btnConfirm.setDisable(true);

        this.labelStatusText = new Label(STATUS_LABEL_TEXT);
        this.labelNewValueText = new Label(NEW_VALUE_LABEL_TEXT);

        this.labelCurrentStatus = new Label(String.valueOf(maGo.getStatus()));
        this.textFieldNewValue = new TextField();
        

        firstLine = new HBox(SPACING,labelStatusText,labelCurrentStatus);
        secondLine = new HBox(SPACING,labelNewValueText,textFieldNewValue);
    }

    /**
     * Builds and displays the control window.
     */
    private void initializeControlView() {
        windown.initModality(Modality.APPLICATION_MODAL);
        windown.setMinWidth(MIN_WIDTH);
        windown.setTitle("MaGo Control");
        
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(PADDING));
        layout.getChildren().addAll(firstLine, secondLine, btnConfirm);

        windown.setScene(scene);
        windown.show();
    }

}

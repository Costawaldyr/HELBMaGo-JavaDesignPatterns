package com.example.view;

import com.example.models.MaGoPopulation;
import com.example.utils.ColorUtils;
import com.example.interfaces.MaGoObserver;
import com.example.models.MaGo;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MainView implements MaGoObserver {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int SQUARE_SIZE = 28;
    private static final String TITLE = "HELBro";
    
    // Layout root
    private static final int ROOT_SPACING = 15;
    private static final int ROOT_PADDING = 20;

    // Top section
    private static final int TOP_SECTION_SPACING = 90;
    private static final int CARD_SPACING = 10;
    private static final int CARD_PADDING = 10;
    private static final double AVERAGE_CIRCLE_RADIUS = 14;
    private static final String POSITIVE_COLOR_LABEL = "Positive Color";
    private static final String NEGATIVE_COLOR_LABEL = "Negative Color";
    private static final String CARD_STYLE ="-fx-border-color: black;" + "-fx-border-radius: 10;" + "-fx-background-color: white;";
    
    // central section 
    private static final int CENTRAL_SECTION_SPACING = 15;
    private static final int CENTRAL_ELEMENT_HEIGHT = 160;
    private static final int MESSAGE_AREA_WIDTH = 350;
    private static final int BUTTON_WIDTH = 60;
    private static final int BUTTON_HEIGHT = 30;
    private static final int COUNTER_SPACING = 90;
    private static final int RADIO_SPACING = 10;
    private static final int RADIO_PADDING_VERTICAL = 20;
    private static final int RADIO_PADDING_HORIZONTAL = 15;
    private static final String RADIO_RANDOM = "Random";
    private static final String RADIO_GO_CIRCLE = "GoCirle";
    private static final String RADIO_GO_SQUARE = "GoSquare";
    private static final String RADIO_GO_ANY = "GoAny";
    private static final String DEFAULT_MESSAGE_TEXT = "This is a message";
    private static final String BUTTON_TEXT = "-->";
    private static final String DEFAULT_COUNTER_VALUE = "0";
    private static final String COUNTER_LABEL_STYLE ="-fx-border-color: black; -fx-background-color: white; -fx-alignment: center;";
    private static final String PANEL_BORDER_STYLE ="-fx-border-color: black;" + "-fx-border-radius: 15;" + "-fx-background-radius: 15;" + "-fx-background-color: white;";

    // Bottom section
    private static final int BOTTOM_CARD_WIDTH = 200;
    private static final int BOTTOM_CARD_HEIGHT = 150;
    private static final int BOTTOM_SECTION_PADDING = 15;
    private static final int BOTTOM_SECTION_SPACING = 20;
    private static final int SHAPE_BORDER_WIDTH = 2;
    public static final String TYPE_CIRCLE = "Circle";
    public static final String TYPE_SQUARE = "Square";

    private static final double MAX_SIZE = 80;
    private static final double MIN_SIZE = 45;
    private static final int BASE_POPULATION_FOR_MAX_SIZE = 2;
    private static final double SIZE_DECREASE_PER_MAGO = 6;
    private static final int CIRCLE_RADIUS_DIVISOR = 2;
    


    //TOP
    private static final Color AVERAGE_STATUS_DEFAULT_COLOR = Color.web("#F4A6A6");
    private static final Color NEGATIVE_COLOR = Color.BLUE;
    private static final Color POSITIVE_COLOR = Color.RED;
    private Circle averageStatusCircle;
    private Rectangle negativeColorRectangle;
    private Rectangle positiveColorRectangle;
    private Label averageStatusLabel;
    private Label negativeColorLabel;
    private Label positiveColorLabel;

    //CENTRAL
    private TextArea messageTextArea;
    private Label labelCounter;
    private Button buttonNext;
    private RadioButton randomModeRadio;
    private RadioButton circleModeRadio;
    private RadioButton squareModeRadio;
    private RadioButton anyModeRadio;

    private Scene scene;
    private final Stage stage;
    private final MaGoPopulation population;
    private List<MaGoView> maGoViewList;
    private List<VBox> maGoCardsList;

    /**
     * Creates the main application window.
     * Initializes all graphical components and builds the interface.
     *
     * @param primaryStage application's main stage
     * @param population MaGo population to display
     */
    public MainView(Stage primaryStage, MaGoPopulation population) {
        this.stage = primaryStage;
        this.population = population;
        this.maGoViewList = new ArrayList<MaGoView>();
        this.maGoCardsList = new ArrayList<>();

        initializeComponent();
        initializeView();
    }

    //GETTER
    public Button getNextButton() { return buttonNext; }
    public boolean isRandomSelected() { return randomModeRadio.isSelected(); }
    public boolean isCircleSelected() { return circleModeRadio.isSelected(); }
    public boolean isSquareSelected() { return squareModeRadio.isSelected(); }
    public boolean isAnySelected() { return anyModeRadio.isSelected(); }
    public VBox getMaGoCard(int index) { return maGoCardsList.get(index); }
    public Stage getStage() { return stage; }


    /**
     * Refreshes the interface after the population has changed.
     *
     * @param source updated object
     */
    @Override
    public void update(Object  source) {

        updateAverageStatus(population.getAverageStatus());

        for(MaGoView viewMaGo : maGoViewList) {
            viewMaGo.refresh();
        }
    }

    /**
     * Updates the average status indicator color.
     *
     * @param averageStatus current average status of the population
     */
    public void updateAverageStatus(int averageStatus) {
        averageStatusCircle.setFill(ColorUtils.getColor(averageStatus));
    }

    /**
     * Displays the selected message.
     *
     * @param message message to display
     */
    public void updateMessage(String message) {
        messageTextArea.setText(message);
    }

    /**
     * Updates the countdown displayed in the interface.
     *
     * @param seconds remaining time in seconds
     */
    public void updateCountdown(int seconds) {
        labelCounter.setText(String.valueOf(seconds));
    }

    /**
     * Builds the main window and displays it.
     */
    private void initializeView() {
    
        VBox root = new VBox(ROOT_SPACING);
        root.setPadding(new Insets(ROOT_PADDING));
        root.getChildren().addAll(createTopLayoutSection(),createCentralLayoutSection(),createBottomLayoutSection());

        scene = new Scene(root,WINDOW_WIDTH,WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.show();
    }

    /**
     * Creates and initializes all graphical components.
     */
    private void initializeComponent() {
        averageStatusCircle = new Circle(AVERAGE_CIRCLE_RADIUS, AVERAGE_STATUS_DEFAULT_COLOR);
        averageStatusLabel = new Label("Average Status");
        negativeColorRectangle = new Rectangle(SQUARE_SIZE, SQUARE_SIZE, NEGATIVE_COLOR);
        negativeColorLabel = new Label(NEGATIVE_COLOR_LABEL);
        positiveColorRectangle = new Rectangle(SQUARE_SIZE, SQUARE_SIZE, POSITIVE_COLOR);
        positiveColorLabel = new Label(POSITIVE_COLOR_LABEL);

        messageTextArea = new TextArea(DEFAULT_MESSAGE_TEXT);

        labelCounter = new Label(DEFAULT_COUNTER_VALUE);
        labelCounter.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        labelCounter.setStyle(COUNTER_LABEL_STYLE);

        buttonNext = new Button(BUTTON_TEXT);
        buttonNext.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);

        randomModeRadio = new RadioButton(RADIO_RANDOM);
        circleModeRadio = new RadioButton(RADIO_GO_CIRCLE);
        squareModeRadio = new RadioButton(RADIO_GO_SQUARE);
        anyModeRadio = new RadioButton(RADIO_GO_ANY);
    }


    /**
     * Creates the top section containing the average status
     * and the color legend.
     *
     * @return top layout
     */
    private HBox createTopLayoutSection() {
        HBox averageStatusCard = createRectCard(averageStatusCircle, averageStatusLabel);
        HBox colorLegendArea = createPositiveAndNegativeColorCard();

        HBox topLayout = new HBox(TOP_SECTION_SPACING);
        topLayout.setAlignment(Pos.CENTER);
        topLayout.getChildren().addAll(averageStatusCard,colorLegendArea);
        return topLayout;
    }

    /**
     * Creates the central section containing the message,
     * countdown and scrolling mode controls.
     *
     * @return central layout
     */
    private HBox createCentralLayoutSection() {
        HBox centralLayout = new HBox(CENTRAL_SECTION_SPACING);
        centralLayout.setAlignment(Pos.CENTER);
        centralLayout.getChildren().addAll(createMessageArea(),createCounterAndArrowButton(),createRadioPanel());
        return centralLayout;
    }

    /**
     * Creates the bottom section displaying all MaGos.
     *
     * @return bottom layout
     */
    private HBox createBottomLayoutSection() {

        HBox bottomLayout = new HBox(BOTTOM_SECTION_SPACING);
        bottomLayout.setAlignment(Pos.CENTER);
        bottomLayout.setPadding(new Insets(BOTTOM_SECTION_PADDING));
        bottomLayout.setStyle(PANEL_BORDER_STYLE);

        for (MaGo maGo : population.getMaGoList()) {

            Shape shape = createShape(maGo.getType());
            MaGoView maGoView = new MaGoView(maGo, shape);
            maGoViewList.add(maGoView);
            
            VBox card = createBottomCard(shape);
            maGoCardsList.add(card);
            bottomLayout.getChildren().add(card);
        }

        return bottomLayout;
    }

    //========================= Top ===================================
    /**
     * Creates the color legend displayed in the top section.
     *
     * @return color legend layout
     */
    private HBox createPositiveAndNegativeColorCard() {
        HBox negativeColorCard = createRectCard(negativeColorRectangle, negativeColorLabel);
        HBox positiveColorCard = createRectCard(positiveColorRectangle, positiveColorLabel);
        HBox colorLegendArea = new HBox(CARD_SPACING);
        colorLegendArea.getChildren().addAll(negativeColorCard, positiveColorCard);
        return colorLegendArea;
    }

    /**
     * Creates a small card composed of a graphic node and a label.
     *
     * @param objNode graphical element
     * @param label associated label
     * @return created card
     */
    private HBox createRectCard(Node objNode, Label label) {
        HBox horizontalBox = new HBox(CARD_SPACING);
        horizontalBox.getChildren().addAll(objNode, label);
        horizontalBox.setAlignment(Pos.CENTER_LEFT);
        horizontalBox.setPadding(new Insets(CARD_PADDING));
        horizontalBox.setStyle(CARD_STYLE);
        return horizontalBox;
    }

    //====================== Central ===============================
    /**
     * Creates the message display area.
     *
     * @return message text area
     */
    private TextArea createMessageArea() {

        messageTextArea.setWrapText(true);
        messageTextArea.setEditable(false);
        messageTextArea.setPrefSize(MESSAGE_AREA_WIDTH, CENTRAL_ELEMENT_HEIGHT);
        return messageTextArea;
    }

    /**
     * Creates the countdown label and the next message button.
     *
     * @return layout containing both controls
     */
    private VBox createCounterAndArrowButton() {
        VBox counterAndArrowBox = new VBox(COUNTER_SPACING);
        counterAndArrowBox.setAlignment(Pos.CENTER);
        counterAndArrowBox.getChildren().addAll(labelCounter, buttonNext);
        return counterAndArrowBox;
    }

    /**
     * Creates the panel used to select the message
     * scrolling strategy.
     *
     * @return radio button panel
     */
    private VBox createRadioPanel() {
        ToggleGroup group = new ToggleGroup();

        randomModeRadio.setToggleGroup(group);
        circleModeRadio.setToggleGroup(group);
        squareModeRadio.setToggleGroup(group);
        anyModeRadio.setToggleGroup(group);
        randomModeRadio.setSelected(true);

        VBox radioPanel = new VBox(RADIO_SPACING);
        radioPanel.setAlignment(Pos.CENTER_LEFT);
        radioPanel.setPadding(new Insets(RADIO_PADDING_VERTICAL, RADIO_PADDING_HORIZONTAL,RADIO_PADDING_VERTICAL, RADIO_PADDING_HORIZONTAL));
        radioPanel.setStyle(PANEL_BORDER_STYLE);
        radioPanel.getChildren().addAll(randomModeRadio, circleModeRadio, squareModeRadio, anyModeRadio);
        return radioPanel;
    }

    //========================== Bottom ===================================
    /**
     * Creates a card containing a MaGo shape.
     *
     * @param objNode MaGo graphical representation
     * @return MaGo card
     */
    private VBox createBottomCard(Node objNode) {
        VBox bottomCard = new VBox();
        bottomCard.setPrefSize(BOTTOM_CARD_WIDTH,BOTTOM_CARD_HEIGHT);
        bottomCard.setAlignment(Pos.CENTER);
        bottomCard.setStyle(PANEL_BORDER_STYLE);
        bottomCard.getChildren().add(objNode);
        return bottomCard;
    }

    private double getShapeSize() {

        int numberOfMaGos = population.getNumberOfMaGos();
        double size = MAX_SIZE - (numberOfMaGos - BASE_POPULATION_FOR_MAX_SIZE) * SIZE_DECREASE_PER_MAGO;
        return Math.max(size, MIN_SIZE);
    }

    /**
     * Creates the graphical representation of a MaGo
     * according to its type.
     *
     * @param type MaGo type
     * @return corresponding shape
     */
    private Shape createShape(String type) {

        double size = getShapeSize();
        Shape shape;

        switch (type) {
            case TYPE_CIRCLE:
                shape = new Circle(size / CIRCLE_RADIUS_DIVISOR);
                break;
            case TYPE_SQUARE:
                shape = new Rectangle(size, size);
                break;
            default:
                throw new IllegalArgumentException("Unknown MaGo type : " + type);
        }

        shape.setStroke(Color.BLACK);
        shape.setStrokeWidth(SHAPE_BORDER_WIDTH);
        return shape;
    }

    
}

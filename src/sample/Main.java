package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Created by Marius on 1/9/2017.
 */

public class Main extends Application {


    private Timeline timeline = new Timeline();
    private static CycleOfLife cycleOfLife;
    private double speed = 0.04;


    public Parent createView() throws IOException {

        Pane root = new Pane();
        root.setPrefSize(WindowSize.WIDE, WindowSize.HIGH + 50);

        MyHbox myHbox = new MyHbox(timeline);
        HBox hBox = myHbox.gethBox();

        Group allLife = new Group();
        ObservableList<Node> life = allLife.getChildren();

        KeyFrame frame = new KeyFrame(Duration.seconds(speed), (ActionEvent event) -> {
            life.clear();
            cycleOfLife = myHbox.getCycleOfLife();
            life.addAll(cycleOfLife.getBoard());

            /** Print number of active cells*/
            /*System.out.println(cycleOfLife.getBoard().stream().filter(block ->
                    Color.BLACK.equals(block.getFill()) || Color.LIGHTGREEN.equals(block.getFill())).count());*/
        });

        timeline.getKeyFrames().addAll(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        root.getChildren().addAll(hBox, allLife);
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Game of Life");
        Scene scene = new Scene(createView());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

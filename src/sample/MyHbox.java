package sample;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marius on 1/9/2017.
 */
public class MyHbox implements EventHandler<ActionEvent>{

    private Timeline timeline;
    private HBox hBox;
    private Button button_pause, button_random, button_acorn, button_glider_gun,
            button_cells_plus, button_cells_minus, button_grid_size_plus, button_grid_size_minus;
    CycleOfLife cycleOfLife = new CycleOfLife();
    private Point grid_size = new Point();


    public MyHbox(Timeline timeline) {
        this.timeline = timeline;
        cycleOfLife.createRandomBoard();
        setButtons();
    }

    private void setButtons(){

        hBox = new HBox();
        hBox.setSpacing(20);
        hBox.setTranslateY(WindowSize.HIGH);
        hBox.setPrefSize(WindowSize.WIDE + 10, 60);
        hBox.setStyle("-fx-background-color: #8c848f; -fx-border-color: dimgray;");
        hBox.setAlignment(Pos.CENTER);
        grid_size.setLocation(WindowSize.WIDE / Block.getBlockSize(), WindowSize.HIGH / Block.getBlockSize());

        button_pause = new Button("Pause");
        button_random = new Button("Random");
        button_acorn = new Button("Acorn");
        button_glider_gun = new Button("Glider Gun");
        button_cells_plus = new Button("Living cells (+) " + 100 / cycleOfLife.getChanceOfLife() + "%");
        button_cells_minus = new Button("Living cells (-) " + 100 / cycleOfLife.getChanceOfLife() + "%");
        button_grid_size_plus = new Button("Grid size (+) " + (int)grid_size.getX() + "x" + (int)grid_size.getY());
        button_grid_size_minus = new Button("Grid size (-) " + (int)grid_size.getX() + "x" + (int)grid_size.getY());


        button_pause.setPrefSize(100, 40);button_pause.setStyle("-fx-font: 14 arial; -fx-base: #7ec5e7;");
        button_random.setPrefSize(100, 40);button_random.setStyle("-fx-font: 14 arial; -fx-base: #b6e7c9;");
        button_acorn.setPrefSize(100, 40);button_acorn.setStyle("-fx-font: 14 arial; -fx-base: #b6e7c9;");
        button_glider_gun.setPrefSize(100, 40);button_glider_gun.setStyle("-fx-font: 14 arial; -fx-base: #b6e7c9;");
        button_cells_plus.setPrefSize(120, 40);button_cells_plus.setStyle("-fx-font: 10 arial; -fx-base: #34e721;");
        button_cells_minus.setPrefSize(120, 40);button_cells_minus.setStyle("-fx-font: 10 arial; -fx-base: #e77a71;");
        button_grid_size_plus.setPrefSize(120, 40);button_grid_size_plus.setStyle("-fx-font: 10 arial; -fx-base: #b6e7c9;");
        button_grid_size_minus.setPrefSize(120, 40);button_grid_size_minus.setStyle("-fx-font: 10 arial; -fx-base: #b6e7c9;");

        hBox.getChildren().addAll(button_pause, button_random, button_acorn, button_glider_gun,button_cells_plus,
                button_cells_minus, button_grid_size_plus, button_grid_size_minus);

        button_pause.setOnAction(this);
        button_random.setOnAction(this);
        button_acorn.setOnAction(this);
        button_glider_gun.setOnAction(this);
        button_cells_plus.setOnAction(this);
        button_cells_minus.setOnAction(this);
        button_grid_size_plus.setOnAction(this);
        button_grid_size_minus.setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
        int size = Block.getBlockSize();
        int chance = cycleOfLife.getChanceOfLife();

        if (event.getSource().equals(button_pause)) {
            String text;
            if (button_pause.getText().equals("Pause")){
                timeline.pause();
                text = "Play";
            }
            else {
                timeline.play();
                text = "Pause";
            }
            button_pause.setText(text);
        }
        else if (event.getSource().equals(button_random)){
            cycleOfLife.createRandomBoard();
        }
        else if (event.getSource().equals(button_acorn)){
            cycleOfLife.createAcornBoard();
        }
        else if (event.getSource().equals(button_glider_gun)){
            cycleOfLife.createGliderGunBoard();
        }
        else if (event.getSource().equals(button_cells_plus)){
            cycleOfLife.setChanceOfLife(chance - 1);
            if (cycleOfLife.getChanceOfLife() == chance - 1) {
                cycleOfLife.createRandomBoard();
                button_cells_plus.setText("Living cells (+) " + 100 / cycleOfLife.getChanceOfLife() + "%");
                button_cells_minus.setText("Living cells (-) " + 100 / cycleOfLife.getChanceOfLife() + "%");
            }
        }
        else if (event.getSource().equals(button_cells_minus)){
            cycleOfLife.setChanceOfLife(chance + 1);
            if (cycleOfLife.getChanceOfLife() == chance + 1) {
                cycleOfLife.createRandomBoard();
                button_cells_minus.setText("Living cells (-) " + 100 / cycleOfLife.getChanceOfLife() + "%");
                button_cells_plus.setText("Living cells (+) " + 100 / cycleOfLife.getChanceOfLife() + "%");
            }
        }
        else if (event.getSource().equals(button_grid_size_plus)){
            Block.setBlockSize(size - 1);
            if (Block.getBlockSize() == size - 1) {
                reCreateCurrentTypeOfBoard();
                grid_size.setLocation(WindowSize.WIDE / Block.getBlockSize(), WindowSize.HIGH / Block.getBlockSize());
                button_grid_size_plus.setText("Grid size (+) " + (int) grid_size.getX() + "x" + (int) grid_size.getY());
                button_grid_size_minus.setText("Grid size (-) " + (int) grid_size.getX() + "x" + (int) grid_size.getY());
            }
        }
        else if (event.getSource().equals(button_grid_size_minus)){
            Block.setBlockSize(size + 1);
            if (Block.getBlockSize() == size + 1) {
                reCreateCurrentTypeOfBoard();
                grid_size.setLocation(WindowSize.WIDE / Block.getBlockSize(), WindowSize.HIGH / Block.getBlockSize());
                button_grid_size_plus.setText("Grid size (+) " + (int) grid_size.getX() + "x" + (int) grid_size.getY());
                button_grid_size_minus.setText("Grid size (-) " + (int) grid_size.getX() + "x" + (int) grid_size.getY());
            }
        }
    }

    private void reCreateCurrentTypeOfBoard(){
        int chance = cycleOfLife.getChanceOfLife();
        if (cycleOfLife.isAcorn()) {
            cycleOfLife = new CycleOfLife();
            cycleOfLife.setChanceOfLife(chance);
            cycleOfLife.createAcornBoard();
        }
        else if (cycleOfLife.isRandom()) {
            cycleOfLife = new CycleOfLife();
            cycleOfLife.setChanceOfLife(chance);
            cycleOfLife.createRandomBoard();
        }
        else if (cycleOfLife.isGliderGun()) {
            cycleOfLife = new CycleOfLife();
            cycleOfLife.setChanceOfLife(chance);
            cycleOfLife.createGliderGunBoard();
        }
    }

    public HBox gethBox() {
        return hBox;
    }

    public CycleOfLife getCycleOfLife() {
        return cycleOfLife;
    }

    public void setCycleOfLife(CycleOfLife cycleOfLife) {
        this.cycleOfLife = cycleOfLife;
    }
}

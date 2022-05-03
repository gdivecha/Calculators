/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gaussjordancalculatorproject;

import javafx.scene.image.Image;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.ArrayList;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 *
 * @author Gaurav Divecha
 */

public class GaussJordanCalculatorProject extends Application{

    Stage window;
    
    /**
     * @param args the command line arguments
     */
    @Override
    public void start(Stage primarystage) throws Exception{
        window = primarystage;
        window.getIcons().clear();
        Image image = new Image("GJCalc.jpg");
        window.getIcons().add(image);
        window.setTitle("Gauss-Jordan Calculator");
        setUpMatrixScreen(primarystage);
    }
    
    public void setUpMatrixScreen(Stage primarystage){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        
        BorderStroke borderStroke = new BorderStroke(Color.SLATEGREY, BorderStrokeStyle.SOLID, null,
                        new BorderWidths(2)); 
        Border border = new Border(borderStroke);
        
        Label rows = new Label("ROWS:");
        Label cols = new Label("COLUMNS:");
        
        rows.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 14));
        cols.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 14));
        
        TextField rowsInput = new TextField();
        TextField colsInput = new TextField();
        
        rowsInput.setPromptText("#");
        colsInput.setPromptText("#");
        
        rowsInput.setMaxSize(60, 60);
        colsInput.setMaxSize(60, 60);
        
        rowsInput.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        rowsInput.setBorder(border);
        
        colsInput.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        colsInput.setBorder(border);
        
        Button setup = new Button("Setup");
        setup.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        setup.setBorder(border);
        
        GridPane.setConstraints(rows, 0, 0);
        GridPane.setConstraints(cols, 3, 0);
        GridPane.setConstraints(rowsInput, 1, 0);
        GridPane.setConstraints(colsInput, 4, 0);
        GridPane.setConstraints(setup, 4, 3);
        
        grid.getChildren().addAll(rows,cols,rowsInput,colsInput,setup);
        grid.setAlignment(Pos.CENTER);
        grid.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY, Insets.EMPTY)));
        grid.setBorder(border);
        Scene scene = new Scene(grid, 400, 150);
        scene.setFill(Color.DIMGRAY);
        window.setScene(scene);
        window.show();
        
        
        setup.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if((Integer.parseInt(rowsInput.getText()))!=(Integer.parseInt(colsInput.getText()))){
                    errorMessageScreen(primarystage, 1);
                }
                else if(((Integer.parseInt(rowsInput.getText()))==(Integer.parseInt(colsInput.getText())))&&((Integer.parseInt(rowsInput.getText())))<=0){
                    errorMessageScreen(primarystage, 3);
                }
                else{
                    calculatorScreen(primarystage, Integer.parseInt(rowsInput.getText()));
                }
            }
        }
        );
    }
    
    public void answer(Stage primarystage, ArrayList<Double> values){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        
        BorderStroke borderStroke = new BorderStroke(Color.SLATEGREY, BorderStrokeStyle.SOLID, null,
                        new BorderWidths(2)); 
        Border border = new Border(borderStroke);
        
        ArrayList<Label> variables = new ArrayList<Label>();
        ArrayList<Label> varNames = new ArrayList<Label>();

        Label answer = new Label("The values are \nas follows:");
        answer.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 14));
        
        GridPane.setConstraints(answer, 0, 0);
        
        for(int i=0; i<values.size(); i++){
            variables.add(new Label("" + values.get(i)));
            variables.get(i).setMaxSize(100, 60);
            variables.get(i).setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 14));
        }
        for(int i=0; i<values.size(); i++){
            varNames.add(new Label("Var #" + (i+1) + ":"));
            varNames.get(i).setMaxSize(60, 60);
            varNames.get(i).setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 14));
        }
        for(int i=0; i<values.size(); i++){
            GridPane.setConstraints(varNames.get(i), 0, (i+1));
            GridPane.setConstraints(variables.get(i), 1, (i+1));
        }
        grid.getChildren().add(answer);
        for(int i=0; i<values.size(); i++){
            grid.getChildren().addAll(variables.get(i), varNames.get(i));
        }
        Button back = new Button("Back");
        back.setPrefSize(60, 30);
        back.setBorder(border);
        back.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY, Insets.EMPTY)));
        
        GridPane.setConstraints(back, 0, values.size()+1);
        grid.getChildren().add(back);
        
        grid.setVgap(10);
        
        grid.setAlignment(Pos.CENTER);
        grid.setBorder(border);
        Scene scene = new Scene(grid, 400, (values.size()*50 + 100));
        
        window.setScene(scene);
        window.show();
        
        back.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setUpMatrixScreen(primarystage);
            }
        }
        );
    }
    
    public void errorMessageScreen(Stage primarystage, int type){
        BorderStroke borderStroke = new BorderStroke(Color.SLATEGREY, BorderStrokeStyle.SOLID, null,
                        new BorderWidths(2)); 
        Border border = new Border(borderStroke);
        
        GridPane grid = new GridPane();
        Label message = null;
        Button back = new Button("Back");
        back.setPrefSize(60, 30);
        back.setBorder(border);
        back.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        
        switch(type){
            case 1:
                message = new Label("Please enter the same number \nof rows and columns");                            //If rows!=cols
                break;
            case 2:
                message = new Label("This matrix has invalid/no solutions");                                                  //if divided by 0 error OR has multiple solutions        
                break;
            case 3:                                                                                                   //column/row size of <=0
                message = new Label("Row/Column sizes expected >= 0"); 
                break;
        }
        
        message.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 14));
        
        GridPane.setConstraints(message, 0, 0);
        GridPane.setConstraints(back, 0, 2);
        
        grid.setVgap(10);
        
        back.setAlignment(Pos.CENTER);
        back.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY, Insets.EMPTY)));
        back.setPrefSize(60, 30);
        back.setBorder(border);
        
        grid.getChildren().add(message);
        grid.getChildren().add(back);
        grid.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        
        grid.setAlignment(Pos.CENTER);
        grid.setBorder(border);
        Scene scene = new Scene(grid, 400, 200);
        scene.setFill(Color.SLATEGREY);
        window.setScene(scene);
        window.show();
        
        back.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setUpMatrixScreen(primarystage);
            }
        }
        );
    }
    
    public void calculatorScreen(Stage primaryStage, int num){
        ArrayList<TextField> inputs = new ArrayList<TextField>();
        ArrayList<TextField> values = new ArrayList<TextField>();
        ArrayList<Label> partitions = new ArrayList<Label>();
        
        BorderStroke borderStroke = new BorderStroke(Color.SLATEGREY, BorderStrokeStyle.SOLID, null,
                        new BorderWidths(2)); 
        Border border = new Border(borderStroke);
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        
        int n = num;
        
        //FOR COEFFICIENTS
        for(int i=0; i<(n*n); i++){
            inputs.add(new TextField());
            inputs.get(i).setPromptText("#");
            inputs.get(i).setMaxSize(60, 60);
            inputs.get(i).setBorder(border);
            inputs.get(i).setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        for(int i=0; i<(n*n); i++){
            GridPane.setConstraints(inputs.get(i), (i%n), ((i/n)+1));
        }
        for(int i=0; i<(n*n); i++){
            grid.getChildren().add(inputs.get(i));
        }
        
        //FOR PARTITIONS
        for(int i=0; i<n; i++){
            partitions.add(new Label("=  "));
        }
        for(int i=0; i<n; i++){
            GridPane.setConstraints(partitions.get(i), n+1, i+1);
        }
        for(int i=0; i<n; i++){
            grid.getChildren().add(partitions.get(i));
        }
        
        //FOR RESULTS
        for(int i=0; i<n; i++){
            values.add(new TextField());
            values.get(i).setPromptText("#");
            values.get(i).setMaxSize(60, 60);
            values.get(i).setBorder(border);
            values.get(i).setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        for(int i=0; i<n; i++){
            GridPane.setConstraints(values.get(i), n+2, i+1);
        }
        for(int i=0; i<n; i++){
            grid.getChildren().add(values.get(i));
        }
        
        Button solve = new Button("solve");
        solve.setPrefSize(60, 30);
        solve.setBorder(border);
        solve.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        
        Button back = new Button("Back");
        back.setPrefSize(60, 30);
        back.setBorder(border);
        back.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        
        grid.add(solve, n+2, n+4);
        grid.add(back, 0, n+4);
        
        grid.setAlignment(Pos.CENTER);
        int sizeMarker = (125*n);
        if(sizeMarker<400){
            sizeMarker = 400;
        }
        grid.setBorder(border);
        grid.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(grid, sizeMarker, 85*n+100);
        window.setScene(scene);
        window.show();
        
        final double[][] matrixToSolve = new double[n][n];
        final double[] results = new double[n];
        solve.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent a) {
                int count = 0;
                for(int i=0; i<n; i++){
                    for(int j=0; j<n; j++){
                        matrixToSolve[i][j] = Double.parseDouble(inputs.get(count).getText());
                        count++;
                    } 
                }
                for(int i=0; i<n; i++){
                    results[i] = Double.parseDouble(values.get(i).getText());
                }
                try{
                    ArrayList<Double> finalValues = GJCalculator.findColDeterminant(matrixToSolve, results);
                    answer(primaryStage, finalValues);
                }
                catch(Exception e){
                    errorMessageScreen(window, 2);
                }
            }
        }
        );
        back.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setUpMatrixScreen(window);
            }
        }
        );
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
package sample;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
    Boolean Flag=true;
private TextField [][]textField;
private Label [][]label;
          @FXML
         private JFXTextField NID;
          @FXML
         private GridPane gp;
         @FXML
         private GridPane MO;
          private  int[][] maze ;
    @FXML
    private GridPane s1;

    @FXML
    private GridPane s2;

    @FXML
    private GridPane s3;





    @FXML
    public void EID(ActionEvent event) {
      String str =  NID.getText();
      gp.getChildren().clear();
      gp.setHgap(3);
      gp.setVgap(3);
      gp.setAlignment(Pos.CENTER);
      try{
            int N = Integer.parseInt(str);
            textField= new TextField[N][N];

            for (int i = 0; i < N; i++) {
                for (int m = 0; m < N; m++) {
                    TextField textField1=new TextField("0");
                    gp.add(textField[i][m]=textField1,i,m);
                }
            }
        }
        catch (NumberFormatException ex){

            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setContentText("ERROR you don't enter Integer");
            a.show();


        }


    }
    @FXML
    void SED(ActionEvent event) throws IOException {
        String str =  NID.getText();
        try{
            int N = Integer.parseInt(str);
             maze = new int[N][N];
            int value;
            for (int i = 0; i < N; i++) {
                for (int m = 0; m < N; m++) {
                    value= Integer.parseInt(textField[i][m].getText());

                    if (value == 0 || value == 1) {
                        maze[i][m]=value;

                    }else {
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText(" Error value not excpted\nreturn enter value change value to scan it \n"+
                                "maze[" + i + "][" + m + "]" + ": ");
                        a.show(); }
                    }
                }



            int[][] sol = new int[N][N];
            for (int i = 0; i < N; i++) {  //to mack all value in sol matrix equal zero before work;
                for (int m = 0; m < N; m++) {
                    sol[i][m] = 0;
                }
            }

            if (Flag){
                makeMaze X = new makeMaze(N);
                System.out.print(sol[2][0]);
                X.setMaze(maze);
                X.getMaze();
                solveMaze solveMaze = new solveMaze(N, maze, sol, 0, 0);
                Thread t0 = new Thread(solveMaze);
                t0.start();
                t0.join();
                int printsol[][]=solveMaze.getSol();

                s2.getChildren().clear();
                s2.setHgap(3);
                s2.setVgap(3);
                s2.setAlignment(Pos.CENTER);
                for (int i = 0; i < N; i++) {  //to mack all value in sol matrix equal zero before work;
                    for (int m = 0; m < N; m++) {
                        Button btn = new Button("     ");
                        if(printsol[i][m]==1){
                            btn.getStyleClass().add("btn1");
                            s2.add( btn,i,m);
                        }else {

                            btn.getStyleClass().add("btn2");
                            s2.add( btn,i,m);
                        } }
                }
if(sol[N-1][N-1]==1) {
    int printsolop[][] = solveMaze.getSolop();

    s3.getChildren().clear();
    s3.setHgap(3);
    s3.setVgap(3);
    s3.setAlignment(Pos.CENTER);
    for (int i = 0; i < N; i++) {  //to mack all value in sol matrix equal zero before work;
        for (int m = 0; m < N; m++) {
            Button btn = new Button("     ");
            if (printsolop[i][m] == 1) {
                btn.getStyleClass().add("btn1");
                s3.add(btn, i, m);
            } else {

                btn.getStyleClass().add("btn2");
                s3.add(btn, i, m);
            }
        }
    }
}else  s3.getChildren().clear();

            }

        }
        catch (NumberFormatException | InterruptedException ex){
            ex.printStackTrace();
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setContentText("ERORR");
            a.show();
        }
        s1.getChildren().clear();
        s1.setHgap(3);
        s1.setVgap(3);
        s1.setAlignment(Pos.CENTER);
        int N = Integer.parseInt(str);

        for (int i = 0; i < N; i++) {  //to mack all value in sol matrix equal zero before work;
            for (int m = 0; m < N; m++) {
                Button btn = new Button("     ");
                if(maze[i][m]==1){
                    btn.getStyleClass().add("btn1");
                    s1.add( btn,i,m);
                }else {

                    btn.getStyleClass().add("btn2");
                    s1.add( btn,i,m);
                } }
        }


        }
}
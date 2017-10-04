package com.MC.Efficient;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;

public class SimulatorController implements Initializable{

	
	@FXML public Canvas myCanvas;
	@FXML public Button CreateScene;
	
	@FXML public TextField numText;
	@FXML public TextField radiusText;
	@FXML public TextField moveText;
	@FXML public TextField requiredLower;
	@FXML public TextField requiredUpper;
	@FXML public TextField transferText;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		myCanvas.setOnScroll(new EventHandler<ScrollEvent>(){

			double zoom = 1.0;
			
			@Override
			public void handle(ScrollEvent event) {
				// TODO Auto-generated method stub
				if(event.getDeltaY()>0)
				{
					System.out.println("이건 위로");
					zoom *= 1.2;
				}
				else
				{
					System.out.println("이건 아래로");
					zoom /= 1.2;
				}
				
				myCanvas.setScaleX(zoom);
				myCanvas.setScaleY(zoom);
			}
			
		});
		
		CreateScene.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(numText.getText().equals("0") || radiusText.getText().equals("0") || moveText.getText().equals("0") || requiredUpper.getText().equals("0") || 
						transferText.getText().equals("0"))
				{
					// 안됨
					System.out.println("Wrong Input");
				}
				else
				{
					for(int i=0;i<Integer.valueOf(numText.getText());i++)
					{
						double tmp_x = Math.random() * 300;
						double tmp_y = Math.random() * 300;
						
						GraphicsContext gc = myCanvas.getGraphicsContext2D();
						
						gc.setFill(Color.BLACK);
						
						gc.fillOval(tmp_x, tmp_y, 4, 4);
					}
				}
			}
			
		});
	}
	
}

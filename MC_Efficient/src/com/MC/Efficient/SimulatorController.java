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
	
	public Cluster myCluster = null;
	// ���� Charger�� ������ Cluster ��ü
	
	public static final double centerX = 450;
	public static final double centerY = 360;
	// (450,360) ������ �߽������̴�.
	
	public double clusterRadius = 0;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		myCanvas.setOnScroll(new EventHandler<ScrollEvent>(){

			double zoom = 1.0;
			
			@Override
			public void handle(ScrollEvent event) {
				// TODO Auto-generated method stub
				
				/*
				if(event.getDeltaY()>0)
				{
					System.out.println("�̰� ����");
					zoom *= 1.2;
				}
				else
				{
					System.out.println("�̰� �Ʒ���");
					zoom /= 1.2;
				}
				
				myCanvas.setScaleX(zoom);
				myCanvas.setScaleY(zoom);
				*/
				if(event.getDeltaY()>0)
				{
					System.out.println("�̰� ����");
					zoom *= 1.2;
				}
				else
				{
					System.out.println("�̰� �Ʒ���");
					zoom /= 1.2;
				}
				
				myCanvas.getGraphicsContext2D().clearRect(0, 0, 900, 720);
				
				myCluster.ChangeDrawRadius(zoom);
				myCluster.Draw(myCanvas.getGraphicsContext2D());
				
			}
			
		});
		
		CreateScene.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
								
				if(isCorrect()==true) // ��ȿ�ϴٸ�
				{
					myCanvas.getGraphicsContext2D().clearRect(0, 0, 900, 720);
					myCluster = new Cluster();
					
					Charger tmpCharger = new Charger(centerX, centerY, Double.parseDouble(moveText.getText()),Double.valueOf(transferText.getText()));
					// �ӽ÷� tmpCharger�� �����
					
					myCluster.SetCharger(tmpCharger);
					// �� Charger�� Cluster�� �ִ´�.
					
					clusterRadius = Double.valueOf(radiusText.getText());
					// �������� ��������
					
					// �� ���̱� ���ؼ� 20�踦 �ϵ��� �Ѵ�.
					
					clusterRadius *= 20;
					
					myCluster.SetRadius(clusterRadius);
					// Cluster�� ����
					
					
					for(int i=0;i<Integer.valueOf(numText.getText());i++)
					{
						double randomAngle = Math.random() * 360; // 0~360������ ����
						
						double tmp_x = centerX + clusterRadius * Math.cos(Math.toRadians(randomAngle));
						
						double tmp_y = centerY + clusterRadius * Math.sin(Math.toRadians(randomAngle));
						
						// tmp_x, tmp_y ������ ��带 �����ϰ�, �̰� ���� �ȴ�.
						
						double lower = Double.valueOf(requiredLower.getText());
						double upper = Double.valueOf(requiredUpper.getText());
						
						double requiredEnergy_tmp = Math.random() * (upper-lower) + lower;
						
						System.out.println(requiredEnergy_tmp + " �䱸 ���� ");
						
						myCluster.AddNode(new Node(tmp_x,tmp_y,requiredEnergy_tmp,randomAngle));
					}
					
					
					GraphicsContext gc = myCanvas.getGraphicsContext2D();
					
					myCluster.Draw(gc);
					// �׸��� �׸���.
				}
			}
			
		});
	}
	
	
	public boolean isCorrect()
	{
		if(numText.getText().equals("0") || radiusText.getText().equals("0") || moveText.getText().equals("0") || requiredUpper.getText().equals("0") || 
				transferText.getText().equals("0"))
		{
			// �ȵ�
			System.out.println("Wrong Input");
			return false;
		}
		return true;
	}
	
}

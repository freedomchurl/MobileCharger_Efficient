package com.MC.Efficient;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Cluster {

	
	public Charger centerCharger = null;
	// ��� �������ִ� Charger.
	
	public ArrayList<Node> chargedNodes = new ArrayList<Node>();
	// ������ �����ϴ� ArrayList
	
	public double Radius = 0;
	
	public double DrawRadius = 0;
	
	Cluster()
	{
		// ?
	}
	
	
	public void Cal()
	{
		
	}
	
	public void AddNode(Node input)
	{
		this.chargedNodes.add(input); 
		// ��带 �߰��Ѵ�.
	}
	
	public void SetCharger(Charger input)
	{
		this.centerCharger = input;
	}
	
	public void SetRadius(double r)
	{
		this.Radius = r;
		this.DrawRadius = r;
	}
	
	public boolean checkSameNode(Node input)
	{
		for(int i=0;i<chargedNodes.size();i++)
		{
			// �� �κ� �Ÿ��� 0���� �� ������ �� ���� �� ����. �׷��� ������� �������� ������ ����
			if(input.location.distance(chargedNodes.get(i).location) == 0)
			{ 
				return true;
			}
		}
		return false;
	}
	
	public void Draw(GraphicsContext gc)
	{
		gc.setFill(Color.RED);
		// �߽��� ���������� ǥ���Ѵ�.
		gc.fillOval(centerCharger.x_pos-3, centerCharger.y_pos-3, 6, 6);
		
		gc.setFill(Color.BLUE);
		// ������ ������
		for(int i=0;i<chargedNodes.size();i++)
		{
			gc.fillOval(chargedNodes.get(i).x_pos-3, chargedNodes.get(i).y_pos-3, 6, 6);
		}
		
		gc.setFill(Color.BLACK);
		// ���� ������
		gc.strokeOval(centerCharger.x_pos-DrawRadius, centerCharger.y_pos-DrawRadius, 2*DrawRadius,2*DrawRadius);
	}
	
	public void ChangeDrawRadius(double rate)
	{
		DrawRadius = Radius * rate;
		// DrawRadius ������ �����ϰ�
		
		for(int i=0;i<chargedNodes.size();i++)
		{
			chargedNodes.get(i).x_pos = centerCharger.x_pos + DrawRadius * Math.cos(Math.toRadians(chargedNodes.get(i).Angle));
			
			chargedNodes.get(i).y_pos = centerCharger.y_pos + DrawRadius * Math.sin(Math.toRadians(chargedNodes.get(i).Angle));
		}
	}
}

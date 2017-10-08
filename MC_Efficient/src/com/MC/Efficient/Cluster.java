package com.MC.Efficient;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Cluster {

	
	public Charger centerCharger = null;
	// 가운데 충전해주는 Charger.
	
	public ArrayList<Node> chargedNodes = new ArrayList<Node>();
	// 노드들을 충전하는 ArrayList
	
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
		// 노드를 추가한다.
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
			// 이 부분 거리를 0에서 더 수정할 수 있을 것 같다. 그러면 어느정도 범위내로 수정이 가능
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
		// 중심은 빨간색으로 표현한다.
		gc.fillOval(centerCharger.x_pos-3, centerCharger.y_pos-3, 6, 6);
		
		gc.setFill(Color.BLUE);
		// 노드들은 검정색
		for(int i=0;i<chargedNodes.size();i++)
		{
			gc.fillOval(chargedNodes.get(i).x_pos-3, chargedNodes.get(i).y_pos-3, 6, 6);
		}
		
		gc.setFill(Color.BLACK);
		// 원은 검정색
		gc.strokeOval(centerCharger.x_pos-DrawRadius, centerCharger.y_pos-DrawRadius, 2*DrawRadius,2*DrawRadius);
	}
	
	public void ChangeDrawRadius(double rate)
	{
		DrawRadius = Radius * rate;
		// DrawRadius 비율을 조절하고
		
		for(int i=0;i<chargedNodes.size();i++)
		{
			chargedNodes.get(i).x_pos = centerCharger.x_pos + DrawRadius * Math.cos(Math.toRadians(chargedNodes.get(i).Angle));
			
			chargedNodes.get(i).y_pos = centerCharger.y_pos + DrawRadius * Math.sin(Math.toRadians(chargedNodes.get(i).Angle));
		}
	}
}

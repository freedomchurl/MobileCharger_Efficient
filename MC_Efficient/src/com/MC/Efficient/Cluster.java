package com.MC.Efficient;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Cluster {

	
	public Charger centerCharger = null;
	// 가운데 충전해주는 Charger.
	
	public ArrayList<Node> chargedNodes = new ArrayList<Node>();
	// 노드들을 충전하는 ArrayList
	
	public double Radius = 0;
	
	public double DrawRadius = 0;
	
	public double Radius20 = 0;
	
	public double singleDistanceResult = 0;
	public double singleEnergyResult = 0;
	public double multiEnergyResult = 0;
	
	public double singleTime = 0;
	public double multiTime = 0;
	
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
		this.Radius20 = 20 * r;
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
		DrawRadius = Radius20 * rate;
		// DrawRadius 비율을 조절하고
		
		for(int i=0;i<chargedNodes.size();i++)
		{
			chargedNodes.get(i).x_pos = centerCharger.x_pos + DrawRadius * Math.cos(Math.toRadians(chargedNodes.get(i).Angle));
			
			chargedNodes.get(i).y_pos = centerCharger.y_pos + DrawRadius * Math.sin(Math.toRadians(chargedNodes.get(i).Angle));
		}
	}
	
	public void Run()
	{
		this.singleDistanceResult = 0;
		this.singleEnergyResult = 0;
		this.multiEnergyResult = 0;
		this.multiTime = 0;
		this.singleTime = 0;
		
		double centerfriisResult = centerCharger.TransferAntennaGain * chargedNodes.get(0).ReceiveAntennaGain * chargedNodes.get(0).Retenna
				/ centerCharger.polarizationLoss * Math.pow((centerCharger.WaveLength/(this.Radius + centerCharger.friisParameter)), 2);
		
		
		//System.out.println("Radius = " + this.Radius);
		
		for(int i=0;i<chargedNodes.size()-1;i++)
		{
			//System.out.println(chargedNodes.get(i).location.getX() + " " + chargedNodes.get(i).location.getY());
			
			singleDistanceResult += chargedNodes.get(i).location.distance(chargedNodes.get(i+1).location);
		}
		
		double maxEnergy = 0;
		
		for(int i =0;i<chargedNodes.size();i++)
		{
			if(chargedNodes.get(i).RequiredEnergy > maxEnergy)
			{
				maxEnergy = chargedNodes.get(i).RequiredEnergy; // 가장 큰 에너지를 찾아야함
			}
		}
		
		this.multiTime = maxEnergy / (centerCharger.TransferEnergy * centerfriisResult);
		// 가장 많이 충전해야 되는 에너지를, friis효율 * 전송에너지, 즉 도달에너지로 나누면 시간이 나온다
		
		this.multiEnergyResult = multiTime * centerCharger.TransferEnergy;
		// 그리고 소요시간에, 전송에너지를 곱하면 사용에너지겠지
		
		double singlefrissResult = centerCharger.TransferAntennaGain * chargedNodes.get(0).ReceiveAntennaGain * chargedNodes.get(0).Retenna
				/ centerCharger.polarizationLoss * Math.pow((centerCharger.WaveLength/(centerCharger.friisParameter)), 2);
		
		
		
		for(int i=0;i<chargedNodes.size();i++)
		{
			double tmpTime = chargedNodes.get(i).RequiredEnergy / (centerCharger.TransferEnergy * singlefrissResult);
			// 똑같이 이러면 각각의 노드에 대한 소요시간이 나올 것이고,
			
			this.singleTime += tmpTime; // 그 시간을 더한다. 
			
			this.singleEnergyResult += (tmpTime * centerCharger.TransferEnergy);
		}
		
	}
}

package com.MC.Efficient;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Cluster {

	
	public Charger centerCharger = null;
	// ��� �������ִ� Charger.
	
	public ArrayList<Node> chargedNodes = new ArrayList<Node>();
	// ������ �����ϴ� ArrayList
	
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
		this.Radius20 = 20 * r;
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
		DrawRadius = Radius20 * rate;
		// DrawRadius ������ �����ϰ�
		
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
				maxEnergy = chargedNodes.get(i).RequiredEnergy; // ���� ū �������� ã�ƾ���
			}
		}
		
		this.multiTime = maxEnergy / (centerCharger.TransferEnergy * centerfriisResult);
		// ���� ���� �����ؾ� �Ǵ� ��������, friisȿ�� * ���ۿ�����, �� ���޿������� ������ �ð��� ���´�
		
		this.multiEnergyResult = multiTime * centerCharger.TransferEnergy;
		// �׸��� �ҿ�ð���, ���ۿ������� ���ϸ� ��뿡��������
		
		double singlefrissResult = centerCharger.TransferAntennaGain * chargedNodes.get(0).ReceiveAntennaGain * chargedNodes.get(0).Retenna
				/ centerCharger.polarizationLoss * Math.pow((centerCharger.WaveLength/(centerCharger.friisParameter)), 2);
		
		
		
		for(int i=0;i<chargedNodes.size();i++)
		{
			double tmpTime = chargedNodes.get(i).RequiredEnergy / (centerCharger.TransferEnergy * singlefrissResult);
			// �Ȱ��� �̷��� ������ ��忡 ���� �ҿ�ð��� ���� ���̰�,
			
			this.singleTime += tmpTime; // �� �ð��� ���Ѵ�. 
			
			this.singleEnergyResult += (tmpTime * centerCharger.TransferEnergy);
		}
		
	}
}

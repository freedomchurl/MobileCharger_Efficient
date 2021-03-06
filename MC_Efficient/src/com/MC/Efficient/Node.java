package com.MC.Efficient;

import javafx.geometry.Point2D;

public class Node {
	public double x_pos;
	public double y_pos;
	
	public Point2D location;
	
	public double RequiredEnergy;
	
	public double ReceiveAntennaGain = 1.0;
	
	public double Retenna = 1.0; 
	//Retenna 변환율, 다이오드에 따라서 거의 100%에 가까운 변환효율을 보여준다.
	
	public double Angle = 0;
	
	Node()
	{
		
	}
	
	Node(double x, double y, double Required)
	{
		this.x_pos = x;
		this.y_pos = y;
		
		location = new Point2D(x_pos, y_pos);
		
		this.RequiredEnergy = Required;
	}
	
	Node(double x, double y, double Required, double angle)
	{
		this.x_pos = x;
		this.y_pos = y;
		
		location = new Point2D(x_pos, y_pos);
		
		this.Angle = angle;
		
		this.RequiredEnergy = Required;
	}
}

package com.MC.Efficient;

import javafx.geometry.Point2D;

public class Node {
	public double x_pos;
	public double y_pos;
	
	public Point2D location;
	
	public double RequiredEnergy;
	
	public double ReceiveAntennaGain;
	
	public double Retenna = 1.0; 
	//Retenna ��ȯ��, ���̿��忡 ���� ���� 100%�� ����� ��ȯȿ���� �����ش�.
	
	Node(double x, double y, double Required)
	{
		this.x_pos = x;
		this.y_pos = y;
		
		location = new Point2D(x_pos, y_pos);
		
		this.RequiredEnergy = Required;
	}
}

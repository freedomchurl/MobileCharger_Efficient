package com.MC.Efficient;

import javafx.geometry.Point2D;

// Node�� �̾�޵��� �Ѵ�.
public class Charger{

	public double MoveEnergy;
	// �̵��� ��� ������
	
	public double x_pos;
	public double y_pos;
	
	public Point2D location;
	
	public double TransferEnergy;
	// ���ۿ� ���� ������ J ������ �����Ѵ�.
	
	public double TransferAntennaGain;
	
	
	Charger()
	{
	}
	
	Charger(double x, double y, double MoveEnergy, double TransferEnergy)
	{
		this.x_pos = x;
		this.y_pos = y;
		this.MoveEnergy = MoveEnergy;
		this.TransferEnergy = TransferEnergy;
		
		location = new Point2D(x_pos,y_pos);

	}
}
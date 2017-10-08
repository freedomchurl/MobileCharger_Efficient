package com.MC.Efficient;

import javafx.geometry.Point2D;

// Node를 이어받도록 한다.
public class Charger{

	public double MoveEnergy;
	// 이동에 드는 에너지
	
	public double x_pos;
	public double y_pos;
	
	public Point2D location;
	
	public double TransferEnergy;
	// 전송에 쓰는 에너지 J 단위로 통합한다.
	
	public double TransferAntennaGain = 36.0;
	public double WaveLength = 1.0;
	public double polarizationLoss = 1.0;
	public double friisParameter = 0.05;
	// 이게 몇이 맞는건가?
	
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

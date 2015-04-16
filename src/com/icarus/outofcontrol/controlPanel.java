package com.icarus.outofcontrol;

import javax.swing.*;

public class controlPanel extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int MaxBirdsNumber = 100;
	static birds[] myBirds = new birds[MaxBirdsNumber];
	public controlPanel(){
		
	}
	public controlPanel(String title){
		super(title);
	}
	protected void frameInit(){
		super.frameInit();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void initBirds(){
		
		for(int i = 0; i < MaxBirdsNumber; i++){
			myBirds[i] = new birds();
			//System.out.println("第"+ i + "只鸟的坐标是：x = "+ myBirds[i].x + ",y = " + myBirds[i].y);
		}

		controlPanel.calAvr();
		System.out.println("鸟群的平均坐标为x=" + birds.avr_x + "; y=" + birds.avr_y);
		System.out.println("鸟群的平均速度为v=" + birds.avr_velocity + "; 移动角度为" + birds.avr_angle/3.14*180);
	}
	static void calAvr() {
		// TODO Auto-generated method stub
		double sum = 0, temp = 0;
		for(int i = 0; i < MaxBirdsNumber; i++){
			sum += myBirds[i].x;
		}
		birds.avr_x = sum/MaxBirdsNumber;
		sum = 0;
		
		for(int i = 0; i < MaxBirdsNumber; i++){
			sum += myBirds[i].y;
		}
		birds.avr_y = sum/MaxBirdsNumber;
		sum = 0;
		
		for(int i = 0; i < MaxBirdsNumber; i++){
			sum += myBirds[i].velocity * Math.cos(myBirds[i].angle);
			temp += myBirds[i].velocity * Math.sin(myBirds[i].angle);
		}
		birds.avr_velocity = Math.sqrt(Math.pow(sum, 2) + Math.pow(temp, 2));
		
		if(sum>0){
			birds.avr_angle = Math.atan(sum/temp);
		}else{
			birds.avr_angle = -Math.atan(sum/temp);
		}
		
	}
}

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
			//System.out.println("��"+ i + "ֻ��������ǣ�x = "+ myBirds[i].x + ",y = " + myBirds[i].y);
		}

		controlPanel.calAvr();
		System.out.println("��Ⱥ��ƽ������Ϊx=" + birds.avr_x + "; y=" + birds.avr_y);
		System.out.println("��Ⱥ��ƽ���ٶ�Ϊv=" + birds.avr_velocity + "; �ƶ��Ƕ�Ϊ" + birds.avr_angle/3.14*180);
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

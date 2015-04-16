package com.icarus.outofcontrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;

public class birds {
	static private int maxVelocity = 10;
	static private int dangerDistance = 10;
	static private int flySpeed = 4;
	static public double avr_velocity = 0, avr_angle = 0, avr_x = 0, avr_y = 0;
	double x = 0, y = 0, velocity = 0, angle = 0;
	birds(){
		Random rd = new Random();
		x = rd.nextDouble()*100;
		y = rd.nextDouble()*100;

	};
	
	public HashMap<Integer, Double> findBirdsInView(int i){
		HashMap<Integer, Double> neighbors = new HashMap<Integer, Double>();
		double[] distance = new double[controlPanel.MaxBirdsNumber];
		for(int iter = 0; iter < controlPanel.MaxBirdsNumber; iter++){
			double temp1 = 0, temp2 = 0;
			temp1 = Math.pow(controlPanel.myBirds[iter].x - this.x, 2);
			temp2 = Math.pow(controlPanel.myBirds[iter].y - this.y, 2);
			distance[iter] = Math.sqrt(temp1+temp2);
			if(iter != i && distance[iter] < dangerDistance+flySpeed){
				//System.out.println("i am bird "+ i + " and "+"i see bird " + iter +" in my view!");
				neighbors.put(iter, distance[iter]);
			}
		}
		return neighbors;
	};
	public void fly(int i){
		double tempx = 0, tempy = 0, tempangle = 0, tempMin = 1000;		
		int tempKey = 0;
		HashMap<Integer, Double> neighbors = new HashMap<Integer, Double>();
		neighbors = findBirdsInView(i);	//观察视野内是否有邻居鸟
		
		Iterator<Entry<Integer, Double>> itor = neighbors.entrySet().iterator();
		while(itor.hasNext()){			//观察是否与邻居鸟有碰撞危险
			Entry<Integer, Double> entry = itor.next();
			if(entry.getValue() < dangerDistance){	//如果有碰撞危险，则选出离我最近的一只邻居鸟
				tempMin = entry.getValue();
				tempKey = entry.getKey();
			}
		}
		if(neighbors.containsValue(tempMin)){//如有碰撞危险，计算离我最近的邻居鸟与我的角度，并朝反方向飞
			if(controlPanel.myBirds[i].x < controlPanel.myBirds[tempKey].x){
				tempangle = Math.atan((controlPanel.myBirds[i].y-controlPanel.myBirds[tempKey].y)/(controlPanel.myBirds[i].x-controlPanel.myBirds[tempKey].x));
			}else{
				tempangle = 3.14+Math.atan((controlPanel.myBirds[i].y-controlPanel.myBirds[tempKey].y)/(controlPanel.myBirds[i].x-controlPanel.myBirds[tempKey].x));
			}
			tempx = controlPanel.myBirds[i].x - flySpeed*Math.cos(tempangle);	
			tempy = controlPanel.myBirds[i].y - flySpeed*Math.sin(tempangle);
			tempMin = 1000;
			tempKey = 0;
			controlPanel.myBirds[i].x = tempx;
			controlPanel.myBirds[i].y = tempy;
			controlPanel.myBirds[i].angle = 3.14+tempangle;
			controlPanel.myBirds[i].velocity = flySpeed;
		}
		else{								//没有碰撞危险，往中心飞
			if(controlPanel.myBirds[i].x < birds.avr_x){
				tempangle = Math.atan((controlPanel.myBirds[i].y-birds.avr_y)/(controlPanel.myBirds[i].x-birds.avr_x));
			}else{
				tempangle = 3.14+Math.atan((controlPanel.myBirds[i].y-birds.avr_y)/(controlPanel.myBirds[i].x-birds.avr_x));
			}
			
			tempx = controlPanel.myBirds[i].x + flySpeed*Math.cos(tempangle);	
			tempy = controlPanel.myBirds[i].y + flySpeed*Math.sin(tempangle);
//			System.out.println("我的坐标是"+controlPanel.myBirds[i].x+" "+controlPanel.myBirds[i].y);
//			System.out.println("平均坐标是"+birds.avr_x+" "+birds.avr_y);
//			System.out.println("我要朝"+tempangle+"方向走");
//			System.out.println("我下一步要走到的坐标是"+tempx+" "+tempy);
			controlPanel.myBirds[i].x = tempx;
			controlPanel.myBirds[i].y = tempy;
			controlPanel.myBirds[i].angle = tempangle;
			controlPanel.myBirds[i].velocity = maxVelocity;
		} 

	}
}

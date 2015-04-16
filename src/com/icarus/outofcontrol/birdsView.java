package com.icarus.outofcontrol;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Paint;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class birdsView implements KeyListener, MouseListener{
	static Canvas c;
	String s = "";
	TextField t;
	birdsView(){
	}
	public void drawBirds() {
		// TODO Auto-generated method stub

		c.paint(c.getGraphics());
		for(int iter = 1; iter < controlPanel.MaxBirdsNumber; iter++)
		{
			c.getGraphics().drawString("π", ((int)(controlPanel.myBirds[iter].x)+50)*4, ((int)(controlPanel.myBirds[iter].y)+50)*4);
		}
		c.getGraphics().drawString("M", ((int)(controlPanel.myBirds[0].x)+50)*4, ((int)(controlPanel.myBirds[0].y)+50)*4);
//		c.getGraphics().drawArc(((int)(controlPanel.myBirds[0].x)+50)*4, ((int)(controlPanel.myBirds[0].y)+50)*4, 4, 4, 0, 360);
		controlPanel.calAvr();
	}
	public static void main(String args[]){

		Frame f = new Frame("BirdsCanvas");
		birdsView bv = new birdsView();
		birdsView.c = new Canvas();
		bv.t = new TextField();
		f.add("South", bv.t);
		f.add("Center",birdsView.c);
		f.setSize(1500,1500);
		birdsView.c.addMouseListener(bv);
		birdsView.c.addKeyListener(bv);
		controlPanel.initBirds();
		f.addWindowListener(new WindowAdapter()  //为了关闭窗口
		  {
		   public void windowClosing(WindowEvent e)
		   {
		       System.exit(0);
		   }
		  });
		f.setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		t.setText("mouseClicked");
		c.requestFocus();
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		t.setText("mouseEntered");
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		t.setText("mouseExited");
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		t.setText("mousePressed");
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		t.setText("mouseReleased");
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		t.setText("keyReleased");
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		char temp;
		t.setText("KeyTyped");
		temp = arg0.getKeyChar();
		//s += arg0.getKeyChar();
		//c.getGraphics().drawString(s, 0, 20);
		switch(temp){
			case 'q':
				//System.out.println("You press w");
				//c.repaint();

				for(int i = 0; i < controlPanel.MaxBirdsNumber; i++){
					controlPanel.myBirds[i].fly(i);
				}
				this.drawBirds();
				System.out.println("鸟群的平均坐标为x=" + birds.avr_x + "; y=" + birds.avr_y);
				System.out.println("鸟群的平均速度为v=" + birds.avr_velocity + "; 移动角度为" + birds.avr_angle/3.14*180);
				break;
			case 'w':
				for(int i = 0; i < 10; i++)
				controlPanel.myBirds[i].y -= 2;
				for(int i = 0; i < controlPanel.MaxBirdsNumber; i++){
					controlPanel.myBirds[i].fly(i);
				}
				this.drawBirds();
				break;
			case 's':
				for(int i = 0; i < 10; i++)
				controlPanel.myBirds[i].y += 2;
				for(int i = 0; i < controlPanel.MaxBirdsNumber; i++){
					controlPanel.myBirds[i].fly(i);
				}
				this.drawBirds();
				break;
			case 'a':
				for(int i = 0; i < 10; i++)
				controlPanel.myBirds[i].x -= 2;
				for(int i = 0; i < controlPanel.MaxBirdsNumber; i++){
					controlPanel.myBirds[i].fly(i);
				}
				this.drawBirds();
				break;
			case 'd':
				for(int i = 0; i < 10; i++)
				controlPanel.myBirds[i].x += 2;
				for(int i = 0; i < controlPanel.MaxBirdsNumber; i++){
					controlPanel.myBirds[i].fly(i);
				}
				this.drawBirds();
				break;
			default:
				System.out.println("You press other key");
				break;
		}
	}

}

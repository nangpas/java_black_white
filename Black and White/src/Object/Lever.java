package Object;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

public class Lever extends object {

	boolean state; // ������ ���� (���������� �Ǹ� true)
	int number; // ���° ��������
	double count; // ���� �������� �׸��� ���� ī���Ͱ�
	Image[] actImg; // ������ �����̴� �̹�����
	boolean moving; // ���� ������ �����̴��� �ȿ����̴� ���°�
	
	
	public Lever(int x, int y, int number) {
		this.x = x;
		this.y = y;
		name = "Lever";
		this.number = number;
		count = 0; 
		state = false;
		actImg = new Image[4];
		moving = false;
		img = new ImageIcon("����0.png").getImage();
		for (int i = 0; i < actImg.length; i++) 
			actImg[i] = new ImageIcon("����" + i + ".png").getImage();
		r = new Rectangle(x,y,54,26);
	}

	public void touch() {
		moving = true;
	}

	public void draw(Graphics g, ImageObserver frame) {
		if(moving)
			drawAct(g, frame);
		else
			g.drawImage(img, x, y, frame);
	}
	

	public void drawAct(Graphics g, ImageObserver frame) {
		if(state){
			if (count >= 0 && count < 1)
				g.drawImage(actImg[3], x, y, frame);
			if (count >= 1 && count < 2)
				g.drawImage(actImg[2], x, y, frame);
			if (count >= 2 && count < 3)
				g.drawImage(actImg[1], x, y, frame);
			if (count >= 3 && count < 4)
				g.drawImage(actImg[0], x, y, frame);
			if(count > 4){
				moving = false;
				state = false;
				count = 0;
				img = new ImageIcon("����0.png").getImage();
				draw(g, frame);
			}
		}else{
			if (count >= 0 && count < 1)
				g.drawImage(actImg[0], x, y, frame);
			if (count >= 1 && count < 2)
				g.drawImage(actImg[1], x, y, frame);
			if (count >= 2 && count < 3)
				g.drawImage(actImg[2], x, y, frame);
			if (count >= 3 && count < 4)
				g.drawImage(actImg[3], x, y, frame);
			if(count > 4){
				moving = false;
				state = true;
				count = 0;
				img = new ImageIcon("����3.png").getImage();
				draw(g, frame);
			}
		}
	}
	
	public void timer() {
		if (moving){
			count+=0.4;
		}
	}

}

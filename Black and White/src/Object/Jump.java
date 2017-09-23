package Object;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Jump extends object{
	int number; // ���° ��������
	double count; // ���� �������� �׸��� ���� ī���Ͱ�
	Image[] actImg; // ������ �����̴� �̹�����
	boolean moving; // ���� ������ �����̴��� �ȿ����̴� ���°�
	
	public Jump(int x, int y, int number) {
		this.x = x;
		this.y = y;
		name = "JumpJump";
		this.number = number;
		count = 0;
		actImg = new Image[5];
		moving = false;
		img = new ImageIcon("������4.png").getImage();
		for (int i = 0; i < actImg.length; i++) 
			actImg[i] = new ImageIcon("������" + i + ".png").getImage();
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
			if (count >= 0 && count < 6)
				g.drawImage(actImg[3], x, y, frame);
			if (count >= 6 && count < 11)
				g.drawImage(actImg[2], x, y, frame);
			if (count >= 11 && count < 15)
				g.drawImage(actImg[1], x, y, frame);
			if (count >= 15 && count < 24)
				g.drawImage(actImg[0], x, y, frame);
			if (count >= 24 && count < 26)
				g.drawImage(actImg[2], x, y, frame);
			if (count >= 26 && count < 28)
				g.drawImage(actImg[4], x, y, frame);
			if(count > 27){
				moving = false;
				count = 0;
				draw(g, frame);
			}
	}
	
	public void timer() {
		if (moving){
			count+=2;
		}
	}
}

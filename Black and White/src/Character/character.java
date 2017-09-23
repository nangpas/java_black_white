package Character;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import org.w3c.dom.css.Rect;

import Object.object;

public class character implements KeyListener {
	int x, y;
	int count; // 스킬 카운터

	Rectangle crashR;
	Rectangle underR;
	Rectangle tempR;

	Image[] jumpImg;
	Image[] runRImg;
	Image[] runLImg;
	Image[] stayImg;
	Image[] jumpjumpImg;
	Image[] downImg;

	boolean still; // 가만히 있는지 안있는지
	boolean down; // 떨어지고 있는지 아닌지
	boolean jump; // 점프 중인지 아닌지
	boolean jumpjump; // 점프대를 탓는지 안탓는지
	boolean move;// 좌 우로 움직이는지 아닌지
	boolean roll; // 구르기 온 오프
	
	boolean gravitycheck;
	boolean stillcheck;
	boolean crash = false;

	boolean keyUp;
	boolean keyDown;
	boolean keyLeft;
	boolean keyRight;

	object ob;

	// 좌 우 이미지 : 65 X 76
	// 가만히 이미지 : 49 * 76

	public character(int x, int y, int playerNumber) {
		this.x = x;
		this.y = y;

		count = 0;

		still = false;
		down = true;
		jump = false;
		jumpjump = false;
		move = false;
		roll = false;
		
		stillcheck = false;
		gravitycheck = false;

		keyUp = false;
		keyDown = false;
		keyLeft = false;
		keyRight = false;

		crashR = new Rectangle(x, y, 49, 75);
		underR = new Rectangle(x, y + 76, 49, 1);

		stayImg = new Image[6];
		runRImg = new Image[8];
		runLImg = new Image[8];
		jumpImg = new Image[8];
		jumpjumpImg = new Image[2];
		downImg = new Image[4];

		if (playerNumber == 0) {
			for (int i = 0; i < stayImg.length; i++)
				stayImg[i] = new ImageIcon("가만히 " + i + ".png").getImage();

			for (int i = 0; i < runRImg.length; i++)
				runRImg[i] = new ImageIcon("캐릭터 오른" + i + ".png").getImage();

			for (int i = 0; i < runLImg.length; i++)
				runLImg[i] = new ImageIcon("캐릭터 왼" + i + ".png").getImage();

			for (int i = 0; i < jumpImg.length; i++)
				jumpImg[i] = new ImageIcon("점프 " + i + ".png").getImage();

			for (int i = 0; i < jumpjumpImg.length; i++)
				jumpjumpImg[i] = new ImageIcon("점프대 점프" + i + ".png").getImage();

			for (int i = 0; i < downImg.length; i++)
				downImg[i] = new ImageIcon("캐릭터 하강" + i + ".png").getImage();
		}
	}

	public void draw(Graphics g, ImageObserver frame) {
		if (move) {
			if (keyRight) {
				if (count % 8 == 0)
					g.drawImage(runRImg[0], x, y, frame);
				if (count % 8 == 1)
					g.drawImage(runRImg[1], x, y, frame);
				if (count % 8 == 2)
					g.drawImage(runRImg[2], x, y, frame);
				if (count % 8 == 3)
					g.drawImage(runRImg[3], x, y, frame);
				if (count % 8 == 4)
					g.drawImage(runRImg[4], x, y, frame);
				if (count % 8 == 5)
					g.drawImage(runRImg[5], x, y, frame);
				if (count % 8 == 6)
					g.drawImage(runRImg[6], x, y, frame);
				if (count % 8 == 7)
					g.drawImage(runRImg[7], x, y, frame);
			}
			
			if(keyLeft){
				if (count % 8 == 0)
					g.drawImage(runLImg[0], x, y, frame);
				if (count % 8 == 1)
					g.drawImage(runLImg[1], x, y, frame);
				if (count % 8 == 2)
					g.drawImage(runLImg[2], x, y, frame);
				if (count % 8 == 3)
					g.drawImage(runLImg[3], x, y, frame);
				if (count % 8 == 4)
					g.drawImage(runLImg[4], x, y, frame);
				if (count % 8 == 5)
					g.drawImage(runLImg[5], x, y, frame);
				if (count % 8 == 6)
					g.drawImage(runLImg[6], x, y, frame);
				if (count % 8 == 7)
					g.drawImage(runLImg[7], x, y, frame);
			}
		}else if(still){
			if (count % 5 == 0)
				g.drawImage(stayImg[0], x, y, frame);
			if (count % 5 == 1)
				g.drawImage(stayImg[1], x, y, frame);
			if (count % 5 == 2)
				g.drawImage(stayImg[2], x, y, frame);
			if (count % 5 == 3)
				g.drawImage(stayImg[3], x, y, frame);
			if (count % 5 == 4)
				g.drawImage(stayImg[4], x, y, frame);
		}else if(down){
			if (count % 4 == 0)
				g.drawImage(downImg[0], x, y, frame);
			if (count % 4 == 1)
				g.drawImage(downImg[1], x, y, frame);
			if (count % 4 == 2)
				g.drawImage(downImg[2], x, y, frame);
			if (count % 4 == 3)
				g.drawImage(downImg[3], x, y, frame);
		}

	}

	public void rectFlash() {
		crashR.setLocation(x, y);
		underR.setLocation(x, y + 76);
	}

	public void gravity(ArrayList<object> ar) {
		for (int i = 0; i < ar.size(); i++) {
			ob = ar.get(i);
			ob.getName();
			if (ob.getName() == "UP_ironwall") {
				if (underR.intersects(ob.getR()) == false) {
					gravitycheck = false;
				} else {
					gravitycheck = true;
					break;
				}
			}
		}

		if (gravitycheck != true) {
			y += 1;
		}
	}

	public void check(ArrayList<object> ar) {

		for (int i = 0; i < ar.size(); i++) {
			ob = ar.get(i);

			if (ob.getName() == "Lever") {
				if (crashR.intersects(ob.getR())) {
					ob.touch();
				}
			}

			if (ob.getName() == "JumpJump") {
				if (crashR.intersects(ob.getR())) {
					ob.touch();
				}
			}

		}
	}

	public void keyProcess(ArrayList<object> ar) {

		if (keyUp == false &&keyDown == false && keyLeft == false && keyRight == false){
			if(gravitycheck)
				still = true;
			else
				down = true;
		}

		if (keyUp) {
			jump = true ;;
		}
		
		if (keyDown) {

		}

		if (keyLeft) {
			still = false;
			down = false;
			
			if(jump = false && jumpjump == false && roll == false)
				move = true;
			
			
			for (int i = 0; i < ar.size(); i++) {
				ob = ar.get(i);
				tempR = new Rectangle(crashR);
				tempR.setLocation(crashR.x - 1, tempR.y);
				if (ob.getName() == "UP_ironwall") {
					if (tempR.intersects(ob.getR())) {
						crash = true;
						break;
					} else
						crash = false;
				}
			}
			if (crash == false) {
				x -= 1;
			}
		}

		if (keyRight) {
			still = false;
			down = false;
			if(jump = false && jumpjump == false && roll == false)
				move = true;
			
			for (int i = 0; i < ar.size(); i++) {
				ob = ar.get(i);
				tempR = new Rectangle(crashR);
				tempR.setLocation(crashR.x + 1, tempR.y);

				if (ob.getName() == "UP_ironwall") {
					if (tempR.intersects(ob.getR())) {
						crash = true;
						System.out.println("충돌햇네");
						break;
					} else
						crash = false;
				}
			}

			if (crash == false) {
				x += 1;
			}
		}
	}

	public void timer() {
		if (move) {
			count++;
		}
		if (still) {
			count++;
		}
		if (down) {
			count++;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			keyLeft = true;
			keyRight = false;
			keyUp = false;
			keyDown = false;
			break;
		case KeyEvent.VK_RIGHT:
			keyRight = true;
			keyLeft = false;
			keyUp = false;
			keyDown = false;
			break;
		case KeyEvent.VK_UP:
			keyUp = true;
			keyUp = false;
			keyDown = false;
			keyRight = false;
			keyLeft = false;
			break;
		case KeyEvent.VK_DOWN:
			keyDown = true;
			keyRight = false;
			keyLeft = false;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			keyLeft = false;
			move = false;
			count = 0;
			break;
		case KeyEvent.VK_RIGHT:
			keyRight = false;
			move = false;
			count = 0;
			break;
		case KeyEvent.VK_UP:
			keyUp = false;
			break;
		case KeyEvent.VK_DOWN:
			keyDown = false;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}

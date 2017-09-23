package Client;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JFrame;

import Character.character;
import Object.Jump;
import Object.Lever;
import Object.Wall;
import Object.object;

public class Main extends JFrame implements Runnable {
	Thread th;
	Image buffimg;
	
	Graphics objectG;
	object object;
	
	character character;
	Graphics characterG;
	
	int count;
	
	ArrayList<object> objectList = new ArrayList<object>();
	ArrayList<character> characterList = new ArrayList<character>();
	

	public Main() {
		setTitle("Å×½ºÆ®");
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		count = 0;
		object = new Jump(500, 500, 0);
		objectList.add(object);
		object = new Lever(250, 500, 0);
		objectList.add(object);

		for (int i = 0; i < 10; i++) {
			object = new Wall(68*i, 800);
			objectList.add(object);
		}
		
		character = new character(500,60,0);
		characterList.add(character);
		
		for(int i = 0; i < characterList.size(); i++){
			character = (character) characterList.get(i);
			addKeyListener(character);
		}
		
		
		th = new Thread(this);
		th.start();
		setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		buffimg = createImage(1000, 1000);
		objectG = buffimg.getGraphics();
		characterG = buffimg.getGraphics();
		update(g);
	}

	@Override
	public void update(Graphics g) {
		for (int i = 0; i < objectList.size(); i++) {
			object = (object) objectList.get(i);
			object.draw(objectG, this);
			if (count == 50)
				object.touch();
			if (count == 100)
				object.touch();
		}
		
		for(int i = 0; i < characterList.size(); i++){
			character = (character) characterList.get(i);
			character.draw(characterG, this);
			character.gravity(objectList);
		}
		
		count++;
		g.drawImage(buffimg, 0, 0, this);
	}

	@Override
	public void run() {
		while (true) {
			try {
				for (int i = 0; i < objectList.size(); i++) {
					object = (object) objectList.get(i);
					object.timer();
				}
				
				for(int i = 0; i < characterList.size(); i++){
					character = (character) characterList.get(i);
					character.keyProcess(objectList);
					character.rectFlash();
					character.timer();
				}
				repaint();
				Thread.sleep(20);
			} catch (Exception e) {
			}
		}
	}

	public static void main(String[] args) {
		new Main();
	}

}

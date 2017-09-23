package Object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Wall extends object {
	public Wall(int x, int y) {
		this.x = x;
		this.y = y;
		name = "UP_ironwall";
		img = new ImageIcon("√∂≈∏¿œ0.png").getImage();

		r = new Rectangle(x, y, 68, 68);
	}
	
	public void draw(Graphics g, ImageObserver frame){
		g.drawImage(img, x, y, frame);
	}

}

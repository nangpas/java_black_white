package Object;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class object {
	int x,y;
	String name;
	Image img;
	Rectangle r;
	public Rectangle getR(){return r;}
	public String getName(){return name;}
	public void draw(Graphics g, ImageObserver frame){}
	public void timer() {}
	public void touch() {}
}

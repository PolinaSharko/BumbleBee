package Model;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Lives {

    private ArrayList<Rectangle> lives = new ArrayList<Rectangle>();
    public final int WIDTH = 650, HEIGHT = 650;
    public Random rand = new Random();
    public Image hrt=null;

    public Lives() {
        try {
            hrt = ImageIO.read(new File("C:\\Users\\Шмель\\Desktop\\BumbleBee\\Resources\\heart.png"));
        }
        catch (IOException e){}
    }

    public void addLife(boolean start) {
        int width = 100;
        int height = 55 + rand.nextInt(500);
        if (start) {
            lives.add(new Rectangle(WIDTH + width + lives.size() * 350, HEIGHT - height - 120, 20, 20));
        }
    }

    public void paintLife(Graphics g, Rectangle lives, JFrame j) {
        g.drawImage(hrt, lives.x, lives.y, 40, 40, j);
    }

    public ArrayList<Rectangle> getLives(){
        return lives;
    }
}


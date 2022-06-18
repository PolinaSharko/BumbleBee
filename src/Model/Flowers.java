package Model;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Flowers {

    private ArrayList<Rectangle> flows = new ArrayList<Rectangle>();
    public final int WIDTH = 650, HEIGHT = 650;
    public Random rand = new Random();
    public Image flw = null;

    public Flowers() {
        try {
            flw = ImageIO.read(new File("C:\\Users\\Шмель\\Desktop\\BumbleBee\\Resources\\flowers.png"));
        }
        catch (IOException e){System.out.print(e);}
    }

    public void addFlow(boolean start) {
        int width = 100;
        int height = 30 + rand.nextInt(600);
        if (start)
        {
            flows.add(new Rectangle(WIDTH + width + flows.size() * 200, HEIGHT - height - 200, 20, 20));
        }
    }

    public void paintFlows(Graphics g, Rectangle flow, JFrame j)
    {
        g.drawImage(flw,flow.x, flow.y,40, 40, j);
    }

    public ArrayList<Rectangle> getFlows(){
        return flows;
    }

}


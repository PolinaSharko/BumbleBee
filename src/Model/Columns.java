package Model;
import View.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Columns {

    private ArrayList<Rectangle> columns = new ArrayList<Rectangle>();
    public final int WIDTH = 650, HEIGHT = 650;
    public Random rand = new Random();

    public Columns(){ }

    public void addColumn(boolean start) {
        int space = 300;
        int width = rand.nextInt(100);
        int height = 30 + rand.nextInt(300);
        if (start) {
            columns.add(new Rectangle(WIDTH + width + columns.size() * 300+1, HEIGHT - height - 120, 40, height));
            columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, 40, HEIGHT - height - space));
            columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, 0, 40, height));
            columns.add(new Rectangle(columns.get(columns.size() - 1).x + 350, HEIGHT - height - 120, 40, height-30));
            if (View.vw.getLevelUp() > 1) {
                width = rand.nextInt(200);
                height = 30 + rand.nextInt(300);
                columns.add(new Rectangle(columns.get(columns.size() - 1).x + height, HEIGHT - height - width, 30, height-30));
                columns.add(new Rectangle(columns.get(columns.size() - 1).x + 400, HEIGHT - height - 200, 45, height-30));
                columns.add(new Rectangle(columns.get(columns.size() - 1).x + height - 90, 0, 35, height-30));

            }
        }

     }

    public void paintColumn(Graphics g, Rectangle column)
    {
        if (View.vw.getLevelUp() % 2 == 0) {
            g.setColor(Color.green.darker());
        }
        else g.setColor(Color.cyan.darker());
        g.fillRect(column.x, column.y, column.width, column.height);
    }

    public ArrayList<Rectangle> getColumns(){
        return columns;
    }
}

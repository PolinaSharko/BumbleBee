package View;
import Model.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class View {

    private Image im, bgd, flw, hrt, bgd1, frm;
    public static View vw;
    private final int WIDTH = 650, HEIGHT = 650;
    private Renderer renderer;
    private Rectangle beeChar;
    private SortedIntegerList lst;
    private int HEALTH = 3;
    private int ticks, score = 0, flag = 1, levelUp;
    private boolean gameOver, started, see;
    private Columns c;
    private Lives l;
    private Flowers f;
    private Random rand;
    private JFrame jframe = new JFrame();

    public View() {
        try {
            frm = ImageIO.read(new File("C:\\Users\\Шмель\\Desktop\\BumbleBee\\Resources\\frame.png"));
            hrt = ImageIO.read(new File("C:\\Users\\Шмель\\Desktop\\BumbleBee\\Resources\\heart.png"));
            im = ImageIO.read(new File("C:\\Users\\Шмель\\Desktop\\BumbleBee\\Resources\\1.png"));
            bgd = ImageIO.read(new File("C:\\Users\\Шмель\\Desktop\\BumbleBee\\Resources\\backgroung.png"));
            bgd1 = ImageIO.read(new File("C:\\Users\\Шмель\\Desktop\\BumbleBee\\Resources\\bgd.png"));
            flw = ImageIO.read(new File("C:\\Users\\Шмель\\Desktop\\BumbleBee\\Resources\\flowers.png"));
           } catch (IOException e) {
        }
        lst = new SortedIntegerList(true);
        renderer = new Renderer();
        rand = new Random();
        c = new Columns();
        l = new Lives();
        f = new Flowers();
        this.jframe.setJMenuBar(new Menu());
        jframe.add(renderer);
        jframe.setTitle("BumbelBee");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(WIDTH, HEIGHT);
        jframe.setResizable(false);
        jframe.setVisible(true);
        beeChar = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 50, 50);
     }

    public void paint(Graphics g) {
        if (levelUp % 2 != 0) {
            g.drawImage(bgd1, 0, 0, WIDTH, HEIGHT, jframe);
            g.setColor(Color.lightGray);
            g.fillRect(0, HEIGHT - 120, WIDTH, 120);
            g.setColor(Color.cyan.darker());
            g.fillRect(0, HEIGHT - 120, WIDTH, 20);
        }
        else{
            g.drawImage(bgd, 0, 0, WIDTH, HEIGHT, jframe);
            g.setColor(Color.orange);
            g.fillRect(0, HEIGHT - 120, WIDTH, 120);
            g.setColor(Color.green.darker());
            g.fillRect(0, HEIGHT - 120, WIDTH, 20);
        }

        g.drawImage(im, beeChar.x, beeChar.y, 50, 50, jframe);
        g.drawImage(hrt, 0, HEIGHT - 100, 50, 50, jframe);
        g.drawImage(flw, WIDTH - 120, HEIGHT - 100, 50, 50, jframe);


        for (Rectangle live : l.getLives()) {
            l.paintLife(g, live, jframe);
        }

        for (Rectangle flow : f.getFlows()) {
            f.paintFlows(g, flow, jframe);
        }

        for (Rectangle column : c.getColumns()) {
            c.paintColumn(g, column);
        }

        g.setColor(Color.white);
        g.setFont(new Font("Arial", 1, 70));

        if (!started)
        {
            c.addColumn(false);
            l.addLife(false);
            f.addFlow(false);
            g.drawString("Click to start!", 75, HEIGHT / 2 - 50);
        }

        if (gameOver) {
            c.getColumns().clear();
            l.getLives().clear();
            f.getFlows().clear();
            g.setColor(Color.green.darker());
            g.fillRect(WIDTH / 2 - 160, HEIGHT / 2 - 142, 370, 230);
            g.drawImage(frm, WIDTH / 2 - 175, HEIGHT / 2 - 150, 400, 250, jframe);
            g.setColor(Color.orange);
            g.drawString("Game Over!", 150, HEIGHT / 2 - 200);
            g.setFont(new Font("Times new Roman", 2, 20));
            g.setColor(Color.white);
            g.drawString("Your score: " + score, WIDTH / 2 - 60, HEIGHT / 2 - 100);
            g.drawString("Best score table: " , WIDTH / 2 - 60, HEIGHT / 2 - 80);
            g.drawString(Model.model.getTable().toStringBest(), WIDTH / 2 - 60, HEIGHT / 2 - 60);
            g.drawString("Press space to restart ", WIDTH / 2 - 60, HEIGHT / 2 + 20);
            g.setFont(new Font("Times new Roman", 1, 40));
            g.drawString(": " + HEALTH, 50, HEIGHT - 60);
            g.drawString(": " + score, WIDTH - 80, HEIGHT - 60);
            HEALTH = 3;
            levelUp = 0;
        }

        if (!gameOver && started) {
            if (score % 2 == 0 && score!=0 && flag == 1){levelUp++;
                g.drawString("LEVEL UP!" + levelUp, 150, HEIGHT/2);
            }
            if (score % 2 == 0){
                flag = 0;
            }
            else flag = 1;
            g.setFont(new Font("Times new Roman", 1, 40));
            g.drawString(": " + HEALTH, 50, HEIGHT - 60);
            g.drawString(": " + score, WIDTH - 80, HEIGHT - 60);
        }
        if (see){
                g.setColor(Color.green.darker());
                g.fillRect(WIDTH / 2 - 160, HEIGHT / 2 - 142, 370, 230);
                g.drawImage(frm, WIDTH / 2 - 175, HEIGHT / 2 - 150, 400, 250, jframe);
                g.setFont(new Font("Times new Roman", 2, 20));
                g.setColor(Color.white);
                g.drawString("Score table: " , WIDTH / 2 - 60, HEIGHT / 2 - 80);
                g.drawString(Model.model.getTable().toString(), WIDTH / 2 - 60, HEIGHT / 2 - 60);
            }
    }

    public void setHEALTH(int h){
        HEALTH=h;
    }
    public void seeTable(boolean b) {see = b;}
    public void setScore(int s){
        score=s;
    }
    public Renderer getRenderer(){
        return renderer;
    }
    public void setTick(int i){
        ticks+=i;
    }
    public JFrame getJframe (){
        return jframe;
    }
    public Columns getC(){
        return c;
    }
    public Flowers getF(){
        return f;
    }
    public Lives getL(){
        return l;
    }
    public int getLevelUp(){
        return levelUp;
    }
    public void setBeeCharY(int y) {
        this.beeChar.y += y;
    }
    public Rectangle getBee() {
        return this.beeChar;
    }
    public void setBee() {
        beeChar = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 40, 40);
    }

    public void setGO(boolean b){
        gameOver = b;
    }
    public void setST(boolean b){
        started = b;
    }
}
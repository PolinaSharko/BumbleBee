package Model;
import View.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Model implements  ActionListener, MouseListener, KeyListener {

    public static Model model;
    private InputStream music;
    public int ticks, yMotion, score = 0, HEALTH = 3, speed, k, j;
    public boolean gameOver, started;
    private Columns c = View.vw.getC();
    private Flowers f =  View.vw.getF();
    private Lives l =  View.vw.getL();
    private SortedIntegerList scores = new SortedIntegerList(false);
    private final int HEIGHT = 650;

    public Model(){
        new Sound();
        try
        {
            music = new FileInputStream("C:\\Users\\Шмель\\Desktop\\BumbleBee\\Resources\\theme.wav");
            FileReader reader = new FileReader("scores.txt");
            int ch;
            while((ch=reader.read())!=-1){
                if (Character.getNumericValue((char) ch)!= -1) {
                    scores.add(Character.getNumericValue((char) ch));
                }
            }
        }
        catch (IOException ex){}
        JFrame jframe=View.vw.getJframe();
        Timer timer = new Timer(20, this);
        jframe.addMouseListener(this);
        jframe.addKeyListener(this);
        timer.start();
    }


    public void jump()
    {
        if ((!started)||(gameOver))
        {
            scores.add(score);
            View.vw.setBee();
            ticks = 0;
            yMotion = 0;
            View.vw.setScore(0);
            started = true;
            gameOver=false;
            View.vw.setST(started);
            View.vw.setGO(gameOver);
            scores.add(score);
            score = 0;
            try(FileWriter writer = new FileWriter("scores.txt", false))
            {
                writer.write(scores.toString());
                writer.flush();
            }
            catch(IOException ex){}
        }
        if (!gameOver)
        {
            if (yMotion > 0)
            {
                yMotion = 0;
            }

            yMotion -= 10;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {   View.vw.setTick(ticks++);
        if (View.vw.getLevelUp()!=0){speed = View.vw.getLevelUp()*2;}
        if (started)
        {
            if (ticks % 121 ==0) {
                c.addColumn(true);
                l.addLife(true);
                f.addFlow(true);
            }
            else {
                c.addColumn(false);
                l.addLife(false);
                f.addFlow(false);
            }
            if (c.getColumns().size()>2000){
                c.getColumns().clear();
                l.getLives().clear();
                f.getFlows().clear();
            }
            m:
            for (int i = 0; i < c.getColumns().size(); i++)
            {
                if (c.getColumns().get(i).intersects(View.vw.getBee()))
                {
                    k++;
                    HEALTH--;
                    View.vw.setHEALTH(HEALTH);
                    View.vw.getBee().x = c.getColumns().get(i).x - 125;
                    break m;
                }
                c.getColumns().get(i).x -= 5+speed;
            }
            if (k == 0) {
                for (int i = 0; i < l.getLives().size(); i++) {
                    if (l.getLives().get(i).intersects(View.vw.getBee())) {
                        HEALTH++;
                        View.vw.setHEALTH(HEALTH);
                        l.getLives().get(i).setRect(0, HEIGHT, 0, 0);
                    }
                    l.getLives().get(i).x -= 5 + speed;
                }

                for (int i = 0; i < f.getFlows().size(); i++) {
                    if (f.getFlows().get(i).intersects(View.vw.getBee())) {
                        score++;
                        View.vw.setScore(score);
                        f.getFlows().get(i).setRect(0, HEIGHT, 0, 0);
                    }
                    f.getFlows().get(i).x -= 5 + speed;
                }
            }
            else {
                k = 0;
            }
            if (ticks % 2 == 0 && yMotion < 15)
            {
                yMotion += 2;
            }
            View.vw.setBeeCharY(yMotion);

            if ( View.vw.getBee().y < 0 || View.vw.getBee().y > HEIGHT - 120 )
            {
                View.vw.setHEALTH(0);
                HEALTH=0;
            }
            if (HEALTH == 0){
                gameOver = true;
                View.vw.setGO(true);
                speed = 0;
                HEALTH = 3;
            }
        }
        View.vw.getRenderer().repaint();
    }

    public SortedIntegerList getTable(){return scores;}
    @Override
    public void mouseClicked(MouseEvent e)
    {
        jump();
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if (gameOver){
            speed=0;
            View.vw.setHEALTH(3);
            jump();
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            jump();
        }
    }

    public static void music()
    {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        ContinuousAudioDataStream loop = null;

        try
        {
            InputStream test = new FileInputStream("C:\\Users\\Шмель\\Desktop\\BumbleBee\\Resources\\theme.wav");
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
        }
        catch (IOException ex){}
        MGP.start(loop);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {

    }
}

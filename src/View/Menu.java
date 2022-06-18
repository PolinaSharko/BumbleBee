package View;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JMenuBar implements ActionListener{
    JMenu game = new JMenu("Game");
    JMenuItem newGame = new JMenuItem("New Game");
    JMenuItem loadScore = new JMenuItem("Load score table");

    public Menu(){
        newGame.addActionListener(this);
        loadScore.addActionListener(this);
        game.add(newGame);
        game.add(loadScore);
        add(game);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == newGame)
        {
            View.vw.setST(true);
         }
        if(e.getSource() == loadScore)
        {
            View.vw.seeTable(true);
        }
        else View.vw.seeTable(false);
    }
}
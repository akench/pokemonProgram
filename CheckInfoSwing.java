package pokemonstuff;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CheckInfoSwing
  extends JPanel implements ActionListener
{
  private static JFrame frame;
  private Pokemon currentPoke;
  private JTextArea textArea;
  private JButton backB;
  
  public CheckInfoSwing(Pokemon currentPoke)
  {
    this.currentPoke = currentPoke;
    backB = new JButton("<Back>");
    textArea = new JTextArea(20, 20);
    
    textArea.append(currentPoke.getPokeName().toString() + "\n   HP:" + currentPoke.getHp() + "/" + currentPoke.getMaxHp() + 
      "\n   LEVEL: " + currentPoke.getLevel() + "\n   EXP: " + currentPoke.getXp() + "/" + currentPoke.getMaxXp() + "\nATTACKS: ");
    
    for (int i = 0; i < currentPoke.getNumMoves(); i++) {
      textArea.append("\n" + currentPoke.getMoves()[i].getAttackName() + ";  DMG: " + currentPoke.getMoves()[i].getAttackDmg());
    }
    textArea.setEditable(false);
    
    add(textArea);
    add(backB);
    
    setLayout(new GridLayout(-1, 1));
    
    backB.setActionCommand("<Back>");
    backB.addActionListener(this);
  }
  
  public static void createAndShowGUI(Pokemon currentPoke)
  {
    frame = new JFrame("CLICK A POKEMON TO CHECK INFO");
    frame.setDefaultCloseOperation(3);
    CheckInfoSwing newContentPane = new CheckInfoSwing(currentPoke);
    newContentPane.setOpaque(true);
    frame.setContentPane(newContentPane);
    frame.pack();
    frame.setSize(StartingPokeSwing.WIDTH, StartingPokeSwing.HEIGHT);
    frame.setVisible(true);
  }
  
  public void actionPerformed(ActionEvent ae)
  {
    if (ae.getActionCommand().equals("<Back>")) {
      frame.dispose();
    }
  }
}

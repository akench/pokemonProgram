package pokemonstuff;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class FightSwingScreen
  extends JPanel
  implements ActionListener
{
  public static int WIDTH = 500; public static int HEIGHT = 200;
  private String m3Name;
  
  public FightSwingScreen(Pokemon currentPoke, Pokemon wildPoke) {
    this.currentPoke = currentPoke;
    this.wildPoke = wildPoke;
    if (currentPoke.getMoves()[0] != null) {
      m0Name = currentPoke.getMoves()[0].getAttackName().toString();
    }
    if (currentPoke.getMoves()[1] != null) {
      m1Name = currentPoke.getMoves()[1].getAttackName().toString();
    }
    if (currentPoke.getMoves()[2] != null) {
      m2Name = currentPoke.getMoves()[2].getAttackName().toString();
    }
    if (currentPoke.getMoves()[3] != null) {
      m3Name = currentPoke.getMoves()[3].getAttackName().toString();
    }
    
    move0 = new JButton(m0Name);
    move1 = new JButton(m1Name);
    move2 = new JButton(m2Name);
    move3 = new JButton(m3Name);
    backB = new JButton("<Back>");
    
    if (m0Name != null)
      add(move0);
    if (m1Name != null)
      add(move1);
    if (m2Name != null)
      add(move2);
    if (m3Name != null) {
      add(move3);
    }
    add(backB);
    
    setLayout(new GridLayout(-1, 1));
    
    move0.setActionCommand(m0Name);
    move0.addActionListener(this);
    
    move1.setActionCommand(m1Name);
    move1.addActionListener(this);
    
    move2.setActionCommand(m2Name);
    move2.addActionListener(this);
    
    move3.setActionCommand(m3Name);
    move3.addActionListener(this);
    
    backB.setActionCommand("<Back>");
    backB.addActionListener(this); }
  
  private String m2Name;
  private String m1Name;
  
  public static void createAndShowGUI(Pokemon currentPoke, Pokemon wildPoke) { frame = new JFrame("CHOOSE ATTACK");
    frame.setDefaultCloseOperation(3);
    FightSwingScreen newContentPane = new FightSwingScreen(currentPoke, wildPoke);
    newContentPane.setOpaque(true);
    frame.setContentPane(newContentPane);
    frame.pack();
    frame.setSize(WIDTH, HEIGHT);
    frame.setVisible(true); }
  
  private String m0Name;
  private JButton backB;
  private JButton move3;
  private JButton move2;
  
  public void actionPerformed(ActionEvent ae) { int moveNum = -1;
    if (ae.getActionCommand().equals("<Back>")) {
      frame.dispose();
    }
    else
    {
      if (ae.getActionCommand().equals(m0Name)) {
        moveNum = 0;
      } else if (ae.getActionCommand().equals(m1Name)) {
        moveNum = 1;
      } else if (ae.getActionCommand().equals(m2Name)) {
        moveNum = 2;
      } else if (ae.getActionCommand().equals(m3Name)) {
        moveNum = 3;
      }
      currentPoke.useMove(moveNum, wildPoke);
      
      if (wildPoke.getHp() == 0) {
        BattleSwingScreen.frame.dispose();
        MainSwingScreen.textArea.append("WILD " + wildPoke.getPokeName() + " has fainted!\n");
        currentPoke.gainXp(wildPoke);
      }
      else {
        int n = (int)(Math.random() * wildPoke.getNumMoves());
        wildPoke.useMove(n, currentPoke);
        if (currentPoke.getHp() == 0) {
          if (BattleSwingScreen.getPlayer().alivePokeCount() == 0) {
            BattleSwingScreen.frame.dispose();
            MainSwingScreen.textArea.append("\nAll of your Pokemon are dead! \nHeading to the healing center.\n");
            for (int j = 0; j < BattleSwingScreen.getPlayer().getParty().size(); j++) {
              ((Pokemon)BattleSwingScreen.getPlayer().getParty().get(j)).setHp(((Pokemon)BattleSwingScreen.getPlayer().getParty().get(j)).getMaxHp());
            }
          }
          else {
            FaintedSwingScreen.createAndShowGUI(BattleSwingScreen.getPlayer(), BattleSwingScreen.getCurrentPoke());
          }
        }
      }
    }
    

    frame.dispose();
  }
  
  private JButton move1;
  private JButton move0;
  private Pokemon wildPoke;
  private Pokemon currentPoke;
  private static JFrame frame;
}

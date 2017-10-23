package pokemonstuff;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CheckPokeSwing extends JPanel implements ActionListener
{
  private JButton backB;
  public static JFrame frame;
  private PokePlayer player;
  private JButton[] pokeButtons;
  private String[] pNames;
  private JLabel label;
  
  public CheckPokeSwing(PokePlayer player)
  {
    label = new JLabel("                          Click a Pokemon to check info on it");
    this.player = player;
    backB = new JButton("<Back>");
    pokeButtons = new JButton[player.getParty().size()];
    pNames = new String[pokeButtons.length];
    
    add(label);
    
    for (int i = 0; i < pokeButtons.length; i++) {
      pNames[i] = ((Pokemon)player.getParty().get(i)).getPokeName().toString();
      
      if (((Pokemon)player.getParty().get(i)).getHp() == 0) {
        int tmp126_125 = i; String[] tmp126_122 = pNames;tmp126_122[tmp126_125] = (tmp126_122[tmp126_125] + " FAINTED");
        pokeButtons[i].setEnabled(false);
      }
      pokeButtons[i] = new JButton(pNames[i]);
      add(pokeButtons[i]);
      pokeButtons[i].setActionCommand(pNames[i]);
      pokeButtons[i].addActionListener(this);
    }
    

    add(backB);
    
    setLayout(new GridLayout(-1, 1));
    
    backB.setActionCommand("<Back>");
    backB.addActionListener(this);
  }
  

  public static void createAndShowGUI(PokePlayer player, Pokemon currentPoke)
  {
    frame = new JFrame("YOUR PARTY");
    frame.setDefaultCloseOperation(3);
    CheckPokeSwing newContentPane = new CheckPokeSwing(player);
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
    else
    {
      for (int i = 0; i < player.getParty().size(); i++) {
        if (ae.getActionCommand().equals(pNames[i])) {
          CheckInfoSwing.createAndShowGUI((Pokemon)player.getParty().get(i));
        }
      }
    }
  }
}

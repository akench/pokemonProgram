package pokemonstuff;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ReviveSwingScreen extends JPanel implements ActionListener
{
  private JButton backB;
  public static JFrame frame;
  private PokePlayer player;
  private JButton[] pokeButtons;
  private String[] pNames;
  private Pokemon currentPoke;
  
  public ReviveSwingScreen(PokePlayer player, Pokemon currentPoke)
  {
    this.currentPoke = currentPoke;
    this.player = player;
    pokeButtons = new JButton[player.getParty().size()];
    pNames = new String[pokeButtons.length];
    
    for (int i = 0; i < pokeButtons.length; i++) {
      pNames[i] = ((Pokemon)player.getParty().get(i)).getPokeName().toString();
      if (currentPoke.equals(player.getParty().get(i))) {
        int tmp94_93 = i; String[] tmp94_90 = pNames;tmp94_90[tmp94_93] = (tmp94_90[tmp94_93] + " (Currently in battle)");
        pokeButtons[i] = new JButton(pNames[i]);
        add(pokeButtons[i]);
        pokeButtons[i].setEnabled(false);
      }
      else {
        if (((Pokemon)player.getParty().get(i)).getHp() == 0) {
          int tmp183_182 = i; String[] tmp183_179 = pNames;tmp183_179[tmp183_182] = (tmp183_179[tmp183_182] + " (FAINTED)");
          pokeButtons[i] = new JButton(pNames[i]);
          pokeButtons[i].setEnabled(true);
        }
        else {
          pokeButtons[i] = new JButton(pNames[i]);
          pokeButtons[i].setEnabled(false);
        }
        add(pokeButtons[i]);
      }
      
      pokeButtons[i].setActionCommand(pNames[i]);
      pokeButtons[i].addActionListener(this);
    }
    


    backB = new JButton("<Back>");
    add(backB);
    
    setLayout(new GridLayout(-1, 1));
    
    backB.setActionCommand("<Back>");
    backB.addActionListener(this);
  }
  

  public static void createAndShowGUI(PokePlayer player, Pokemon currentPoke)
  {
    frame = new JFrame("YOUR PARTY");
    frame.setDefaultCloseOperation(3);
    ReviveSwingScreen newContentPane = new ReviveSwingScreen(player, currentPoke);
    newContentPane.setOpaque(true);
    frame.setContentPane(newContentPane);
    frame.pack();
    frame.setSize(StartingPokeSwing.WIDTH, StartingPokeSwing.HEIGHT);
    frame.setVisible(true);
  }
  
  public void actionPerformed(ActionEvent ae)
  {
    BattleSwingScreen.textArea.append("\n");
    if (ae.getActionCommand().equals("<Back>")) {
      frame.dispose();
    }
    else
    {
      for (int i = 0; i < player.getParty().size(); i++) {
        if (ae.getActionCommand().equals(pNames[i])) {
          player.useRevive((Pokemon)player.getParty().get(i));
        }
      }
    }
    frame.dispose();
  }
}

package pokemonstuff;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FaintedSwingScreen extends JPanel implements ActionListener
{
  public static JFrame frame;
  private PokePlayer player;
  private JButton[] pokeButtons;
  private String[] pNames;
  private Pokemon currentPoke;
  private JLabel label;
  
  public FaintedSwingScreen(PokePlayer player, Pokemon currentPoke)
  {
    this.currentPoke = currentPoke;
    this.player = player;
    pokeButtons = new JButton[player.getParty().size()];
    pNames = new String[pokeButtons.length];
    
    label = new JLabel("                    " + currentPoke.getPokeName() + " has fainted! Choose a Pokemon to send out!");
    add(label);
    
    for (int i = 0; i < pokeButtons.length; i++) {
      pNames[i] = ((Pokemon)player.getParty().get(i)).getPokeName().toString();
      if (((Pokemon)player.getParty().get(i)).getHp() == 0) {
        int tmp140_139 = i; String[] tmp140_136 = pNames;tmp140_136[tmp140_139] = (tmp140_136[tmp140_139] + " (FAINTED)");
      }
      pokeButtons[i] = new JButton(pNames[i]);
      add(pokeButtons[i]);
      pokeButtons[i].setEnabled(true);
      if (((Pokemon)player.getParty().get(i)).getHp() == 0) {
        pokeButtons[i].setEnabled(false);
      }
      pokeButtons[i].setActionCommand(pNames[i]);
      pokeButtons[i].addActionListener(this);
    }
    


    setLayout(new GridLayout(-1, 1));
  }
  

  public static void createAndShowGUI(PokePlayer player, Pokemon currentPoke)
  {
    frame = new JFrame("YOUR PARTY");
    frame.setDefaultCloseOperation(3);
    FaintedSwingScreen newContentPane = new FaintedSwingScreen(player, currentPoke);
    newContentPane.setOpaque(true);
    frame.setContentPane(newContentPane);
    frame.pack();
    frame.setSize(StartingPokeSwing.WIDTH, StartingPokeSwing.HEIGHT);
    frame.setVisible(true);
  }
  

  public void actionPerformed(ActionEvent ae)
  {
    for (int i = 0; i < player.getParty().size(); i++) {
      if (ae.getActionCommand().equals(pNames[i])) {
        currentPoke = ((Pokemon)player.getParty().get(i));
      }
    }
    BattleSwingScreen.textArea.append("\nGo " + currentPoke.getPokeName() + "!");
    BattleSwingScreen.setCurrentPoke(currentPoke);
    BattleSwingScreen.updateBar(currentPoke);
    frame.dispose();
  }
}

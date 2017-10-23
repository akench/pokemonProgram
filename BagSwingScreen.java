package pokemonstuff;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class BagSwingScreen
  extends JPanel implements ActionListener
{
  private List<Item> items;
  private String[] itemNames;
  private JButton[] itemButtons;
  private static JFrame frame;
  private JButton backB;
  private PokePlayer player;
  private Pokemon currentPoke;
  private Pokemon wildPoke;
  
  public BagSwingScreen(PokePlayer player, Pokemon currentPoke, Pokemon wildPoke)
  {
    this.player = player;
    this.currentPoke = currentPoke;
    this.wildPoke = wildPoke;
    items = player.getItemBag().getItemsInBag();
    itemNames = new String[items.size()];
    itemButtons = new JButton[6];
    
    for (int k = 0; k < items.size(); k++) {
      itemButtons[k] = new JButton(((Item)items.get(k)).getName() + "  " + ((Item)items.get(k)).getAmount() + "x");
      itemNames[k] = (((Item)items.get(k)).getName() + "  " + ((Item)items.get(k)).getAmount() + "x");
      add(itemButtons[k]);
      itemButtons[k].setActionCommand(itemNames[k]);
      itemButtons[k].addActionListener(this);
    }
    
    setLayout(new GridLayout(-1, 1));
    
    backB = new JButton("<Back>");
    add(backB);
    backB.setActionCommand(" <Back>");
    backB.addActionListener(this);
  }
  

  public static void createAndShowGUI(PokePlayer player, Pokemon currentPoke, Pokemon wildPoke)
  {
    frame = new JFrame("BAG ITEMS");
    frame.setDefaultCloseOperation(3);
    BagSwingScreen newContentPane = new BagSwingScreen(player, currentPoke, wildPoke);
    newContentPane.setOpaque(true);
    frame.setContentPane(newContentPane);
    frame.pack();
    frame.setSize(StartingPokeSwing.WIDTH, StartingPokeSwing.HEIGHT);
    frame.setVisible(true);
  }
  
  public void actionPerformed(ActionEvent ae)
  {
    BattleSwingScreen.textArea.append("\n");
    
    if (!ae.getActionCommand().equals("<Back>")) {
      if (ae.getActionCommand().equals(itemNames[1])) {
        if (!player.usePokeball(wildPoke)) {
          int n = (int)(Math.random() * wildPoke.getNumMoves());
          wildPoke.useMove(n, currentPoke);
        }
        else {
          BattleSwingScreen.frame.dispose();
        }
      }
      else if (ae.getActionCommand().equals(itemNames[0])) {
        player.usePotion(currentPoke);
        BattleSwingScreen.textArea.append("\nUsed Potion on " + currentPoke.getPokeName());
        BattleSwingScreen.updateBar(currentPoke);
        int n = (int)(Math.random() * BattleSwingScreen.getWildPoke().getNumMoves());
        BattleSwingScreen.getWildPoke().useMove(n, currentPoke);
        if (currentPoke.getHp() == 0) {
          if (BattleSwingScreen.getPlayer().alivePokeCount() == 0) {
            BattleSwingScreen.frame.dispose();
            MainSwingScreen.textArea.append("All of your Pokemon are dead! Heading to the healing center.");
            for (int j = 0; j < BattleSwingScreen.getPlayer().getParty().size(); j++) {
              ((Pokemon)BattleSwingScreen.getPlayer().getParty().get(j)).setHp(((Pokemon)BattleSwingScreen.getPlayer().getParty().get(j)).getMaxHp());
            }
          }
          else {
            FaintedSwingScreen.createAndShowGUI(BattleSwingScreen.getPlayer(), BattleSwingScreen.getCurrentPoke());
          }
        }
      }
      else if (ae.getActionCommand().equals(itemNames[2])) {
        ReviveSwingScreen.createAndShowGUI(player, currentPoke);
        int n = (int)(Math.random() * BattleSwingScreen.getWildPoke().getNumMoves());
        BattleSwingScreen.getWildPoke().useMove(n, currentPoke);
        if (currentPoke.getHp() == 0) {
          if (BattleSwingScreen.getPlayer().alivePokeCount() == 0) {
            BattleSwingScreen.frame.dispose();
            MainSwingScreen.textArea.append("All of your Pokemon are dead! Heading to the healing center.");
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
}

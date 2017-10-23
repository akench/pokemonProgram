package pokemonstuff;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;



public class MainSwingScreen
  extends JPanel
  implements ActionListener
{
  private PokePlayer player;
  private JButton walkGrassB;
  private JButton checkPartyB;
  private JButton healPokeB;
  private JButton quitGameB;
  private JScrollPane scrollPane;
  public static JTextArea textArea;
  private static JSplitPane splitPane;
  private static JPanel topPanel;
  private static JPanel bottomPanel;
  
  public MainSwingScreen(PokePlayer player)
  {
    this.player = player;
    walkGrassB = new JButton("Walk in Grass");
    checkPartyB = new JButton("Check Party");
    healPokeB = new JButton("Heal pokemon");
    quitGameB = new JButton("Quit Game");
    
    textArea = new JTextArea(15, 30);
    scrollPane = new JScrollPane(textArea);
    textArea.setEditable(false);
    
    topPanel = new JPanel();
    bottomPanel = new JPanel();
    splitPane = new JSplitPane(0, topPanel, bottomPanel);
    
    textArea.append("You chose " + ((Pokemon)player.getParty().get(0)).getPokeName() + "!");
    textArea.append("\nYou also gain a PIDGEY pokemon!");
    
    add(splitPane);
    topPanel.add(walkGrassB);
    topPanel.add(checkPartyB);
    topPanel.add(healPokeB);
    topPanel.add(quitGameB);
    topPanel.add(scrollPane);
    bottomPanel.add(scrollPane);
    
    topPanel.setLayout(new GridLayout(2, 2));
    
    walkGrassB.setActionCommand("Walk in Grass");
    walkGrassB.addActionListener(this);
    
    checkPartyB.setActionCommand("Check Party");
    checkPartyB.addActionListener(this);
    
    healPokeB.setActionCommand("Heal pokemon");
    healPokeB.addActionListener(this);
    
    quitGameB.setActionCommand("Quit Game");
    quitGameB.addActionListener(this);
  }
  
  public static void createAndShowGUI(PokePlayer player)
  {
    JFrame frame = new JFrame("Choose an Action");
    frame.setDefaultCloseOperation(3);
    MainSwingScreen newContentPane = new MainSwingScreen(player);
    newContentPane.setOpaque(true);
    frame.setContentPane(newContentPane);
    frame.pack();
    frame.setSize(StartingPokeSwing.WIDTH, StartingPokeSwing.HEIGHT);
    frame.setVisible(true);
  }
  

  public void actionPerformed(ActionEvent ae)
  {
    Pokemon currentPoke = null;
    Pokemon wildPoke = null;
    int chance = (int)(Math.random() * 100.0D);
    for (int i = 0; i < player.getParty().size(); i++) {
      if (((Pokemon)player.getParty().get(i)).getHp() > 0) {
        currentPoke = (Pokemon)player.getParty().get(i);
        break;
      }
    }
    
    if (ae.getActionCommand().equals("Walk in Grass"))
    {
      textArea.append("\nYou walk in Grass");
      if (chance <= 75) {
        if (chance <= 25) {
          wildPoke = new Pokemon(PokeName.PIDGEY);
        } else if ((chance > 25) && (chance <= 50)) {
          wildPoke = new Pokemon(PokeName.RATTATA);
        } else if (chance > 50) {
          wildPoke = new Pokemon(PokeName.BIDOOF);
        }
        BattleSwingScreen.createAndShowGUI(player, currentPoke, wildPoke);
      }
      else {
        textArea.append("\nYou find no Pokemon in the grass");
      }
    }
    else if (ae.getActionCommand().equals("Check Party")) {
      CheckPokeSwing.createAndShowGUI(player, currentPoke);
    }
    else if (ae.getActionCommand().equals("Heal pokemon")) {
      for (int j = 0; j < player.getParty().size(); j++) {
        ((Pokemon)player.getParty().get(j)).setHp(((Pokemon)player.getParty().get(j)).getMaxHp());
      }
      textArea.append("\nYou heal your Pokemon at the pokemon center");
    }
    else if (ae.getActionCommand().equals("Quit Game")) {
      System.exit(0);
    }
  }
}

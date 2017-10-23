package pokemonstuff;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;


public class BattleSwingScreen
  extends JPanel
  implements ActionListener
{
  public static JFrame frame;
  private static PokePlayer player;
  private static Pokemon currentPoke;
  private static Pokemon wildPoke;
  private JButton fightB;
  private JButton pokemonB;
  private JButton bagB;
  private JButton runB;
  private static JProgressBar wildPokeHp;
  private static JProgressBar myPokeHp;
  private JScrollPane scrollPane;
  public static JTextArea textArea;
  private static JSplitPane splitPane;
  private static JPanel topPanel;
  private static JPanel bottomPanel;
  
  public BattleSwingScreen(PokePlayer player, Pokemon currentPoke, Pokemon wildPoke)
  {
    player = player;
    currentPoke = currentPoke;
    wildPoke = wildPoke;
    fightB = new JButton("Fight");
    pokemonB = new JButton("Switch Pokemon");
    bagB = new JButton("Bag");
    runB = new JButton("Run");
    
    topPanel = new JPanel();
    bottomPanel = new JPanel();
    splitPane = new JSplitPane(0, topPanel, bottomPanel);
    
    textArea = new JTextArea(17, 27);
    scrollPane = new JScrollPane(textArea);
    textArea.setEditable(false);
    
    textArea.append("A wild " + wildPoke.getPokeName().toString() + " has appeared!");
    textArea.append("\nGO " + currentPoke.getPokeName() + "!");
    
    myPokeHp = new JProgressBar(0, currentPoke.getMaxHp());
    myPokeHp.setValue(100);
    myPokeHp.setStringPainted(true);
    myPokeHp.setForeground(Color.RED);
    updateBar(currentPoke);
    
    wildPokeHp = new JProgressBar(0, wildPoke.getMaxHp());
    wildPokeHp.setValue(100);
    wildPokeHp.setStringPainted(true);
    wildPokeHp.setForeground(Color.RED);
    updateBar(wildPoke);
    


    add(splitPane);
    topPanel.add(fightB);
    topPanel.add(pokemonB);
    topPanel.add(bagB);
    topPanel.add(runB);
    topPanel.add(myPokeHp, "South");
    topPanel.add(wildPokeHp, "South");
    
    topPanel.setLayout(new GridLayout(3, 2));
    
    bottomPanel.add(scrollPane);
    
    updateBar(currentPoke);
    
    fightB.setActionCommand("Fight");
    fightB.addActionListener(this);
    
    pokemonB.setActionCommand("Switch Pokemon");
    pokemonB.addActionListener(this);
    
    bagB.setActionCommand("Bag");
    bagB.addActionListener(this);
    
    runB.setActionCommand("Run");
    runB.addActionListener(this);
  }
  




  public static void createAndShowGUI(PokePlayer player, Pokemon currentPoke, Pokemon wildPoke)
  {
    frame = new JFrame("BATTLE");
    frame.setDefaultCloseOperation(3);
    BattleSwingScreen newContentPane = new BattleSwingScreen(player, currentPoke, wildPoke);
    newContentPane.setOpaque(true);
    frame.setContentPane(newContentPane);
    frame.pack();
    frame.setSize(StartingPokeSwing.WIDTH, StartingPokeSwing.HEIGHT);
    frame.setVisible(true);
  }
  
  public static void setCurrentPoke(Pokemon p)
  {
    currentPoke = p;
  }
  
  public static void updateBar(Pokemon poke) {
    if (poke.equals(currentPoke)) {
      myPokeHp.setString(currentPoke.getPokeName() + ": " + currentPoke.getHp() + "/" + currentPoke.getMaxHp() + " HP");
      myPokeHp.setValue(currentPoke.getHp());
    }
    else if (poke.equals(wildPoke)) {
      wildPokeHp.setString("WILD " + wildPoke.getPokeName() + ": " + wildPoke.getHp() + "/" + wildPoke.getMaxHp() + " HP");
      wildPokeHp.setValue(wildPoke.getHp());
    }
  }
  
  public static PokePlayer getPlayer() {
    return player;
  }
  
  public static Pokemon getCurrentPoke() {
    return currentPoke;
  }
  
  public static Pokemon getWildPoke() {
    return wildPoke;
  }
  
  public void actionPerformed(ActionEvent ae)
  {
    MainSwingScreen.textArea.append("\n");
    if (ae.getActionCommand().equals("Fight")) {
      FightSwingScreen.createAndShowGUI(currentPoke, wildPoke);
    }
    else if (ae.getActionCommand().equals("Switch Pokemon")) {
      SwitchPokeSwing.createAndShowGUI(player, currentPoke);
    }
    else if (ae.getActionCommand().equals("Bag")) {
      BagSwingScreen.createAndShowGUI(player, currentPoke, wildPoke);
    }
    else if (ae.getActionCommand().equals("Run")) {
      Random r = new Random();
      if (r.nextInt() * 100 < 70) {
        MainSwingScreen.textArea.append("\nYou run away");
        frame.dispose();
      }
      else {
        textArea.append("\nYou can't escape!");
        int n = (int)(Math.random() * wildPoke.getNumMoves());
        wildPoke.useMove(n, currentPoke);
      }
    }
  }
}

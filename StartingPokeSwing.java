package pokemonstuff;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class StartingPokeSwing
  extends JPanel
  implements ActionListener
{
  public static int WIDTH = 500;
  public static int HEIGHT = 500;
  private JButton charmanderSelection;
  private JButton squirtleSelection;
  private JButton bulbasaurSelection;
  private static JFrame frame;
  
  public StartingPokeSwing() {
    charmanderSelection = new JButton("Charmander");
    squirtleSelection = new JButton("Squirtle");
    bulbasaurSelection = new JButton("Bulbasaur");
    
    setLayout(new GridLayout(3, 1));
    
    add(charmanderSelection);
    add(squirtleSelection);
    add(bulbasaurSelection);
    
    charmanderSelection.setActionCommand("Charmander");
    charmanderSelection.addActionListener(this);
    
    squirtleSelection.setActionCommand("Squirtle");
    squirtleSelection.addActionListener(this);
    
    bulbasaurSelection.setActionCommand("Bulbasaur");
    bulbasaurSelection.addActionListener(this);
  }
  
  private static void createAndShowGUI() {
    frame = new JFrame("CHOOSE YOUR STARTING POKEMON");
    frame.setDefaultCloseOperation(3);
    StartingPokeSwing newContentPane = new StartingPokeSwing();
    newContentPane.setOpaque(true);
    frame.setContentPane(newContentPane);
    frame.pack();
    frame.setSize(WIDTH, HEIGHT);
    frame.setVisible(true);
  }
  

  public void actionPerformed(ActionEvent ae)
  {
    PokeName pokeName = null;
    
    if (ae.getActionCommand().equals("Charmander")) {
      pokeName = PokeName.CHARMANDER;
    }
    else if (ae.getActionCommand().equals("Squirtle")) {
      pokeName = PokeName.SQUIRTLE;
    }
    else if (ae.getActionCommand().equals("Bulbasaur")) {
      pokeName = PokeName.BULBASAUR;
    }
    PokePlayer player = new PokePlayer("User", pokeName);
    MainSwingScreen.createAndShowGUI(player);
    frame.dispose();
  }
  

  public static void main(String[] args)
  {
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run() {}
    });
  }
}

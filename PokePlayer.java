package pokemonstuff;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JTextArea;

public class PokePlayer
{
  private List<Pokemon> party;
  private String playerName;
  private Bag itemBag;
  
  public PokePlayer(String name, PokeName pokename)
  {
    party = new ArrayList();
    party.add(new Pokemon(pokename));
    party.add(new Pokemon(PokeName.PIDGEY));
    
    List<Item> itemList = new ArrayList();
    itemList.add(new Item("Potion", 5));
    itemList.add(new Item("Pokeball", 5));
    itemList.add(new Item("Revive", 2));
    itemBag = new Bag(itemList);
  }
  
  public String getPlayerName()
  {
    return playerName;
  }
  
  public void setPlayerName(String playerName) { this.playerName = playerName; }
  
  public List<Pokemon> getParty() {
    return party;
  }
  
  public void setParty(List<Pokemon> party) { this.party = party; }
  


  public Bag getItemBag() { return itemBag; }
  
  public int alivePokeCount() {
    int ret = 0;
    
    for (int i = 0; i < party.size(); i++) {
      if (((Pokemon)party.get(i)).getHp() > 0) {
        ret++;
      }
    }
    
    return ret;
  }
  
  public void usePotion(Pokemon currentPoke)
  {
    currentPoke.setHp(currentPoke.getHp() + 40);
    if (currentPoke.getHp() > currentPoke.getMaxHp()) {
      currentPoke.setHp(currentPoke.getMaxHp());
    }
    ((Item)getItemBag().getItemsInBag().get(0)).decrementAmount(1);
  }
  
  public boolean usePokeball(Pokemon wildPoke)
  {
    Random r = new Random();
    int catchRate = (int)((3.0D * wildPoke.getMaxHp() - 2.0D * wildPoke.getHp()) / (3.0D * wildPoke.getMaxHp()) * 100.0D);
    
    if (party.size() >= 6) {
      BattleSwingScreen.textArea.append("You cannot use a Pokeball because your party is full!");
      return false;
    }
    
    if (r.nextInt() * 100 < catchRate) {
      MainSwingScreen.textArea.append("\nYou caught " + wildPoke.getPokeName() + "!");
      BattleSwingScreen.frame.dispose();
      party.add(wildPoke);
      ((Item)getItemBag().getItemsInBag().get(1)).decrementAmount(1);
      return true;
    }
    

    BattleSwingScreen.textArea.append("\n" + wildPoke.getPokeName() + " escaped!");
    ((Item)getItemBag().getItemsInBag().get(1)).decrementAmount(1);
    return false;
  }
  
  public void useRevive(Pokemon deadPoke)
  {
    deadPoke.setHp(deadPoke.getMaxHp());
    ((Item)getItemBag().getItemsInBag().get(2)).decrementAmount(1);
    BattleSwingScreen.textArea.append("\n" + deadPoke.getPokeName() + " was revived!");
  }
}

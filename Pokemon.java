package pokemonstuff;

import javax.swing.JTextArea;

public class Pokemon {
  private int hp;
  private int maxHp;
  private int level;
  private int xp;
  
  public Pokemon(PokeName name) { pokeName = name;
    moves = new PokeMove[4];
    
    if (pokeName == PokeName.CHARMANDER) {
      moves[0] = new PokeMove(AttackName.SCRATCH, 10);
      moves[1] = new PokeMove(AttackName.EMBER, 20);
      moves[2] = null;
      moves[3] = null;
      xpGainedWhenKilled = 60;
    }
    
    if (pokeName == PokeName.SQUIRTLE) {
      moves[0] = new PokeMove(AttackName.POUND, 10);
      moves[1] = new PokeMove(AttackName.WATERGUN, 20);
      moves[2] = null;
      moves[3] = null;
      xpGainedWhenKilled = 60;
    }
    
    if (pokeName == PokeName.BULBASAUR) {
      moves[0] = new PokeMove(AttackName.TACKLE, 10);
      moves[1] = new PokeMove(AttackName.ABSORB, 20);
      moves[2] = null;
      moves[3] = null;
      xpGainedWhenKilled = 60;
    }
    
    if (pokeName == PokeName.PIDGEY) {
      moves[0] = new PokeMove(AttackName.SCRATCH, 10);
      moves[1] = new PokeMove(AttackName.SANDATTACK, 5);
      moves[2] = null;
      moves[3] = null;
      xpGainedWhenKilled = 50;
    }
    
    if (pokeName == PokeName.RATTATA) {
      moves[0] = new PokeMove(AttackName.POUND, 15);
      moves[1] = new PokeMove(AttackName.TAILWHIP, 10);
      moves[2] = null;
      moves[3] = null;
      xpGainedWhenKilled = 70;
    }
    
    if (pokeName == PokeName.BIDOOF) {
      moves[0] = new PokeMove(AttackName.TACKLE, 5);
      moves[1] = new PokeMove(AttackName.BITE, 20);
      moves[2] = null;
      moves[3] = null;
      xpGainedWhenKilled = 100;
    }
    
    hp = 50;
    maxHp = 50;
    xp = 0;
    maxXp = 70;
    level = 1; }
  
  private int maxXp;
  private int xpGainedWhenKilled;
  private PokeName pokeName;
  private PokeMove[] moves;
  public void useMove(int moveNum, Pokemon defendingPoke) { BattleSwingScreen.textArea.append("\n" + pokeName + " used " + moves[moveNum].getAttackName());
    defendingPoke.setHp(defendingPoke.getHp() - moves[moveNum].getAttackDmg());
    if (defendingPoke.getHp() < 0) {
      defendingPoke.setHp(0);
    }
    BattleSwingScreen.textArea.append("\n   " + defendingPoke.getPokeName() + 
      " took " + moves[moveNum].getAttackDmg() + " damage");
    BattleSwingScreen.updateBar(defendingPoke);
  }
  
  public void gainXp(Pokemon deadPoke) {
    xp += deadPoke.getXpGainedWhenKilled();
    MainSwingScreen.textArea.append(getPokeName() + " has gained " + deadPoke.getXpGainedWhenKilled() + " xp!");
    while (xp >= maxXp) {
      xp -= maxXp;
      level += 1;
      maxXp = ((int)(maxXp * 1.25D));
    }
    MainSwingScreen.textArea.append("\n" + getPokeName() + " is now level " + getLevel());
  }
  
  public int getNumMoves() {
    int ret = 0;
    for (int i = 0; i < 4; i++) {
      if (moves[i] != null)
        ret++;
    }
    return ret;
  }
  
  public PokeName getPokeName() {
    return pokeName;
  }
  
  public void setPokeName(PokeName pokeName) {
    this.pokeName = pokeName;
  }
  
  public int getHp() {
    return hp;
  }
  
  public void setHp(int health) {
    hp = health;
  }
  
  public int getMaxHp() {
    return maxHp;
  }
  
  public void setMaxHp(int hp) {
    maxHp = hp;
  }
  
  public int getLevel() {
    return level;
  }
  
  public void setLevel(int input) {
    level = input;
  }
  
  public int getXp() {
    return xp;
  }
  
  public void setXp(int myXp) {
    xp = myXp;
  }
  
  public int getMaxXp() {
    return maxXp;
  }
  
  public void setMaxXp(int maxXp) {
    this.maxXp = maxXp;
  }
  
  public PokeMove[] getMoves() { return moves; }
  
  public void setMoves(PokeMove input, int index) {
    moves[index] = input;
  }
  
  public int getXpGainedWhenKilled() { return xpGainedWhenKilled; }
}

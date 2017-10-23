package pokemonstuff;

public class PokeMove
{
  private AttackName attackName;
  private int attackDmg;
  
  public PokeMove(AttackName name, int dmg)
  {
    attackName = name;
    attackDmg = dmg;
  }
  
  public AttackName getAttackName() {
    return attackName;
  }
  
  public int getAttackDmg() { return attackDmg; }
}

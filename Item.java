package pokemonstuff;

public class Item
{
  private String name;
  private int amount;
  
  public Item(String n, int a) {
    name = n;
    amount = a;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String n) {
    name = n;
  }
  
  public int getAmount() {
    return amount;
  }
  
  public void IncrementAmount(int i) {
    amount += i;
  }
  
  public void decrementAmount(int d) {
    amount -= d;
  }
}

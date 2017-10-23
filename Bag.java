package pokemonstuff;

import java.util.List;

public class Bag
{
  private List<Item> itemsInBag;
  
  public Bag(List<Item> input)
  {
    itemsInBag = new java.util.ArrayList();
    for (int i = 0; i < input.size(); i++) {
      itemsInBag.add((Item)input.get(i));
    }
  }
  
  public List<Item> getItemsInBag() {
    return itemsInBag;
  }
  
  public void setItemsInBag(List<Item> input) {
    itemsInBag = input;
  }
  
  public int getIndexOfItem(String itemName) {
    for (int k = 0; k < itemsInBag.size(); k++) {
      if (((Item)itemsInBag.get(k)).getName().equals(itemName))
        return k;
    }
    return -1;
  }
}

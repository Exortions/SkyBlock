// This was made on a text editor, classes aren't imported and code isn't tested
package skyblock.Mechanics.Anvil;

public class AnvilActions {
 
  public void combineItems(Inventory inv, Player player){
    ItemStack air = new ItemStack(Material.AIR);
    inv.setItem(29, air);
    inv.setItem(33, air);
    // Code to take slot 29 & 33 and combine them to make item in slot 13
    // Blank space cause I dont know how to do that yet
    player.playSound(Sound.ANVIL_COMBINE, 100, 1);
  }
  
}

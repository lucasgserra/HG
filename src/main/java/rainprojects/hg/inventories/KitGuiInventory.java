package rainprojects.hg.inventories;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class KitGuiInventory {

    public static void gui(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, "§aKits");

        player.openInventory(inv);
    }

}

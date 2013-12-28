package plasticraft.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import plasticraft.PlastiCraft;

public class ContainerGrindStone extends Container{

	private InventoryPlayer player;
	
	public ContainerGrindStone(InventoryPlayer player) {
		this.player = player;
		
		for(int x=0; x < 9; x++){
			addSlotToContainer(new Slot(this.player, x, 8 + 18 * x, 130));
		}
		
		for(int y = 0; y<3; y++){
			for(int x = 0; x < 9; x++){
				addSlotToContainer(new Slot(this.player,x + y * 9 + 9,8 + 18 * x, 72 + y * 18));
			}
		}
		
		PlastiCraft.info("Container");
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}
	
	@Override
	public void removeCraftingFromCrafters(ICrafting crafting){
		PlastiCraft.info("saved item (called)");
		super.removeCraftingFromCrafters(crafting);
		
	}

}

package plasticraft.client.interfaces;

import plasticraft.tileentities.TeGrindStone;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class SlotGrindStone extends Slot
{
    /** The player that is using the GUI where this slot resides. */
    private EntityPlayer thePlayer;
    private TeGrindStone teGrindStone;
    private int field_75228_b;

    public SlotGrindStone(EntityPlayer par1EntityPlayer, TeGrindStone teGrindStone, int par3, int par4, int par5)
    {
        super(teGrindStone, par3, par4, par5);
        this.thePlayer = par1EntityPlayer;
        this.teGrindStone = teGrindStone;
    }

    /**
     * Check if the stack is a valid item for this slot. Always false cause it's an output.
     */
    public boolean isItemValid(ItemStack par1ItemStack)
    {
        return false;
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    public ItemStack decrStackSize(int par1)
    {
        if (this.getHasStack())
        {
            this.field_75228_b += Math.min(par1, this.getStack().stackSize);
        }

        return super.decrStackSize(par1);
    }

    public void onPickupFromSlot(EntityPlayer player, ItemStack itemStack)
    {
        this.onCrafting(itemStack);
        super.onPickupFromSlot(player, itemStack);
        if (player.experienceLevel >= this.teGrindStone.experienceCost)
        {
            player.experienceLevel -= this.teGrindStone.experienceCost;
        }
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
     * internal count then calls onCrafting(item).
     */
    protected void onCrafting(ItemStack par1ItemStack, int par2)
    {
        this.field_75228_b += par2;
        this.onCrafting(par1ItemStack);
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
     */
    protected void onCrafting(ItemStack par1ItemStack)
    {
        par1ItemStack.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.field_75228_b);

        GameRegistry.onItemSmelted(thePlayer, par1ItemStack);
    }
}
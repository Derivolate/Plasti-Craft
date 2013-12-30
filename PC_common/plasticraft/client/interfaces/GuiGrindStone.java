package plasticraft.client.interfaces;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import plasticraft.PlastiCraft;
import plasticraft.lib.References;
import plasticraft.tileentities.TeGrindStone;

public class GuiGrindStone extends GuiContainer {

	private ResourceLocation texture = new ResourceLocation(References.MOD_ID.toLowerCase(), "textures/gui/grindstone.png");
	
	private TeGrindStone teGrindStone;
	
	public GuiGrindStone(InventoryPlayer player, TeGrindStone grindstone){
		super(new ContainerGrindStone(player, grindstone));
		this.teGrindStone = grindstone;
		xSize = 176;
		ySize = 154;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j){
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		PlastiCraft.info("getProgressScaled:");
		
		int progressBar = this.teGrindStone.getProgressScaled(34);
		
		PlastiCraft.info(progressBar);
		if (progressBar != 0)
		{
			this.drawTexturedModalRect(guiLeft + 71, guiTop + 21, 176, 0, progressBar + 1, 15);
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRenderer.drawString("Grindstone", 43, 6, 4210752);
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, this.guiLeft + 43, this.guiTop + 40, 90, 20, "Grind"));
	}
	
	protected void actionPerformed(GuiButton button)
	{
		if (button.id == 0)
		{
			this.teGrindStone.grindTime = 1;
		}
	}
}

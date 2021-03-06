package plasticraft.client.interfaces;

import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import plasticraft.lib.References;
import plasticraft.tileentities.TileEntityCarbonFormer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCarbonFormer extends GuiContainer{

	private TileEntityCarbonFormer carbonformer;
	
	public GuiCarbonFormer(InventoryPlayer invPlayer, TileEntityCarbonFormer carbonformer) {
		super(new ContainerCarbonformer(invPlayer,carbonformer));
		this.carbonformer = carbonformer;
		xSize = 176;
		ySize = 154;
	}
	
	private static final ResourceLocation texture = new ResourceLocation(References.MOD_ID.toLowerCase(), "textures/gui/carbonformer.png");
	private static final ResourceLocation plasticTexture = new ResourceLocation(References.MOD_ID.toLowerCase(), "textures/blocks/fluidplastic_still.png");

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		int i1;
		
		
		i1 = this.carbonformer.getCookProgressScaled(34);
		if(i1 != 0){
			this.drawTexturedModalRect(guiLeft + 48, guiTop + 16, 176, 0, (int) i1 +1, 15);
		}
		
		int i2;
		if(this.carbonformer.tank.getFluidAmount() != 0){
			Minecraft.getMinecraft().getTextureManager().bindTexture(plasticTexture);
			i2 = this.carbonformer.getFluidAmountScaled(32);
			this.drawTexturedModalRect(guiLeft + 100,guiTop + 17 + 32 - i2 ,0, 0, 16, i2);
		}
		if(x <= guiLeft + 116 && x >= guiLeft + 100 && y <= guiTop + 49 && y >= guiTop + 17){
			String text = GuiColor.YELLOW.toString() + this.carbonformer.tank.getFluidAmount() + "/" + this.carbonformer.tank.getCapacity()+"mB";
			if(this.carbonformer.tank.getFluid()!= null){
				text += "\n" + this.carbonformer.tank.getFluid().getFluid().getName();
			}
			this.drawHoveringText(Arrays.asList(text.split("\n")),x , y , fontRendererObj);
		}
	}
	

}

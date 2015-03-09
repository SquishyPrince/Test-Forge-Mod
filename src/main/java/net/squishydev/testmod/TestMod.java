package net.squishydev.testmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid="TestMod",name="Test Mod", version="1.0.0")

public class TestMod {

	public static Item tool;
	public static Item ignot;
	public final static Block ore = new Ore(Material.rock);
	
	@Instance(value="TestMod")
	public static TestMod instance;
	
	@SidedProxy(clientSide="net.squishydev.testmod.client.ClientProxy",
			serverSide="net.squishydev.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		tool = new Tool();
		ignot = new Ignot();
		GameRegistry.registerItem(tool, "Tool");
		GameRegistry.registerItem(ignot, "Ignot");
		GameRegistry.registerBlock(ore,  "Ore");
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
		ItemStack dirt = new ItemStack(Blocks.dirt);
		ItemStack diamonds = new ItemStack(Items.diamond, 64);
		ItemStack gravel = new ItemStack(Blocks.gravel);
		ItemStack furnace = new ItemStack(Blocks.furnace);
		ItemStack oreStack = new ItemStack(ore);
		ItemStack ingotStack = new ItemStack(ignot, 1);
		ItemStack toolStack = new ItemStack(tool, 1);
		ItemStack stick = new ItemStack(Items.stick, 1);
		GameRegistry.addShapelessRecipe(diamonds, dirt);
		GameRegistry.addRecipe(diamonds,"xyx","y y","xyx",'x',dirt,'y',gravel);
		GameRegistry.addSmelting(dirt, diamonds, 0.1f);
		GameRegistry.addSmelting(oreStack, ingotStack, 0.1f);
		GameRegistry.addRecipe(toolStack, "xxx"," y "," y ",'x', ingotStack, 'y', stick);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}

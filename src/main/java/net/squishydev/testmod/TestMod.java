package net.squishydev.testmod;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderSurface;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import net.squishydev.testmod.Research;

@Mod(modid="TestMod",name="Test Mod", version="1.1.0")

public class TestMod {

	public static Item tool;
	public static Item ignot;
	public static Item multiWrench;
	public static Item testBook;
	public static ResearchClass testResearchClass;
	public static ResearchMaster testResearch;
	public static boolean vanillaRecipesDisabled;
	public final static Block multiController = new MultiController(Material.rock);
	public final static Block multiPart = new MultiPart(Material.rock);
	public final static Block ore = new Ore(Material.rock);
	public final static Block crushingblock = new CrushingBlock(Material.rock);
	public final static Block crushingblock2 = new CrushingBlock2(Material.rock);
	public final static Block testFurnace2 = new TestFurnace2(false).setCreativeTab(CreativeTabs.tabMisc);
	public final static Block testFurnace2Active = new TestFurnace2(true);
	public final static CustomWorldData worldData = new CustomWorldData(WorldProvider.getProviderForDimension(0).toString());

	@Instance(value="TestMod")
	public static TestMod instance;
	
	@SidedProxy(clientSide="net.squishydev.testmod.client.ClientProxy",
			serverSide="net.squishydev.testmod.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		vanillaRecipesDisabled = config.get("VanillaRecipesDisabled", Configuration.CATEGORY_GENERAL, true).getBoolean();
		config.save();
		if (vanillaRecipesDisabled) {
			removeVanillaRecipes();
		}
		tool = new Tool();
		ignot = new Ignot();
		multiWrench = new MultiWrench();
		testBook = new TestBook();
		testResearchClass = new TestResearchClass();
		testResearch = new ResearchMaster(testResearchClass, "testResearch");
		Research.registerResearchClass(testResearchClass, "testResearchClass");
		Research.registerResearch(testResearch, "testResearch");
		GameRegistry.registerItem(tool, "Tool");
		GameRegistry.registerItem(ignot, "Ignot");
		GameRegistry.registerBlock(ore,  "Ore");
		GameRegistry.registerItem(testBook, "testBook");
		GameRegistry.registerItem(multiWrench, "MultiWrench");
		GameRegistry.registerBlock(crushingblock, "CrushingBlock");
		GameRegistry.registerBlock(crushingblock2, "CrushingBlock2");
		GameRegistry.registerBlock(multiController, "MultiController");
		GameRegistry.registerBlock(multiPart, "MultiPart");
		GameRegistry.registerBlock(testFurnace2, "TestFurnace2");
		GameRegistry.registerBlock(testFurnace2Active, "TestFurnace2Active");
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
		ItemStack dirt = new ItemStack(Blocks.dirt);
		ItemStack gravel = new ItemStack(Blocks.gravel);
		ItemStack furnace = new ItemStack(Blocks.furnace);
		ItemStack oreStack = new ItemStack(ore);
		ItemStack ingotStack = new ItemStack(ignot, 1);
		ItemStack toolStack = new ItemStack(tool, 1);
		ItemStack crushingBlockStack = new ItemStack(crushingblock, 1);
		ItemStack crushingBlockStack2 = new ItemStack(crushingblock2, 1);
		ItemStack stick = new ItemStack(Items.stick, 1);
		ItemStack cobblestoneStack = new ItemStack(Blocks.cobblestone, 1);
		//GameRegistry.addShapelessRecipe(diamonds, dirt);
		//GameRegistry.addRecipe(diamonds,"xyx","y y","xyx",'x',dirt,'y',gravel);
		//GameRegistry.addSmelting(dirt, diamonds, 0.1f);
		//GameRegistry.addSmelting(oreStack, ingotStack, 0.1f);
		//GameRegistry.addRecipe(toolStack, "xxx"," y "," y ",'x', ingotStack, 'y', stick);
		GameRegistry.addRecipe(crushingBlockStack, "x x","xxx","xxx",'x', cobblestoneStack);
		GameRegistry.addRecipe(crushingBlockStack2, "  x","xxx","xxx",'x', cobblestoneStack);
		OreGen oregen = new OreGen();
		GameRegistry.registerWorldGenerator(oregen, 0);
		GameRegistry.registerTileEntity(CrushingBlockTileEntity.class, "CrushingBlock");
		GameRegistry.registerTileEntity(CrushingBlockTileEntity2.class, "CrushingBlock2");
		GameRegistry.registerTileEntity(MultiControllerTileEntity.class, "MultiControllerTileEntity");
		GameRegistry.registerTileEntity(TileEntityTestFurnace2.class, "TileEntityTestFurnace2");
		GuiHandler2 guiHandler2 = new GuiHandler2();
		BookGuiHandler bookGuiHandler = new BookGuiHandler();
		NetworkRegistry.INSTANCE.registerGuiHandler(TestMod.instance, guiHandler2);
		NetworkRegistry.INSTANCE.registerGuiHandler(TestMod.instance, bookGuiHandler);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	public void removeVanillaRecipes() {
		Item[] rem = {Items.iron_pickaxe, Items.golden_pickaxe, Items.diamond_pickaxe};
		List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
		Iterator<IRecipe> Leash = recipes.iterator();
		while (Leash.hasNext()) {
			ItemStack is = Leash.next().getRecipeOutput();
			for (int i = 0;i<rem.length;i++) {
				if (is!=null&&is.getItem()==rem[i]) {
					Leash.remove();
				}
			}
		}
	}
}

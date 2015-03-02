package modmuss50.mmlTests;


import me.modmuss50.mods.mml.ModmussMod;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class TestMod extends ModmussMod {

	@Override
	public String modId(){
		return "test";
	};

	@Override
	public String modName(){
		return "Test";
	};

	@Override
	public String modVersion(){
		return "0";
	};

	@Override
	public String recomenedMinecraftVeriosion(){
		return "1.7.10";
	}


    @Override
    public void enable(){
        System.out.println("OMG! Im a mod loaded from a folder :)");
		Item item = new Item().setUnlocalizedName("test").setCreativeTab(CreativeTabs.tabMisc);
		GameRegistry.registerItem(item, "Test");
    }


	@Override
	public void disable(){
		System.out.println("Good bye o/");
	}

    @Override
    public void load(){
        System.out.printf("This is were the game gets loaded!");
    }
}

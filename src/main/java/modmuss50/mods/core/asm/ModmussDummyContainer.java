package modmuss50.mods.core.asm;

import com.google.common.eventbus.Subscribe;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLConstructionEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import java.util.Arrays;

public class ModmussDummyContainer extends DummyModContainer
{
    public ModmussDummyContainer()
    {
        super(new ModMetadata());
        ModMetadata meta = getMetadata();
        meta.modId = "modASM";
        meta.name = "ModmussASM";
        meta.version = "0.1";
        meta.credits = "modmuss50";
        meta.authorList = Arrays.asList(new String[]{"modmuss50"});
        meta.description = "";
        meta.url = "";
        meta.updateUrl = "";
        meta.screenshots = new String[0];
        meta.logoFile = "";
        
    }

    public boolean registerBus(com.google.common.eventbus.EventBus bus, LoadController controller)
    {
        bus.register(this);
        return true;
    }

    @Subscribe
    public void modConstruction(FMLConstructionEvent evt)
    {
    }

    @Subscribe
    public void init(FMLInitializationEvent evt)
    {
    }

    @Subscribe
    public void preInit(FMLPreInitializationEvent evt) {
        
    }

    @Subscribe
    public void postInit(FMLPostInitializationEvent evt)
    {
    }
}

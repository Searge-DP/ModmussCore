package modmuss50.mmlTests;


import modmuss50.mods.mml.ModmussMod;


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
    }


	@Override
	public void disable(){
		System.out.println("Good bye o/");
	}

    @Override
    public void load(){
        System.out.printf("This is were the game get loaded!");
    }
}

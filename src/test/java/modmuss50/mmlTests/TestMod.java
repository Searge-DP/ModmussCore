package modmuss50.mmlTests;


import modmuss50.mods.mml.ModmussMod;

//This how you will define a mml mod, This is like fml but it wont depend on fml.
//TODO Im not sure if i want to keep this. I might not
@ModmussMod(modId = "test", modName = "Test", modVersion = "0", recomenedMinecraftVeriosion = "1.7.10")
//You need to implement this so we know when the mod has been loaded
public class TestMod {

    @ModmussMod.enable
    public void enable(){
        System.out.println("JHSGAJKGJFHJghsfd;oihgjdkgsfskljsadhfkljfdhssakljfsadu;hflkjsahfkdahfdaslkjhdsflkjfhaalkfjfhlbhvjlkhvbslgf;slkjh");
    }

    @ModmussMod.disable
    public void disable(){

    }


}

package sourceteam.mods.lib.schematics;


import net.minecraft.world.World;

public class ThrededPlacer {
    World w;
    int x, y, z;
    Schematic s;

    public void placeSchematic(World world, int xc, int yc, int zc, Schematic schematic) {
       w = world;
       x = xc;
       y = yc;
       x= zc;
       s = schematic;
        Thread thread = new Thread("placeSchematic Thread") {
            public void run(){
                System.out.println("hi");
                Schematic.placeSchematic(w, x, y, z, s);
            }
        };
        thread.start();
    }
}
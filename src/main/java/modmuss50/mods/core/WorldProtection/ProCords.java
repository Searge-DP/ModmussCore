package modmuss50.mods.core.WorldProtection;

public class ProCords {

  public int x,y,z;
  public String Owner;
  public String cordsName;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public String getOwner() {
        return Owner;
    }



    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }
}

package sourceteam.mods.core.Logger;

import cpw.mods.fml.common.FMLCommonHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SourceLogger {

    public String modName;

    public String prefix = "[" + getTime() + "] [" + getSide() + "] [" + getModName() + "] ";


    public SourceLogger(String modName) {
        this.modName = modName;
    }

    public void log(String message) {
        System.out.println(prefix + message);
    }

    public static SourceLogger getLogger(String modName) {
        return new SourceLogger(modName);
    }

    public String getSide() {
        if (FMLCommonHandler.instance().getSide().isClient()) {
            return "Client";
        }

        if (FMLCommonHandler.instance().getSide().isServer()) {
            return "Server";
        }

        return "ERROR";
    }

    public String getTime() {
        return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
    }


    public String getModName() {
        return modName;
    }

}

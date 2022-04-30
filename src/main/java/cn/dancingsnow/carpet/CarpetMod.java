package cn.dancingsnow.carpet;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CarpetMod implements ModInitializer, CarpetExtension {

    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_NAME = "DancingSnow's Carpet";
    public static final String MOD_ID = "dancingsnow_carpet";
    public static String VERSION = "unknown";
    public static MinecraftServer minecraft_server;

    static {
        CarpetServer.manageExtension(new CarpetMod());
    }

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(CarpetSettings.class);
    }

    @Override
    public void onServerLoaded(MinecraftServer server) {
        minecraft_server = server;
    }

    @Override
    public void onInitialize() {
        VERSION = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(RuntimeException::new).getMetadata().getVersion().getFriendlyString();
    }

}

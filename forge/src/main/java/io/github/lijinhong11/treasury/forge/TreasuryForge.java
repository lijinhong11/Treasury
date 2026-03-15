package io.github.lijinhong11.treasury.forge;

import com.google.gson.Gson;
import io.github.lijinhong11.treasury.Treasury;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

@Mod(TreasuryForge.MODID)
public class TreasuryForge {
    public static final String MODID = "treasury";

    private static final Gson GSON = new Gson()
            .newBuilder()
            .setPrettyPrinting()
            .create();

    private static final Path CONFIG_PATH = FMLPaths.CONFIGDIR.get().resolve("treasury.json");
    private static final Logger LOGGER = Treasury.logger();

    public TreasuryForge() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        init();
        LOGGER.info("Initialized Treasury");
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        init();
        LOGGER.info("Initialized Treasury");
    }

    private static void init() {
        try {
            if (Files.notExists(CONFIG_PATH)) {
                Files.writeString(
                        CONFIG_PATH,
                        GSON.toJson(new ForgeTreasuryConfig()),
                        StandardCharsets.UTF_8
                );
            }

            ForgeTreasuryConfig config =
                    GSON.fromJson(Files.readString(CONFIG_PATH), ForgeTreasuryConfig.class);

            Treasury.setConfig(config);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load treasury config", e);
        }

        if (!Treasury.economyService().hasPrimary()) {
            Treasury.logger().info("Cannot found primary economy! Did you install any economy implementation?");
        }
    }
}

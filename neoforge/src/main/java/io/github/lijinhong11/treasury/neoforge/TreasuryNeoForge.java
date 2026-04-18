package io.github.lijinhong11.treasury.neoforge;

import com.google.gson.Gson;
import io.github.lijinhong11.treasury.Treasury;
import io.github.lijinhong11.treasury.TreasuryConfigImpl;
import io.github.lijinhong11.treasury.command.TreasuryCommand;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

@Mod(TreasuryNeoForge.MODID)
@Mod.EventBusSubscriber(modid = TreasuryNeoForge.MODID)
public class TreasuryNeoForge {
    public static final String MODID = "treasury";

    private static final Gson GSON = new Gson()
            .newBuilder()
            .setPrettyPrinting()
            .create();

    private static final Path CONFIG_PATH = FMLPaths.CONFIGDIR.get().resolve("treasury.json");
    private static final Logger LOGGER = Treasury.logger();

    public TreasuryNeoForge() {
    }

    @SubscribeEvent
    public void onStartup(FMLCommonSetupEvent event) {
        init();
        LOGGER.info("Initialized Treasury");
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(TreasuryCommand.getForRegistration());
    }

    private static void init() {
        try {
            if (Files.notExists(CONFIG_PATH)) {
                Files.writeString(
                        CONFIG_PATH,
                        GSON.toJson(new TreasuryConfigImpl()),
                        StandardCharsets.UTF_8
                );
            }

            TreasuryConfigImpl config =
                    GSON.fromJson(Files.readString(CONFIG_PATH), TreasuryConfigImpl.class);

            Treasury.setConfig(config);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load treasury config", e);
        }

        if (!Treasury.economy().hasPrimary()) {
            Treasury.logger().info("Cannot found primary economy! Did you install any economy implementation?");
        }
    }
}

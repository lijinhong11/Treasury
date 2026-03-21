package io.github.lijinhong11.treasury.fabric;

import com.google.gson.Gson;
import io.github.lijinhong11.treasury.Treasury;
import io.github.lijinhong11.treasury.TreasuryConfigImpl;
import io.github.lijinhong11.treasury.EconomyProvider;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

public class TreasuryFabric implements ModInitializer {
    private static final Gson GSON = new Gson()
            .newBuilder()
            .setPrettyPrinting()
            .create();

    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("treasury.json");
    private static final Logger LOGGER = Treasury.logger();

    @Override
    public void onInitialize() {
        init();
        LOGGER.info("Initialized Treasury");
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

        List<EconomyProvider> economyProviders = FabricLoader.getInstance().getEntrypoints("treasury-economy", EconomyProvider.class);
        if (economyProviders != null && !economyProviders.isEmpty()) {
            economyProviders.forEach(Treasury.economy()::register);
        }

        if (!Treasury.economy().hasPrimary()) {
            Treasury.logger().info("Cannot found primary economy! Did you install any economy implementation?");
        }
    }
}

package io.github.lijinhong11.treasury.fabric;

import com.google.gson.Gson;
import io.github.lijinhong11.treasury.Treasury;
import io.github.lijinhong11.treasury.TreasuryConfigImpl;
import io.github.lijinhong11.treasury.chat.ChatProvider;
import io.github.lijinhong11.treasury.economy.EconomyProvider;
import io.github.lijinhong11.treasury.permission.PermissionProvider;
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
            economyProviders.forEach(Treasury.economyService()::register);
        }

        List<PermissionProvider> permissionProviders = FabricLoader.getInstance().getEntrypoints("treasury-permission", PermissionProvider.class);
        if (permissionProviders != null && !permissionProviders.isEmpty()) {
            Treasury.permissionService().register(permissionProviders.get(0));
        }

        List<ChatProvider> chatProviders = FabricLoader.getInstance().getEntrypoints("treasury-chat", ChatProvider.class);
        if (chatProviders != null && !chatProviders.isEmpty()) {
            Treasury.chatService().register(chatProviders.get(0));
        }

        if (!Treasury.economyService().hasPrimary()) {
            Treasury.logger().info("Cannot found primary economy! Did you install any economy implementation?");
        }
    }
}

package io.github.lijinhong11.treasury.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.lijinhong11.treasury.Treasury;
import io.github.lijinhong11.treasury.economy.EconomyProvider;
import io.github.lijinhong11.treasury.economy.EconomyServiceRegistry;
import io.github.lijinhong11.treasury.points.PointsServiceRegistry;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import java.util.List;

public class TreasuryCommand {
    public static LiteralArgumentBuilder<CommandSourceStack> getForRegistration() {
        return Commands.literal("treasury-info")
                .requires(c -> c.hasPermission(2))
                .executes(c -> {
                    CommandSourceStack s = c.getSource();

                    EconomyServiceRegistry economyService = Treasury.economy();
                    PointsServiceRegistry pointsService = Treasury.points();

                    String primaryEconomy = economyService.hasPrimary() ? economyService.getPrimary().getName() : "None";
                    List<EconomyProvider> allEconomyImpls = economyService.getAll();
                    String economyImpls = String.join(",", allEconomyImpls.stream()
                            .map(EconomyProvider::getName)
                            .toArray(String[]::new));

                    String pointsImpl = pointsService.isRegistered() ? pointsService.get().getName() : "None";

                    s.sendSystemMessage(Component.literal("Treasury Info:"));
                    s.sendSystemMessage(Component.literal("================================"));
                    s.sendSystemMessage(Component.literal("Primary Economy Implementation: " + primaryEconomy));
                    s.sendSystemMessage(Component.literal("Economy Implementations (" + allEconomyImpls.size() + ") : §a" + economyImpls));
                    s.sendSystemMessage(Component.literal("================================"));
                    s.sendSystemMessage(Component.literal("Points Implementation: " + pointsImpl));
                    s.sendSystemMessage(Component.literal("================================"));

                    return 1;
                });
    }
}

package io.github.lijinhong11.treasury.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
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
        return Commands.literal("treasury")
                .then(Commands.literal("info")
                        .requires(c -> c.hasPermission(2))
                        .executes(TreasuryCommand::showInfo));
    }

    private static int showInfo(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        EconomyServiceRegistry economyService = Treasury.economy();
        PointsServiceRegistry pointsService = Treasury.points();

        String primaryEconomy = economyService.hasPrimary() ? economyService.getPrimary().getName() : "None";
        List<EconomyProvider> allEconomyImpls = economyService.getAll();
        String economyImpls = allEconomyImpls.isEmpty()
                ? "None"
                : String.join(", ", allEconomyImpls.stream().map(EconomyProvider::getName).toArray(String[]::new));
        String pointsImpl = pointsService.isRegistered() ? pointsService.get().getName() : "None";

        source.sendSystemMessage(Component.literal("Treasury Info:"));
        source.sendSystemMessage(Component.literal("================================"));
        source.sendSystemMessage(Component.literal("Primary Economy Implementation: " + primaryEconomy));
        source.sendSystemMessage(Component.literal("Economy Implementations (" + allEconomyImpls.size() + "): " + economyImpls));
        source.sendSystemMessage(Component.literal("================================"));
        source.sendSystemMessage(Component.literal("Points Implementation: " + pointsImpl));
        source.sendSystemMessage(Component.literal("================================"));
        return 1;
    }
}

package io.github.lijinhong11.treasury.command;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.github.lijinhong11.treasury.Treasury;
import io.github.lijinhong11.treasury.economy.EconomyProvider;
import io.github.lijinhong11.treasury.economy.EconomyServiceRegistry;
import io.github.lijinhong11.treasury.points.PointsProvider;
import io.github.lijinhong11.treasury.points.PointsServiceRegistry;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.GameProfileArgument;
import net.minecraft.network.chat.Component;

import java.util.Iterator;
import java.util.List;

public class TreasuryCommand {
    private static final SimpleCommandExceptionType NO_TARGETS =
            new SimpleCommandExceptionType(Component.literal("No matching player profile was found"));

    public static LiteralArgumentBuilder<CommandSourceStack> getForRegistration() {
        return Commands.literal("treasury")
                .requires(c -> c.hasPermission(2))
                .then(Commands.literal("info")
                        .executes(TreasuryCommand::showInfo))
                .then(Commands.literal("balance")
                        .then(Commands.argument("target", GameProfileArgument.gameProfile())
                                .executes(TreasuryCommand::showBalance)));
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

    private static int showBalance(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        GameProfile profile = getSingleProfile(context);
        CommandSourceStack source = context.getSource();

        source.sendSystemMessage(Component.literal("Balance for " + profile.getName() + ":"));

        if (Treasury.economy().hasPrimary()) {
            EconomyProvider economy = Treasury.economy().getPrimary();
            source.sendSystemMessage(Component.literal(
                    "Economy (" + economy.getName() + "): " + economy.format(economy.getBalance(profile.getId()))
            ));
        } else {
            source.sendSystemMessage(Component.literal("Economy: unavailable"));
        }

        if (Treasury.points().isRegistered()) {
            PointsProvider points = Treasury.points().get();
            source.sendSystemMessage(Component.literal(
                    "Points (" + points.getName() + "): " + points.format(points.getBalance(profile.getId()))
            ));
        } else {
            source.sendSystemMessage(Component.literal("Points: unavailable"));
        }

        return 1;
    }

    private static GameProfile getSingleProfile(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Iterator<GameProfile> iterator = GameProfileArgument.getGameProfiles(context, "target").iterator();
        if (!iterator.hasNext()) {
            throw NO_TARGETS.create();
        }

        return iterator.next();
    }
}

package com.example.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.argument.ColorArgumentType;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static net.minecraft.command.argument.ColorArgumentType.getColor;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public final class CommandTest {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher){
        dispatcher.register(literal("broadcast")
                .requires(source -> source.hasPermissionLevel(2)) // Must be a game master to use the command. Command will not show up in tab completion or execute to non operators or any operator that is permission level 1.
                .then(argument("color", ColorArgumentType.color())
                        .then(argument("message", greedyString())
                                .executes(ctx -> broadcast(ctx.getSource(), getColor(ctx, "color"), getString(ctx, "message")))))); // You can deal with the arguments out here and pipe them into the command.
    }

    public static int broadcast(ServerCommandSource source, Formatting formatting, String message) {
        final Text text = Text.literal(message).formatted(formatting);

        source.getServer().getPlayerManager().broadcast(text, true);
        return Command.SINGLE_SUCCESS; // Success
    }
}
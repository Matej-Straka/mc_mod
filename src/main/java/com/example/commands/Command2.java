package com.example.commands;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public final class Command2 {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher){
        dispatcher.register(literal("move")
                .requires(source -> source.hasPermissionLevel(0))
                .then(argument("message", greedyString())
                        .executes(ctx -> broadcast(ctx.getSource(), getString(ctx, "message"))))); // You can deal with the arguments out here and pipe them into the command.
    }

    public static int broadcast(ServerCommandSource source, String message) {
        try{
            int x = Integer.parseInt(message);
            source.sendMessage(Text.literal("pohnul se vpřed o " + message));
            return 1;
        } catch (NumberFormatException e) {
            source.sendMessage(Text.literal("nebylo zadáno čislo"));
            return 0;
        }

         // Successs
    }
}
package com.example.commands;

import com.example.TemplateMod;
import com.example.entity.CubeEntity;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public final class JedCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher){
        dispatcher.register(literal("jed")
                .then(literal("dopredu")
                        .then(argument("rychlost", IntegerArgumentType.integer())
                                .executes(ctx-> pohyb(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "rychlost"), 1))))
                .then(literal("doleva")
                        .then(argument("rychlost", IntegerArgumentType.integer())
                                .executes(ctx-> pohyb(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "rychlost"), 2))))
                .then(literal("doprava")
                        .then(argument("rychlost", IntegerArgumentType.integer())
                                .executes(ctx-> pohyb(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "rychlost"), 3))))
                .then(literal("dozadu")
                        .then(argument("rychlost", IntegerArgumentType.integer())
                                .executes(ctx-> pohyb(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "rychlost"), 4))))
                .then(literal("nahoru")
                        .then(argument("rychlost", IntegerArgumentType.integer())
                                .executes(ctx-> pohyb(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "rychlost"), 5))))
                .then(literal("dolu")
                        .then(argument("rychlost", IntegerArgumentType.integer())
                                 .executes(ctx-> pohyb(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "rychlost"), 6))))
                .then(literal("manualne")
                        .then(argument("x", IntegerArgumentType.integer())
                                .then(argument("y", IntegerArgumentType.integer())
                                        .then(argument("z", IntegerArgumentType.integer())
                                            .executes(ctx-> manualne(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "x"), IntegerArgumentType.getInteger(ctx, "y"), IntegerArgumentType.getInteger(ctx, "z")))))))
                );
    }
    static int pohyb(ServerCommandSource source, Integer posun, int arg){
        PlayerEntity playerEntity = source.getPlayer();
        World world = source.getWorld();
        float posun1 = posun;
        float x = 0;
        float y = 0;
        float z = 0;
        switch (arg) {
            case 1 -> x = posun1 / 10;
            case 2 -> z = -posun1 / 10;
            case 3 -> z = posun1 / 10;
            case 4 -> x = -posun1 / 10;
            case 5 -> y = posun1 / 10;
            case 6 -> y = -posun1 / 10;
        }
        CubeEntity cubeE = TemplateMod.CUBE.create(world);
        try{
            Box box = new Box(playerEntity.getX() - 100, playerEntity.getY() - 100, playerEntity.getZ() - 100, playerEntity.getX() + 100, playerEntity.getY() + 100, playerEntity.getZ() + 100);
            List<CubeEntity> ent = world.getEntitiesByClass(CubeEntity.class, box , EntityPredicates.VALID_ENTITY);
            CubeEntity cube = ent.get(0);
            cube.setNoGravity(true);
            cube.povolit(x, y, z);

        } catch (Exception e) {
            playerEntity.sendMessage(Text.literal(e.getLocalizedMessage()));
            world.spawnEntity(cubeE);
        }
        return 1;
    }

    static int manualne(ServerCommandSource source, Integer x, Integer y, Integer z){
        PlayerEntity playerEntity = source.getPlayer();
        World world = source.getWorld();
        float x1 = x;
        float y1 = y;
        float z1 = z;
        x1 = x1/100;
        y1 = y1/100;
        z1 = z1/100;
        CubeEntity cubeE = TemplateMod.CUBE.create(world);
        try{
            Box box = new Box(playerEntity.getX() - 100, playerEntity.getY() - 100, playerEntity.getZ() - 100, playerEntity.getX() + 100, playerEntity.getY() + 100, playerEntity.getZ() + 100);
            List<CubeEntity> ent = world.getEntitiesByClass(CubeEntity.class, box , EntityPredicates.VALID_ENTITY);
            CubeEntity cube = ent.get(0);
            cube.setNoGravity(true);
            cube.setHealth(10000000);
            cube.povolit(x1, y1, z1);
        } catch (Exception e) {
            playerEntity.sendMessage(Text.literal(e.getLocalizedMessage()));
            world.spawnEntity(cubeE);
        }
        return 1;
    }
}
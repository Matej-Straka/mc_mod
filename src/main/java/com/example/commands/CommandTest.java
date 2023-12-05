package com.example.commands;

import com.example.TemplateMod;
import com.example.entity.CubeEntity;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public final class CommandTest {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher){
        dispatcher.register(literal("move")
                .then(literal("dopredu")
                        .then(argument("početbloku", IntegerArgumentType.integer())
                                .executes(ctx-> pohyb(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "početbloku"), 1))))
                .then(literal("doleva")
                        .then(argument("početbloku", IntegerArgumentType.integer())
                                .executes(ctx-> pohyb(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "početbloku"), 2))))
                .then(literal("doprava")
                        .then(argument("početbloku", IntegerArgumentType.integer())
                                .executes(ctx-> pohyb(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "početbloku"), 3))))
                .then(literal("dozadu")
                        .then(argument("početbloku", IntegerArgumentType.integer())
                                .executes(ctx-> pohyb(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "početbloku"), 4))))
                );
    }
    static int pohyb(ServerCommandSource source, Integer posun, int arg){
        PlayerEntity playerEntity = source.getPlayer();
        World world = source.getWorld();
        int x = 0;
        int z = 0;
        switch (arg) {
            case 1 -> x = posun;
            case 2 -> z = -posun;
            case 3 -> z = posun;
            case 4 -> x = -posun;
        }
        CubeEntity cubeE = TemplateMod.CUBE.create(world);
        try{
            Box box = new Box(playerEntity.getX() - 100, playerEntity.getY() - 100, playerEntity.getZ() - 100, playerEntity.getX() + 100, playerEntity.getY() + 100, playerEntity.getZ() + 100);
            List<CubeEntity> ent = world.getEntitiesByClass(CubeEntity.class, box , EntityPredicates.VALID_ENTITY);
            CubeEntity cube = ent.get(0);
            cube.move(MovementType.SELF, new Vec3d(x, 0, z));

        } catch (Exception e) {
            playerEntity.sendMessage(Text.literal(e.getLocalizedMessage()));
            world.spawnEntity(cubeE);
            cubeE.setPos(playerEntity.getX(), playerEntity.getY(), playerEntity.getZ());
        }
        return 1;
    }
}
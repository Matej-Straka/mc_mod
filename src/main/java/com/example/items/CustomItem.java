package com.example.items;

import com.example.TemplateMod;
import com.example.entity.CubeEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CustomItem extends Item {

    public CustomItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        playerEntity.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 1.0F);
        playerEntity.sendMessage(Text.literal("funguje Toto?"));
        CubeEntity cubeE = TemplateMod.CUBE.create(world);
        try{
            List<CubeEntity> ent = world.getEntitiesByClass(CubeEntity.class, Box.from(Vec3d.add(Vec3i.ZERO, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ())), EntityPredicates.VALID_ENTITY);
            CubeEntity cube = ent.get(0);
            cube.move(MovementType.SELF, Vec3d.add(Vec3i.ZERO, cube.getX() + 20, cube.getY(), cube.getZ()));
        } catch (Exception e) {
            playerEntity.sendMessage(Text.literal(e.getLocalizedMessage()));
            world.spawnEntity(cubeE);
            cubeE.setPos(playerEntity.getX(), playerEntity.getY(), playerEntity.getZ());
        }

        cubeE.move(MovementType.SELF, Vec3d.add(Vec3i.ZERO, cubeE.getX() + 10, cubeE.getY(), cubeE.getZ()));
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}

package com.example.items;

import com.example.TemplateMod;
import com.example.entity.CubeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class Dozadu extends Item {

    public Dozadu(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        CubeEntity cubeE = TemplateMod.CUBE.create(world);
        try{
            Box box = new Box(playerEntity.getX() - 100, playerEntity.getY() - 100, playerEntity.getZ() - 100, playerEntity.getX() + 100, playerEntity.getY() + 100, playerEntity.getZ() + 100);
            List<CubeEntity> ent = world.getEntitiesByClass(CubeEntity.class, box , EntityPredicates.VALID_ENTITY);
            CubeEntity cube = ent.get(0);
            cube.setPos(cube.getX() - 1, cube.getY(), cube.getZ());

        } catch (Exception e) {
            playerEntity.sendMessage(Text.literal(e.getLocalizedMessage()));
            world.spawnEntity(cubeE);
            cubeE.setPos(playerEntity.getX(), playerEntity.getY(), playerEntity.getZ());
        }

        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
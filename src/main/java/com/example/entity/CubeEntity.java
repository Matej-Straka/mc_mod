package com.example.entity;

import com.example.TemplateMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/*
 * Our Cube Entity extends MobEntityWithAi, which extends MobEntity, which extends LivingEntity.
 *
 * LivingEntity has health and can deal damage.
 * MobEntity has movement controls and AI capabilities.
 * MobEntityWithAi has pathfinding favor and slightly tweaked leash behavior.
 */
public class CubeEntity extends PathAwareEntity {

    public CubeEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }
    private float x = 0;
    private float y = 0;
    private float z = 0;

    public void povolit(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public void tick() {
        super.tick();
        this.move(MovementType.SELF, new Vec3d(x, y, z));
        if(TemplateMod.getDopredu().isPressed()){
            this.move(MovementType.SELF, new Vec3d(1,0,0));
        }
        if(TemplateMod.getDozadu().isPressed()){
            this.move(MovementType.SELF, new Vec3d(-1,0,0));
        }
        if(TemplateMod.getDoleva().isPressed()){
            this.move(MovementType.SELF, new Vec3d(0,0,-1));
        }
        if(TemplateMod.getDoprava().isPressed()){
            this.move(MovementType.SELF, new Vec3d(0,0,1));
        }
        if(TemplateMod.getJump().isPressed()){
            this.jump();
        }
    }
}

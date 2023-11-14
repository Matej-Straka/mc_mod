package com.example;

import com.example.blocks.ExampleBlock;
import com.example.items.CustomItem;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TemplateMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("template-mod");
	/*
	 * Registers our Cube Entity under the ID "entitytesting:cube".
	 *
	 * The entity is registered under the SpawnGroup#CREATURE category, which is what most animals and passive/neutral mobs use.
	 * It has a hitbox size of .75x.75, or 12 "pixels" wide (3/4ths of a block).
	 */
	public static final Block EXAMPLE_BLOCK = new ExampleBlock(FabricBlockSettings.create().strength(4.0f));
	public static final Item CUSTOM_ITEM =
			Registry.register(Registries.ITEM, new Identifier("template-mod", "custom_item"),
					new CustomItem(new FabricItemSettings().maxCount(1)));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		Registry.register(Registries.BLOCK, new Identifier("template-mod", "example_block"), EXAMPLE_BLOCK);
		Registry.register(Registries.ITEM, new Identifier("template-mod", "example_block"), new BlockItem(EXAMPLE_BLOCK, new Item.Settings()));
		LOGGER.info("Hello Fabric world!");
	}
}
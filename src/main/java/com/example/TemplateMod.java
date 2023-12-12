package com.example;

import com.example.blocks.ExampleBlock;
import com.example.commands.Command2;
import com.example.commands.CommandTest;
import com.example.commands.JedCommand;
import com.example.entity.CubeEntity;
import com.example.items.Doleva;
import com.example.items.Doprava;
import com.example.items.Dopredu;
import com.example.items.Dozadu;
import com.example.renderer.CubeEntityRenderer;
import net.fabricmc.api.ModInitializer;


import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.model.CubeEntityModel;


import static net.minecraft.server.command.CommandManager.literal;

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
	public static final Item DOPREDU = new Dopredu(new FabricItemSettings().maxCount(1));

	public static final Item DOZADU = new Dozadu(new FabricItemSettings().maxCount(1));

	public static final Item DOLEVA = new Doleva(new FabricItemSettings().maxCount(1));

	public static final Item DOPRAVA = new Doprava(new FabricItemSettings().maxCount(1));

	private static KeyBinding dopredu;
	private static KeyBinding dozadu;

	private static KeyBinding doleva;
	private static KeyBinding doprava;

	public static KeyBinding getDopredu() {
		return dopredu;
	}

	public static KeyBinding getDozadu() {
		return dozadu;
	}

	public static KeyBinding getDoleva() {
		return doleva;
	}

	public static KeyBinding getDoprava() {
		return doprava;
	}

	public static KeyBinding getJump() {
		return jump;
	}

	private static KeyBinding jump;


	public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(new Identifier("template-mod", "textures/entity/cube/cube.png"), "main");
	public static final EntityType<CubeEntity> CUBE = Registry.register(
			Registries.ENTITY_TYPE,
			new Identifier("template-mod", "cube"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CubeEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
	);
	public static final Item CUBE_SPAWN_EGG = new SpawnEggItem(CUBE, 0xff0000, 0x00a2ff, new FabricItemSettings());

	private static final ItemGroup ITEMY = FabricItemGroup.builder()
			.icon(() -> new ItemStack(EXAMPLE_BLOCK))
			.displayName(Text.translatable("itemGroup.template-mod.itemy"))
			.entries((context, entries) -> {
				entries.add(DOPREDU);
				entries.add(DOZADU);
				entries.add(DOLEVA);
				entries.add(DOPRAVA);
				entries.add(EXAMPLE_BLOCK);
				entries.add(CUBE_SPAWN_EGG);
			})
			.build();
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("foo")
				.executes(context -> {
					// For versions since 1.20, please use the following, which is intended to avoid creating Text objects if no feedback is needed.
					context.getSource().sendMessage(Text.literal("Called /foo with no arguments"));

					return 1;
				})));


		dopredu = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.examplemod.dopredu", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_I, // The keycode of the key
				"category.examplemod.test" // The translation key of the keybinding's category.
		));
		dozadu = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.examplemod.dozadu", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_K, // The keycode of the key
				"category.examplemod.test" // The translation key of the keybinding's category.
		));
		doleva = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.examplemod.doleva", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_J, // The keycode of the key
				"category.examplemod.test" // The translation key of the keybinding's category.
		));
		doprava = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.examplemod.doprava", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_L, // The keycode of the key
				"category.examplemod.test" // The translation key of the keybinding's category.
		));
		jump = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.examplemod.jump", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_M, // The keycode of the key
				"category.examplemod.test" // The translation key of the keybinding's category.
		));


		Registry.register(Registries.ITEM, new Identifier("template-mod", "dopredu"), DOPREDU);
		Registry.register(Registries.ITEM, new Identifier("template-mod", "dozadu"), DOZADU);
		Registry.register(Registries.ITEM, new Identifier("template-mod", "doleva"), DOLEVA);
		Registry.register(Registries.ITEM, new Identifier("template-mod", "doprava"), DOPRAVA);
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> CommandTest.register(dispatcher));
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> JedCommand.register(dispatcher));
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> Command2.register(dispatcher));
		Registry.register(Registries.BLOCK, new Identifier("template-mod", "example_block"), EXAMPLE_BLOCK);
		Registry.register(Registries.ITEM, new Identifier("template-mod", "example_block"), new BlockItem(EXAMPLE_BLOCK, new Item.Settings()));
		LOGGER.info("Hello Fabric world!");



		EntityRendererRegistry.register(CUBE, CubeEntityRenderer::new);

		EntityModelLayerRegistry.registerModelLayer(MODEL_CUBE_LAYER, CubeEntityModel::getTexturedModelData);

		FabricDefaultAttributeRegistry.register(CUBE, CubeEntity.createMobAttributes());

		Registry.register(Registries.ITEM, new Identifier("template-mod", "cube_spawn_egg"), CUBE_SPAWN_EGG);
		Registry.register(Registries.ITEM_GROUP, new Identifier("template-mod", "itemy"), ITEMY);
	}
}
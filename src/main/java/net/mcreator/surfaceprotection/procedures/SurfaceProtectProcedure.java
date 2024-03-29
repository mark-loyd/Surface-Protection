package net.mcreator.surfaceprotection.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.BlockTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class SurfaceProtectProcedure {
	@SubscribeEvent
	public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
		if (event.getHand() != event.getEntity().getUsedItemHand())
			return;
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double block_counter = 0;
		double y_plus = 0;
		boolean exception = false;
		boolean cancel_anyway = false;
		if (new Object() {
			public boolean checkGamemode(Entity _ent) {
				if (_ent instanceof ServerPlayer _serverPlayer) {
					return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SURVIVAL;
				} else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
					return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.SURVIVAL;
				}
				return false;
			}
		}.checkGamemode(entity)) {
			block_counter = 0;
			y_plus = 1;
			exception = false;
			cancel_anyway = false;
			for (int index0 = 0; index0 < 10; index0++) {
				if (!((world.getBlockState(BlockPos.containing(x, y + y_plus, z))).getBlock() == Blocks.AIR)) {
					block_counter = block_counter + 1;
					if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BlockTags.create(new ResourceLocation("minecraft:mineable/shovel")))
							&& (world.getBlockState(BlockPos.containing(x, y + y_plus, z))).is(BlockTags.create(new ResourceLocation("minecraft:leaves")))) {
						cancel_anyway = true;
					} else if ((world.getBlockState(BlockPos.containing(x, y + y_plus, z))).is(BlockTags.create(new ResourceLocation("minecraft:planks")))) {
						cancel_anyway = true;
					}
				}
				y_plus = y_plus + 1;
			}
			if (block_counter == 0) {
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				}
			}
			if (cancel_anyway == true) {
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				}
			}
		}
	}
}

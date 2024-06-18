package dev.bagel.growing_grass.mixin;

import dev.bagel.growing_grass.GrowingGrassAddon;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BlockGrass.class)
public abstract class BlockGrassMixin extends Block {

	protected BlockGrassMixin(int par1, Material par2Material) {
		super(par1, par2Material);
	}
	@Inject(method = "updateTick", at = @At(
			value = "INVOKE", target = "Lnet/minecraft/src/BlockGrass;canGrassSpreadFromLocation(Lnet/minecraft/src/World;III)Z",
			shift = At.Shift.AFTER)
	)
	private void init(World world, int x, int y, int z, Random rand, CallbackInfo info) {
		if (rand.nextFloat() < GrowingGrassAddon.grassGrowingChance / 100 && world.isAirBlock(x, y + 1, z)) {
			if (world.getWorldTime() % GrowingGrassAddon.grassGrowingTime == 0) {
				Tuple block = GrowingGrassAddon.enabledPlants.get(rand.nextInt(GrowingGrassAddon.enabledPlants.size()));
				world.setBlockAndMetadata(x, y + 1, z, (Integer) block.getFirst(), (Integer) block.getSecond());
			}
		}
	}
}

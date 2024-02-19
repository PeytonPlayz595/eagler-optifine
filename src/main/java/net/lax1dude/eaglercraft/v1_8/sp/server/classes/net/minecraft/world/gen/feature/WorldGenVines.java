package net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.world.gen.feature;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.block.BlockVine;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.block.state.IBlockState;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.init.Blocks;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.util.BlockPos;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.util.EnumFacing;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.world.World;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.world.gen.feature.WorldGenerator;

import static net.lax1dude.eaglercraft.v1_8.sp.server.classes.ContextUtil.__checkIntegratedContextValid;

/**+
 * This portion of EaglercraftX contains deobfuscated Minecraft 1.8 source code.
 * 
 * Minecraft 1.8.8 bytecode is (c) 2015 Mojang AB. "Do not distribute!"
 * Mod Coder Pack v9.18 deobfuscation configs are (c) Copyright by the MCP Team
 * 
 * EaglercraftX 1.8 patch files (c) 2022-2024 lax1dude, ayunami2000. All Rights Reserved.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 */
public class WorldGenVines extends WorldGenerator {

	static {
		__checkIntegratedContextValid("net/minecraft/world/gen/feature/WorldGenVines");
	}

	public boolean generate(World world, EaglercraftRandom random, BlockPos blockpos) {
		for (; blockpos.getY() < 128; blockpos = blockpos.up()) {
			if (world.isAirBlock(blockpos)) {
				for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL.facings()) {
					if (Blocks.vine.canPlaceBlockOnSide(world, blockpos, enumfacing)) {
						IBlockState iblockstate = Blocks.vine.getDefaultState()
								.withProperty(BlockVine.NORTH, Boolean.valueOf(enumfacing == EnumFacing.NORTH))
								.withProperty(BlockVine.EAST, Boolean.valueOf(enumfacing == EnumFacing.EAST))
								.withProperty(BlockVine.SOUTH, Boolean.valueOf(enumfacing == EnumFacing.SOUTH))
								.withProperty(BlockVine.WEST, Boolean.valueOf(enumfacing == EnumFacing.WEST));
						world.setBlockState(blockpos, iblockstate, 2);
						break;
					}
				}
			} else {
				blockpos = blockpos.add(random.nextInt(4) - random.nextInt(4), 0,
						random.nextInt(4) - random.nextInt(4));
			}
		}

		return true;
	}
}
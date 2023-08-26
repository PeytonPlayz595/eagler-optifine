package net.minecraft.block;

import java.util.List;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**+
 * This portion of EaglercraftX contains deobfuscated Minecraft 1.8 source code.
 * 
 * Minecraft 1.8.8 bytecode is (c) 2015 Mojang AB. "Do not distribute!"
 * Mod Coder Pack v9.18 deobfuscation configs are (c) Copyright by the MCP Team
 * 
 * EaglercraftX 1.8 patch files are (c) 2022-2023 LAX1DUDE. All Rights Reserved.
 * 
 * WITH THE EXCEPTION OF PATCH FILES, MINIFIED JAVASCRIPT, AND ALL FILES
 * NORMALLY FOUND IN AN UNMODIFIED MINECRAFT RESOURCE PACK, YOU ARE NOT ALLOWED
 * TO SHARE, DISTRIBUTE, OR REPURPOSE ANY FILE USED BY OR PRODUCED BY THE
 * SOFTWARE IN THIS REPOSITORY WITHOUT PRIOR PERMISSION FROM THE PROJECT AUTHOR.
 * 
 * NOT FOR COMMERCIAL OR MALICIOUS USE
 * 
 * (please read the 'LICENSE' file this repo's root directory for more info) 
 * 
 */
public class BlockDirt extends Block {
	public static PropertyEnum<BlockDirt.DirtType> VARIANT;
	public static final PropertyBool SNOWY = PropertyBool.create("snowy");

	protected BlockDirt() {
		super(Material.ground);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockDirt.DirtType.DIRT)
				.withProperty(SNOWY, Boolean.valueOf(false)));
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public static void bootstrapStates() {
		VARIANT = PropertyEnum.<BlockDirt.DirtType>create("variant", BlockDirt.DirtType.class);
	}

	/**+
	 * Get the MapColor for this Block and the given BlockState
	 */
	public MapColor getMapColor(IBlockState iblockstate) {
		return ((BlockDirt.DirtType) iblockstate.getValue(VARIANT)).func_181066_d();
	}

	/**+
	 * Get the actual Block state of this Block at the given
	 * position. This applies properties not visible in the
	 * metadata, such as fence connections.
	 */
	public IBlockState getActualState(IBlockState iblockstate, IBlockAccess iblockaccess, BlockPos blockpos) {
		if (iblockstate.getValue(VARIANT) == BlockDirt.DirtType.PODZOL) {
			Block block = iblockaccess.getBlockState(blockpos.up()).getBlock();
			iblockstate = iblockstate.withProperty(SNOWY,
					Boolean.valueOf(block == Blocks.snow || block == Blocks.snow_layer));
		}

		return iblockstate;
	}

	/**+
	 * returns a list of blocks with the same ID, but different meta
	 * (eg: wood returns 4 blocks)
	 */
	public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> list) {
		list.add(new ItemStack(this, 1, BlockDirt.DirtType.DIRT.getMetadata()));
		list.add(new ItemStack(this, 1, BlockDirt.DirtType.COARSE_DIRT.getMetadata()));
		list.add(new ItemStack(this, 1, BlockDirt.DirtType.PODZOL.getMetadata()));
	}

	public int getDamageValue(World world, BlockPos blockpos) {
		IBlockState iblockstate = world.getBlockState(blockpos);
		return iblockstate.getBlock() != this ? 0 : ((BlockDirt.DirtType) iblockstate.getValue(VARIANT)).getMetadata();
	}

	/**+
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int i) {
		return this.getDefaultState().withProperty(VARIANT, BlockDirt.DirtType.byMetadata(i));
	}

	/**+
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState iblockstate) {
		return ((BlockDirt.DirtType) iblockstate.getValue(VARIANT)).getMetadata();
	}

	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { VARIANT, SNOWY });
	}

	/**+
	 * Gets the metadata of the item this Block can drop. This
	 * method is called when the block gets destroyed. It returns
	 * the metadata of the dropped item based on the old metadata of
	 * the block.
	 */
	public int damageDropped(IBlockState iblockstate) {
		BlockDirt.DirtType blockdirt$dirttype = (BlockDirt.DirtType) iblockstate.getValue(VARIANT);
		if (blockdirt$dirttype == BlockDirt.DirtType.PODZOL) {
			blockdirt$dirttype = BlockDirt.DirtType.DIRT;
		}

		return blockdirt$dirttype.getMetadata();
	}

	public static enum DirtType implements IStringSerializable {
		DIRT(0, "dirt", "default", MapColor.dirtColor), COARSE_DIRT(1, "coarse_dirt", "coarse", MapColor.dirtColor),
		PODZOL(2, "podzol", MapColor.obsidianColor);

		private static final BlockDirt.DirtType[] METADATA_LOOKUP = new BlockDirt.DirtType[values().length];
		private final int metadata;
		private final String name;
		private final String unlocalizedName;
		private final MapColor field_181067_h;

		private DirtType(int parInt2, String parString2, MapColor parMapColor) {
			this(parInt2, parString2, parString2, parMapColor);
		}

		private DirtType(int parInt2, String parString2, String parString3, MapColor parMapColor) {
			this.metadata = parInt2;
			this.name = parString2;
			this.unlocalizedName = parString3;
			this.field_181067_h = parMapColor;
		}

		public int getMetadata() {
			return this.metadata;
		}

		public String getUnlocalizedName() {
			return this.unlocalizedName;
		}

		public MapColor func_181066_d() {
			return this.field_181067_h;
		}

		public String toString() {
			return this.name;
		}

		public static BlockDirt.DirtType byMetadata(int metadata) {
			if (metadata < 0 || metadata >= METADATA_LOOKUP.length) {
				metadata = 0;
			}

			return METADATA_LOOKUP[metadata];
		}

		public String getName() {
			return this.name;
		}

		static {
			for (BlockDirt.DirtType blockdirt$dirttype : values()) {
				METADATA_LOOKUP[blockdirt$dirttype.getMetadata()] = blockdirt$dirttype;
			}

		}
	}
}
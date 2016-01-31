package storagecraft.block;

import java.util.List;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import storagecraft.StorageCraft;
import storagecraft.StorageCraftGUI;
import storagecraft.tile.TileStorage;

public class BlockStorage extends BlockMachine
{
	public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumStorageType.class);

	public BlockStorage()
	{
		super("storage");
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List subItems)
	{
		for (int i = 0; i <= 4; i++)
		{
			subItems.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[]
		{
			DIRECTION,
			CONNECTED,
			TYPE
		});
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(TYPE, EnumStorageType.getById(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((EnumStorageType) state.getValue(TYPE)).getId();
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		return new TileStorage();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			player.openGui(StorageCraft.INSTANCE, StorageCraftGUI.STORAGE, world, pos.getX(), pos.getY(), pos.getZ());
		}

		return true;
	}
}
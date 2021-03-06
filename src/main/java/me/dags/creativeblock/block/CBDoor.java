/*
 * The MIT License (MIT)
 *
 * Copyright (c) dags <https://www.dags.me>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package me.dags.creativeblock.block;

import me.dags.creativeblock.CreativeBlock;
import me.dags.creativeblock.Tabs;
import me.dags.creativeblock.adapter.BlockAdapter;
import me.dags.creativeblock.adapter.BlockTypeAdapter;
import me.dags.creativeblock.definition.BlockDefinition;
import me.dags.creativeblock.definition.BlockType;
import me.dags.creativeblock.item.CBItemDoor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * @author dags <dags@dags.me>
 */

public class CBDoor extends BlockDoor
{
    protected CBDoor(Material materialIn)
    {
        super(materialIn);
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? null : getItem();
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return this.getItem();
    }

    private Item getItem()
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        if (!World.doesBlockHaveSolidTopSurface(worldIn, pos.down()) && state.getValue(HALF) == EnumDoorHalf.LOWER)
        {
            return;
        }

        super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
    }

    public static BlockTypeAdapter adapter(CreativeBlock creativeBlock)
    {
        return new BlockAdapter(creativeBlock, creativeBlock.blockNames().door)
        {
            @Override
            public void createBlock(String transformedName, BlockDefinition definition)
            {
                Block block = new CBDoor(definition.material.material());
                super.setAttributes(block, transformedName, definition);

                creativeBlock.registrar().registerBlock(BlockType.DOOR, definition, block, CBItemDoor.class, true);
                block.setCreativeTab(Tabs.tabFor(definition.tabId, block));
            }
        };
    }
}

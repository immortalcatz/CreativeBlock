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
import me.dags.creativeblock.adapter.BlockAdapter;
import me.dags.creativeblock.adapter.BlockTypeAdapter;
import me.dags.creativeblock.definition.BlockDefinition;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author dags <dags@dags.me>
 */

public class CBLeaves extends BlockLeavesBase
{
    protected CBLeaves(Material materialIn)
    {
        super(materialIn, true);
    }

    public boolean isVisuallyOpaque()
    {
        return false;
    }

    public boolean isLeaves(IBlockAccess world, BlockPos pos)
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return Blocks.leaves.getBlockLayer();
    }

    public static BlockTypeAdapter adapter()
    {
        return new BlockAdapter(CreativeBlock.blockNames().leaves)
        {
            @Override
            public void createBlock(String transformedName, BlockDefinition definition)
            {
                Block block = new CBLeaves(definition.material.material());
                super.setAttributes(block, transformedName, definition);
                super.registerBlock(block, definition);
            }
        };
    }
}

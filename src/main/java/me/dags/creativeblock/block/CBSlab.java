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
import me.dags.creativeblock.item.CBItemSlab;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author dags <dags@dags.me>
 */

public abstract class CBSlab extends BlockSlab
{
    private static final PropertyBool VARIANT_PROPERTY = PropertyBool.create("variant");

    public CBSlab(Material materialIn)
    {
        super(materialIn);
        this.useNeighborBrightness = !this.isDouble();
        IBlockState blockState = this.blockState.getBaseState();
        blockState = blockState.withProperty(VARIANT_PROPERTY, false);
        if (!this.isDouble())
        {
            blockState = blockState.withProperty(HALF, EnumBlockHalf.BOTTOM);
            useNeighborBrightness = true;
        }
        setDefaultState(blockState);
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    public final IProperty getVariantProperty()
    {
        return VARIANT_PROPERTY;
    }

    @Override
    public final Object getVariant(final ItemStack itemStack)
    {
        return false;
    }

    @Override
    public String getUnlocalizedName(int meta)
    {
        return this.getUnlocalizedName();
    }

    @Override
    public final IBlockState getStateFromMeta(final int meta)
    {
        IBlockState blockState = this.getDefaultState();
        if (!this.isDouble())
        {
            EnumBlockHalf value = EnumBlockHalf.BOTTOM;
            if ((meta & 8) != 0)
            {
                value = EnumBlockHalf.TOP;
            }
            blockState = blockState.withProperty(HALF, value);
        }
        return blockState;
    }

    @Override
    public final int getMetaFromState(final IBlockState state)
    {
        if (this.isDouble())
        {
            return 0;
        }

        if ((EnumBlockHalf) state.getValue(HALF) == EnumBlockHalf.TOP)
        {
            return 8;
        }
        else
        {
            return 0;
        }
    }

    @Override
    protected final BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[]{VARIANT_PROPERTY, HALF});
    }

    public static class Half extends CBSlab
    {
        public Half(Material materialIn)
        {
            super(materialIn);
        }

        @Override
        public boolean isDouble()
        {
            return false;
        }
    }

    public static class Double extends CBSlab
    {
        public Double(Material materialIn)
        {
            super(materialIn);
        }

        @Override
        public boolean isDouble()
        {
            return true;
        }
    }

    public static BlockTypeAdapter adapter(CreativeBlock creativeBlock)
    {
        return new BlockAdapter(creativeBlock, creativeBlock.blockNames().slab)
        {
            @Override
            public void createBlock(String transformedName, BlockDefinition definition)
            {
                CBSlab halfSlab = new CBSlab.Half(definition.material.material());
                CBSlab doubleSlab = new CBSlab.Double(definition.material.material());

                super.setAttributes(halfSlab, transformedName, definition);
                super.setAttributes(doubleSlab, definition.name + "_" + creativeBlock.blockNames().double_slab, definition);
                super.registerBlock(doubleSlab, false, definition);

                creativeBlock.registrar().registerBlock(halfSlab, CBItemSlab.class, true, halfSlab, doubleSlab);
                halfSlab.setCreativeTab(Tabs.tabFor(definition.tabId, halfSlab));
            }
        };
    }
}

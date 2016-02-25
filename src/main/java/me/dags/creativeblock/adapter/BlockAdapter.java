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

package me.dags.creativeblock.adapter;

import me.dags.creativeblock.CreativeBlock;
import me.dags.creativeblock.Tabs;
import me.dags.creativeblock.definition.BlockDefinition;
import net.minecraft.block.Block;

/**
 * @author dags <dags@dags.me>
 */

public abstract class BlockAdapter implements BlockTypeAdapter
{
    private final String name;

    protected BlockAdapter(String name)
    {
        this.name = "_" + name;
    }

    @Override
    public void register(BlockDefinition definition)
    {
        String name = definition.name + this.name;
        createBlock(name, definition);
    }

    public void setAttributes(Block block, String name, BlockDefinition definition)
    {
        block
                .setRegistryName(name)
                .setUnlocalizedName(name.toLowerCase())
                .setStepSound(definition.material.sound())
                .setHardness(definition.material.hardness());
    }

    public void registerBlock(Block block, BlockDefinition definition)
    {
        registerBlock(block, true, true, definition);
    }

    public void registerBlock(Block block, boolean setTab, BlockDefinition definition)
    {
        registerBlock(block, true, setTab, definition);
    }

    public void registerBlock(Block block, boolean withModel, boolean setTab, BlockDefinition definition)
    {
        CreativeBlock.registrar().registerBlock(block, withModel);
        if (setTab)
        {
            block.setCreativeTab(Tabs.tabFor(definition.tabId, block));
        }
    }

    public abstract void createBlock(String transformedName, BlockDefinition definition);
}

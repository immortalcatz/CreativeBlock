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

package me.dags.creativeblock.proxy;

import me.dags.creativeblock.CreativeBlock;
import me.dags.creativeblock.definition.BlockDefinition;
import me.dags.creativeblock.definition.BlockType;
import me.dags.creativeblock.util.LogUtil;
import me.dags.creativeblock.util.NamingUtil;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.*;

/**
 * @author dags <dags@dags.me>
 */

public abstract class BlockRegistrar implements Proxy
{
    private final Map<String, String> register = new HashMap<>();
    protected final CreativeBlock creativeBlock;

    BlockRegistrar(CreativeBlock creativeBlock)
    {
        this.creativeBlock = creativeBlock;
    }

    @Override
    public BlockRegistrar getRegistrar()
    {
        return this;
    }

    public void registerBlock(BlockType type, BlockDefinition definition, Block block, boolean withItem)
    {
        registerBlock(type, definition, block, ItemBlock.class, withItem);
    }

    public void registerBlock(BlockType type, BlockDefinition definition, Block block, Class<? extends ItemBlock> item, boolean withItem, Object... args)
    {
        if (register.containsValue(block.getRegistryName()))
        {
            LogUtil.register(this, "Skipping duplicate of registered block {}", block.getRegistryName());
            return;
        }

        GameRegistry.registerBlock(block, item, args);
        register.put(block.getUnlocalizedName(), block.getRegistryName());
        LogUtil.register(this, "Registering block {}", block.getRegistryName());

        if (withItem)
        {
            registerBlockItem(block);
        }
    }

    public void clear()
    {
        register.clear();
    }

    public List<String> getBlockNames()
    {
        List<String> blocks = new ArrayList<>();
        for (Map.Entry<String, String> e : register.entrySet())
        {
            String key = e.getKey() + ".name";
            String name = NamingUtil.langFormat(e.getValue().substring(creativeBlock.domain().length() + 1));
            blocks.add(key + "=" + name);
        }
        Collections.sort(blocks);
        return blocks;
    }

    public abstract void registerBlockItem(Block block);
}

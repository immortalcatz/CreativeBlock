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
import me.dags.creativeblock.dynmap.DummySupport;
import me.dags.creativeblock.dynmap.IDynmapSupport;
import me.dags.creativeblock.util.LogUtil;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;

/**
 * @author dags <dags@dags.me>
 */

public class ServerProxy extends BlockRegistrar
{
    private final IDynmapSupport dynmapSupport;

    public ServerProxy(CreativeBlock creativeBlock)
    {
        super(creativeBlock);
        dynmapSupport = DummySupport.getInstance(creativeBlock);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        LogUtil.info(this, "Initialized Server proxy!");
    }

    @Override
    public void postInit(FMLInitializationEvent event)
    {
        if (!creativeBlock.config().enableDynmapSupport)
        {
            return;
        }
        dynmapSupport.publish();
    }

    @Override
    public void serverInit(FMLServerStartedEvent event)
    {
        if (!creativeBlock.config().enableDynmapSupport)
        {
            return;
        }
        dynmapSupport.copyTextures();
    }

    @Override
    public IDynmapSupport dynmapSupport()
    {
        return dynmapSupport;
    }

    @Override
    public void registerBlock(BlockType type, BlockDefinition definition, Block block, Class<? extends ItemBlock> item, boolean withItem, Object... args)
    {
        super.registerBlock(type, definition, block, item, withItem, args);
        if (!creativeBlock.config().enableDynmapSupport)
        {
            return;
        }
        dynmapSupport.registerBlock(block, type, definition.textures);
    }

    @Override
    public void registerBlockItem(Block block)
    {}
}

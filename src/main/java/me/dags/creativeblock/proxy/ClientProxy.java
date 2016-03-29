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
import me.dags.creativeblock.blockpack.LangPack;
import me.dags.creativeblock.dynmap.DummySupport;
import me.dags.creativeblock.dynmap.IDynmapSupport;
import me.dags.creativeblock.util.LogUtil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;

/**
 * @author dags <dags@dags.me>
 */

public class ClientProxy extends BlockRegistrar
{
    final IDynmapSupport dynmapSupport = new DummySupport();

    public ClientProxy(CreativeBlock creativeBlock)
    {
        super(creativeBlock);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        LogUtil.info(this, "Initialized Client proxy!");
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        LangPack langPack = new LangPack(creativeBlock);
        FMLCommonHandler.instance().addModToResourcePack(langPack);
    }

    @Override
    public void serverInit(FMLServerStartedEvent event)
    {}

    @Override
    public IDynmapSupport dynmapSupport()
    {
        return dynmapSupport;
    }

    @Override
    public void registerBlockItem(Block block)
    {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        ModelResourceLocation location = new ModelResourceLocation(block.getRegistryName(), "inventory");
        renderItem.getItemModelMesher().register(Item.getItemFromBlock(block), 0, location);
    }
}

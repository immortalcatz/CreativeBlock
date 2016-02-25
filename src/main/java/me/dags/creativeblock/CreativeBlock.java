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

package me.dags.creativeblock;

import me.dags.creativeblock.adapter.BlockNames;
import me.dags.creativeblock.blockpack.BlockPack;
import me.dags.creativeblock.definition.BlockDefinition;
import me.dags.creativeblock.proxy.*;
import me.dags.creativeblock.util.FileUtil;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.io.File;
import java.util.List;

/**
 * @author dags <dags@dags.me>
 */

public final class CreativeBlock
{
    public static final String ID = "creativeblock";
    private static final CreativeBlock INSTANCE = new CreativeBlock();

    private String domain = ID;
    private File blocksDir = Minecraft.getMinecraft().mcDataDir;
    private Config config = new Config();
    private Proxy proxy = new EmptyProxy();
    private BlockNames blockNames = new BlockNames();

    private CreativeBlock()
    {}

    public static CreativeBlock init(String modId, File configDir, Side side)
    {
        INSTANCE.domain = modId;
        INSTANCE.config = Config.load(configDir, ID);
        INSTANCE.blocksDir = FileUtil.getDir(configDir.getParentFile(), "blockpacks");
        INSTANCE.proxy = side == Side.CLIENT ? new ClientProxy() : new ServerProxy();
        return INSTANCE;
    }

    public void onPreInit(FMLPreInitializationEvent event)
    {
        proxy().preInit(event);
    }

    public void onPostInit(FMLPostInitializationEvent event)
    {
        List<BlockPack> blockPacks = BlockPack.getBlockPacks(blockpackDir());
        for (BlockPack pack : blockPacks)
        {
            FMLCommonHandler.instance().addModToResourcePack(pack);
            List<BlockDefinition> definitions = pack.getDefinitions();
            for (BlockDefinition definition : definitions)
            {
                definition.register();
            }
        }
        proxy().postInit(event);
    }

    public static String domain()
    {
        return INSTANCE.domain;
    }

    public static BlockNames blockNames()
    {
        return INSTANCE.blockNames;
    }

    public static File blockpackDir()
    {
        return INSTANCE.blocksDir;
    }

    public static Config config()
    {
        return INSTANCE.config;
    }

    public static Proxy proxy()
    {
        return INSTANCE.proxy;
    }

    public static BlockRegistrar registrar()
    {
        return proxy().getRegistrar();
    }
}

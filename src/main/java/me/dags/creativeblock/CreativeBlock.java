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

import me.dags.creativeblock.app.util.MCJsonDataProvider;
import me.dags.creativeblock.adapter.BlockNames;
import me.dags.creativeblock.adapter.TypeRegistry;
import me.dags.creativeblock.app.data.JsonData;
import me.dags.creativeblock.block.*;
import me.dags.creativeblock.blockpack.BlockPack;
import me.dags.creativeblock.definition.BlockDefinition;
import me.dags.creativeblock.definition.BlockType;
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

    private CreativeBlock()
    {}

    public static CreativeBlock withBlockNames(BlockNames names)
    {
        BlockNames.setBlockNames(names);
        return INSTANCE;
    }

    private void registerTypes()
    {
        TypeRegistry.register(BlockType.ANVIL, CBAnvil.adapter());
        TypeRegistry.register(BlockType.BLOCK, CBBlock.adapter());
        TypeRegistry.register(BlockType.BUTTON, CBButton.adapter());
        TypeRegistry.register(BlockType.CARPET, CBCarpet.adapter());
        TypeRegistry.register(BlockType.CAULDRON, CBCauldron.adapter());
        TypeRegistry.register(BlockType.CHAIR, CBChair.adapter());
        TypeRegistry.register(BlockType.CROPS, CBCrops.adapter());
        TypeRegistry.register(BlockType.DAY_SENSOR, CBDaySensor.adapter());
        TypeRegistry.register(BlockType.DOOR, CBDoor.adapter());
        TypeRegistry.register(BlockType.DOUBLE_PLANT, CBDoublePlant.adapter());
        TypeRegistry.register(BlockType.FENCE, CBFence.adapter());
        TypeRegistry.register(BlockType.FENCE_GATE, CBFenceGate.adapter());
        TypeRegistry.register(BlockType.FURNACE, CBFurnace.adapter());
        TypeRegistry.register(BlockType.GHOST_BLOCK, CBGhostBlock.adapter());
        TypeRegistry.register(BlockType.GHOST_PANE, CBGhostPane.adapter());
        TypeRegistry.register(BlockType.GLASS, CBGlassBlock.adapter());
        TypeRegistry.register(BlockType.GLASS_STAINED, CBGlassBlockStained.adapter());
        TypeRegistry.register(BlockType.HALF_DOOR, CBHalfDoor.adapter());
        TypeRegistry.register(BlockType.ICE, CBIce.adapter());
        TypeRegistry.register(BlockType.LADDER, CBLadder.adapter());
        TypeRegistry.register(BlockType.LEAVES, CBLeaves.adapter());
        TypeRegistry.register(BlockType.LIGHT_WEB, CBLightWeb.adapter());
        TypeRegistry.register(BlockType.LOG, CBLog.adapter());
        TypeRegistry.register(BlockType.PANE, CBPane.adapter());
        TypeRegistry.register(BlockType.PANE_STAINED, CBPaneStained.adapter());
        TypeRegistry.register(BlockType.PILLAR, CBDirectional.adapter());
        TypeRegistry.register(BlockType.PISTON_EXTENSION, CBPistonExtension.adapter());
        TypeRegistry.register(BlockType.PLANT, CBPlant.adapter());
        TypeRegistry.register(BlockType.PLATE, CBPlate.adapter());
        TypeRegistry.register(BlockType.POT, CBPot.adapter());
        TypeRegistry.register(BlockType.SHORT_CHAIR, CBShortChair.adapter());
        TypeRegistry.register(BlockType.SLAB, CBSlab.adapter());
        TypeRegistry.register(BlockType.SLIM_SLAB, CBPaving.adapter());
        TypeRegistry.register(BlockType.STAIRS, CBStair.adapter());
        TypeRegistry.register(BlockType.TORCH, CBTorch.adapter());
        TypeRegistry.register(BlockType.TRAP_DOOR, CBTrapdoor.adapter());
        TypeRegistry.register(BlockType.TRUNK, CBTrunk.adapter());
        TypeRegistry.register(BlockType.WALL, CBWall.adapter());
        TypeRegistry.register(BlockType.WEB, CBWeb.adapter());
    }

    public static CreativeBlock init(String modId, File configDir, Side side)
    {
        JsonData.setProvider(new MCJsonDataProvider());
        INSTANCE.domain = modId;
        INSTANCE.config = Config.load(configDir, ID);
        INSTANCE.blocksDir = FileUtil.getDir(configDir.getParentFile(), "blockpacks");
        INSTANCE.proxy = side == Side.CLIENT ? new ClientProxy() : new ServerProxy();
        INSTANCE.registerTypes();
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
        return BlockNames.get();
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

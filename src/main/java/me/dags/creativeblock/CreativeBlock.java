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
import me.dags.creativeblock.adapter.BlockTypeAdapter;
import me.dags.creativeblock.app.data.JsonData;
import me.dags.creativeblock.block.*;
import me.dags.creativeblock.blockpack.BlockPack;
import me.dags.creativeblock.definition.BlockDefinition;
import me.dags.creativeblock.definition.BlockType;
import me.dags.creativeblock.proxy.BlockRegistrar;
import me.dags.creativeblock.proxy.ClientProxy;
import me.dags.creativeblock.proxy.Proxy;
import me.dags.creativeblock.proxy.ServerProxy;
import me.dags.creativeblock.util.FileUtil;
import me.dags.creativeblock.util.LogUtil;
import me.dags.creativeblock.util.dataprovider.ResourceDataProvider;
import me.dags.creativeblock.util.logging.Logger4j;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.io.File;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * @author dags <dags@dags.me>
 */

public final class CreativeBlock
{
    static final String ID = "creativeblock";

    static
    {
        LogUtil.setLogger(new Logger4j());
    }

    private final Map<BlockType, BlockTypeAdapter> typeMap = new EnumMap<>(BlockType.class);
    private final String domain;
    private final File blocksDir;
    private final Proxy proxy;
    private BlockNames blockNames = new BlockNames();

    private CreativeBlock(String domain, File configDir, Side side)
    {
        LogUtil.setOptions(Config.load(configDir, ID));
        JsonData.setProvider(new ResourceDataProvider());
        this.domain = domain;
        this.blocksDir = FileUtil.getDir(configDir.getParentFile(), "blockpacks");
        this.proxy = side == Side.CLIENT ? new ClientProxy(this) : new ServerProxy(this);
        registerTypes();
    }

    public CreativeBlock setBlockNames(BlockNames names)
    {
        this.blockNames = names;
        return this;
    }

    public void onPostInit(FMLPostInitializationEvent event)
    {
        List<BlockPack> blockPacks = BlockPack.getBlockPacks(this, blockpackDir());
        for (BlockPack pack : blockPacks)
        {
            FMLCommonHandler.instance().addModToResourcePack(pack);
            List<BlockDefinition> definitions = pack.getDefinitions();
            definitions.forEach(d -> d.register(this));
        }
        proxy.postInit(event);
    }

    public String domain()
    {
        return domain;
    }

    public BlockNames blockNames()
    {
        return blockNames;
    }

    public File blockpackDir()
    {
        return blocksDir;
    }

    public BlockRegistrar registrar()
    {
        return proxy.getRegistrar();
    }

    public BlockTypeAdapter getAdapter(BlockType type)
    {
        return typeMap.get(type);
    }

    private void registerTypes()
    {
        typeMap.put(BlockType.ANVIL, CBAnvil.adapter(this));
        typeMap.put(BlockType.BLOCK, CBBlock.adapter(this));
        typeMap.put(BlockType.BUTTON, CBButton.adapter(this));
        typeMap.put(BlockType.CARPET, CBCarpet.adapter(this));
        typeMap.put(BlockType.CAULDRON, CBCauldron.adapter(this));
        typeMap.put(BlockType.CHAIR, CBChair.adapter(this));
        typeMap.put(BlockType.CROPS, CBCrops.adapter(this));
        typeMap.put(BlockType.DAY_SENSOR, CBDaySensor.adapter(this));
        typeMap.put(BlockType.DOOR, CBDoor.adapter(this));
        typeMap.put(BlockType.DOUBLE_PLANT, CBDoublePlant.adapter(this));
        typeMap.put(BlockType.FENCE, CBFence.adapter(this));
        typeMap.put(BlockType.FENCE_GATE, CBFenceGate.adapter(this));
        typeMap.put(BlockType.FURNACE, CBFurnace.adapter(this));
        typeMap.put(BlockType.GHOST_BLOCK, CBGhostBlock.adapter(this));
        typeMap.put(BlockType.GHOST_PANE, CBGhostPane.adapter(this));
        typeMap.put(BlockType.GLASS, CBGlassBlock.adapter(this));
        typeMap.put(BlockType.GLASS_STAINED, CBGlassBlockStained.adapter(this));
        typeMap.put(BlockType.HALF_DOOR, CBHalfDoor.adapter(this));
        typeMap.put(BlockType.ICE, CBIce.adapter(this));
        typeMap.put(BlockType.LADDER, CBLadder.adapter(this));
        typeMap.put(BlockType.LEAVES, CBLeaves.adapter(this));
        typeMap.put(BlockType.LIGHT_WEB, CBLightWeb.adapter(this));
        typeMap.put(BlockType.LOG, CBLog.adapter(this));
        typeMap.put(BlockType.PANE, CBPane.adapter(this));
        typeMap.put(BlockType.PANE_STAINED, CBPaneStained.adapter(this));
        typeMap.put(BlockType.PILLAR, CBDirectional.adapter(this));
        typeMap.put(BlockType.PISTON_EXTENSION, CBPistonExtension.adapter(this));
        typeMap.put(BlockType.PLANT, CBPlant.adapter(this));
        typeMap.put(BlockType.PLATE, CBPlate.adapter(this));
        typeMap.put(BlockType.POT, CBPot.adapter(this));
        typeMap.put(BlockType.SHORT_CHAIR, CBShortChair.adapter(this));
        typeMap.put(BlockType.SLAB, CBSlab.adapter(this));
        typeMap.put(BlockType.SLIM_SLAB, CBPaving.adapter(this));
        typeMap.put(BlockType.STAIRS, CBStair.adapter(this));
        typeMap.put(BlockType.TORCH, CBTorch.adapter(this));
        typeMap.put(BlockType.TRAP_DOOR, CBTrapdoor.adapter(this));
        typeMap.put(BlockType.TRUNK, CBTrunk.adapter(this));
        typeMap.put(BlockType.WALL, CBWall.adapter(this));
        typeMap.put(BlockType.WEB, CBWeb.adapter(this));
    }

    public static CreativeBlock newInstance(String domain, FMLPreInitializationEvent event)
    {
        CreativeBlock creativeBlock = new CreativeBlock(domain, event.getModConfigurationDirectory(), event.getSide());
        creativeBlock.proxy.preInit(event);
        return creativeBlock;
    }
}

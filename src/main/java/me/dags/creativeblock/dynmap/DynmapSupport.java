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

package me.dags.creativeblock.dynmap;

import me.dags.creativeblock.CreativeBlock;
import me.dags.creativeblock.blockpack.BlockPack;
import me.dags.creativeblock.definition.BlockTextures;
import me.dags.creativeblock.definition.BlockType;
import me.dags.creativeblock.dynmap.models.*;
import me.dags.creativeblock.util.FileUtil;
import me.dags.creativeblock.util.LogUtil;
import net.minecraft.block.Block;
import org.dynmap.modsupport.ModSupportAPI;
import org.dynmap.modsupport.ModTextureDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * @author dags <dags@dags.me>
 */

public class DynmapSupport implements IDynmapSupport
{
    private static final Map<BlockType, ModelMaker> shapes = new EnumMap<>(BlockType.class);

    static
    {
        shapes.put(BlockType.BLOCK, new BlockNormal());
        shapes.put(BlockType.CROPS, new BlockPlant());
        shapes.put(BlockType.DOOR, new BlockDoor());
        shapes.put(BlockType.FENCE, new BlockFence());
        shapes.put(BlockType.LEAVES, new BlockNormalTransparent());
        shapes.put(BlockType.PANE, new BlockPane());
        shapes.put(BlockType.SLAB, new BlockSlab());
        shapes.put(BlockType.SLIM_SLAB, new BlockSlabThin());
        shapes.put(BlockType.STAIRS, new BlockStairs());
        shapes.put(BlockType.TRAP_DOOR, new BlockTrapdoor());
        shapes.put(BlockType.WALL, new BlockWall());

        shapes.put(BlockType.CARPET, shapes.get(BlockType.SLIM_SLAB));
        shapes.put(BlockType.CAULDRON, shapes.get(BlockType.BLOCK));
        shapes.put(BlockType.CHAIR, shapes.get(BlockType.SLAB));
        shapes.put(BlockType.DAY_SENSOR, shapes.get(BlockType.SLAB));
        shapes.put(BlockType.DOUBLE_PLANT, shapes.get(BlockType.CROPS));
        shapes.put(BlockType.FURNACE, shapes.get(BlockType.BLOCK));
        shapes.put(BlockType.GLASS, shapes.get(BlockType.LEAVES));
        shapes.put(BlockType.GLASS_STAINED, shapes.get(BlockType.GLASS));
        shapes.put(BlockType.ICE, shapes.get(BlockType.GLASS));
        shapes.put(BlockType.LOG, shapes.get(BlockType.BLOCK));

        shapes.put(BlockType.PILLAR, shapes.get(BlockType.BLOCK));
        shapes.put(BlockType.PLANT, shapes.get(BlockType.CROPS));
        shapes.put(BlockType.SHORT_CHAIR, shapes.get(BlockType.SLAB));
    }

    private final ModTextureDefinition modTextureDefinition;
    private final CreativeBlock creativeBlock;
    private final File textureDir;

    public DynmapSupport(CreativeBlock creativeBlock)
    {
        File root = creativeBlock.blockpackDir().getParentFile();
        this.creativeBlock = creativeBlock;
        this.modTextureDefinition = ModSupportAPI.getAPI().getModTextureDefinition(creativeBlock.domain(), creativeBlock.version());
        this.modTextureDefinition.setTexturePath("assets/" + creativeBlock.domain() + "/textures/blocks/");
        this.textureDir = FileUtil.getDir(root, "dynmap", "texturepacks", "standard", "assets", creativeBlock.domain(), "textures", "blocks");
    }

    @Override
    public void copyTextures()
    {
        textureDir.mkdirs();

        List<BlockPack> packs = BlockPack.getBlockPacks(creativeBlock, creativeBlock.blockpackDir());
        try
        {
            for (BlockPack pack : packs)
            {
                pack.copyServerTextures();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void copyTexture(String name, InputStream resourceStream) throws IOException
    {
        Path file = textureDir.toPath().resolve(name);
        if (Files.exists(file))
        {
            return;
        }
        LogUtil.io(this, "Copying {} to {}", name, file);
        Files.copy(resourceStream, file);
    }

    @Override
    public void registerBlock(Block block, BlockType type, BlockTextures textures)
    {
        if (shapes.containsKey(type) && textures.valid())
        {
            LogUtil.info(this, "Registering dynmap model for {}", block.getRegistryName());
            shapes.get(type).registerBlock(modTextureDefinition, block, textures);
        }
    }

    @Override
    public void publish()
    {
        modTextureDefinition.publishDefinition();
        modTextureDefinition.getModelDefinition().publishDefinition();
    }

    public static IDynmapSupport newInstance(CreativeBlock creativeBlock)
    {
        return new DynmapSupport(creativeBlock);
    }
}

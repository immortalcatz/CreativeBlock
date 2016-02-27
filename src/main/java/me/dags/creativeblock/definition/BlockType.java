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

package me.dags.creativeblock.definition;

import me.dags.creativeblock.block.*;
import me.dags.creativeblock.adapter.BlockTypeAdapter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author dags <dags@dags.me>
 */

public enum BlockType
{
    BLOCK(CBBlock.adapter()),
    DOOR(CBDoor.adapter()),
    DOUBLE_PLANT(CBDoublePlant.adapter()),
    FENCE(CBFence.adapter()),
    FENCE_GATE(CBFenceGate.adapter()),
    GHOST_BLOCK(CBGhostBlock.adapter()),
    GHOST_PANE(CBGhostPane.adapter()),
    GLASS(CBGlassBlock.adapter()),
    GLASS_STAINED(CBGlassBlockStained.adapter()),
    HALF_DOOR(CBHalfDoor.adapter()),
    ICE(CBIce.adapter()),
    LEAVES(CBLeaves.adapter()),
    LADDER(CBLadder.adapter()),
    LIGHT_WEB(CBLightWeb.adapter(), "_0", "_1", "_2"),
    LOG(CBLog.adapter()),
    PANE(CBPane.adapter()),
    PANE_STAINED(CBPaneStained.adapter()),
    POT(CBPot.adapter()),
    PILLAR(CBDirectional.adapter()),
    SLAB(CBSlab.adapter()),
    SLIM_SLAB(CBPaving.adapter()),
    STAIRS(CBStair.adapter()),
    TORCH(CBTorch.adapter(), "_0", "_1", "_2"),
    TRAP_DOOR(CBTrapdoor.adapter()),
    TRUNK(CBTrunk.adapter()),
    WALL(CBWall.adapter()),
    WEB(CBWeb.adapter()),
    ;

    private final BlockTypeAdapter adapter;
    private final String[] suffixes;

    private BlockType(BlockTypeAdapter adapter)
    {
        this.adapter = adapter;
        this.suffixes = new String[]{""};
    }

    private BlockType(BlockTypeAdapter adapter, String... suffixes)
    {
        this.adapter = adapter;
        this.suffixes = suffixes;
    }

    public String[] suffixes()
    {
        return suffixes;
    }

    public void register(BlockDefinition definition)
    {
        adapter.register(definition);
    }

    private static final Set<String> types = new HashSet<String>();

    static
    {
        for (BlockType type : BlockType.values()) types.add(type.name());
    }

    public static Optional<BlockType> from(String name)
    {
        name = name.toUpperCase().replace("-", "_");
        if (types.contains(name)) return Optional.of(BlockType.valueOf(name));
        return Optional.empty();
    }
}

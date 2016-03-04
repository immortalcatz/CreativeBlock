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

import me.dags.creativeblock.adapter.TypeRegistry;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author dags <dags@dags.me>
 */

public enum BlockType
{
    ANVIL,
    BLOCK,
    BUTTON,
    CARPET,
    CAULDRON,
    CHAIR,
    CROPS,
    DAY_SENSOR,
    DOOR,
    DOUBLE_PLANT,
    FENCE,
    FENCE_GATE,
    FURNACE("", "_on"),
    GHOST_BLOCK,
    GHOST_PANE,
    GLASS,
    GLASS_STAINED,
    HALF_DOOR,
    ICE,
    LEAVES,
    LADDER,
    LIGHT_WEB("_0", "_1", "_2"),
    LOG,
    PANE,
    PANE_STAINED,
    PISTON_EXTENSION,
    PLANT,
    PLATE,
    POT,
    PILLAR,
    SHORT_CHAIR,
    SLAB,
    SLIM_SLAB,
    STAIRS,
    TORCH("_0", "_1", "_2"),
    TRAP_DOOR,
    TRUNK,
    WALL,
    WEB,
    ;

    private final String[] suffixes;

    BlockType()
    {
        this.suffixes = new String[]{""};
    }

    BlockType(String... suffixes)
    {
        this.suffixes = suffixes;
    }

    public String[] suffixes()
    {
        return suffixes;
    }

    public void register(BlockDefinition definition)
    {
        TypeRegistry.get(this).register(definition);
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

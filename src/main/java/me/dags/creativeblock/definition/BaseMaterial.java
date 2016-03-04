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

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author dags <dags@dags.me>
 */

public enum BaseMaterial
{
    CROP(Blocks.wheat, 0.0F),
    DIRT(Blocks.dirt, 0.5F),
    GLASS(Blocks.glass, 0.3F),
    GRASS(Blocks.grass, 0.6F),
    GRAVEL(Blocks.gravel,0.6F),
    SAND(Blocks.sand, 0.5F),
    SNOW(Blocks.snow, 0.2F),
    SOUL_SAND(Blocks.soul_sand, 0.5F),
    SPONGE(Blocks.sponge, 0.6F),
    STONE(Blocks.stone, 1.5F),
    WOOD(Blocks.planks, 2.0F),
    WOOL(Blocks.wool, 0.8F),
    ;

    private final Block base;
    private final float hardness;

    BaseMaterial(Block base, float hardness)
    {
        this.base = base;
        this.hardness = hardness;
    }

    public Block base()
    {
        return base;
    }

    public Material material()
    {
        return base.getMaterial();
    }

    public Block.SoundType sound()
    {
        return base.stepSound;
    }

    public float hardness()
    {
        return hardness;
    }

    private static final Set<String> types = new HashSet<String>();

    private static Set<String> registered()
    {
        if (types.isEmpty())
        {
            for (BaseMaterial material : values()) types.add(material.name());
        }
        return types;
    }

    public static Optional<BaseMaterial> from(String name)
    {
        name = update(name);
        if (registered().contains(name))
        {
            return Optional.of(BaseMaterial.valueOf(name));
        }
        return Optional.empty();
    }

    public static boolean has(String name)
    {
        name = update(name);
        return registered().contains(name);
    }

    private static String update(String s)
    {
        if (s.equalsIgnoreCase("soulsand"))
        {
            return "SOUL_SAND";
        }
        return s.toUpperCase();
    }
}

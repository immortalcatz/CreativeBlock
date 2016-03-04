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

package me.dags.creativeblock.adapter;

/**
 * @author dags <dags@dags.me>
 */

public class BlockNames
{
    private static BlockNames blockNames = new BlockNames();

    public static BlockNames get()
    {
        return BlockNames.blockNames;
    }

    public static void setBlockNames(BlockNames blockNames)
    {
        BlockNames.blockNames = blockNames;
    }

    public String anvil = "anvil";
    public String block = "block";
    public String button = "button";
    public String carpet = "carpet";
    public String cauldron = "cauldron";
    public String chair = "chair";
    public String crops = "crops";
    public String directional = "directional";
    public String door = "door";
    public String double_plant = "double_plant";
    public String double_slab = "double_slab";
    public String furnace = "furnace";
    public String fence = "fence";
    public String fence_gate = "fence_gate";
    public String ghost_block = "ghost_block";
    public String ghost_pane = "ghost_pane";
    public String glass = "glass";
    public String glass_stained = "glass_stained";
    public String half_door = "half_door";
    public String ice = "ice";
    public String ladder = "ladder";
    public String leaves = "leaves";
    public String light_web = "light_web";
    public String log = "log";
    public String pane = "pane";
    public String pane_stained = "pane_stained";
    public String paving = "paving";
    public String piston_extension = "piston_extension";
    public String plant = "plant";
    public String plate = "plate";
    public String pot = "pot";
    public String sensor = "sensor";
    public String short_chair = "short_chair";
    public String slab = "slab";
    public String stairs = "stairs";
    public String torch = "torch";
    public String trapdoor = "trap_door";
    public String trunk = "trunk";
    public String wall = "wall";
    public String web = "web";
}

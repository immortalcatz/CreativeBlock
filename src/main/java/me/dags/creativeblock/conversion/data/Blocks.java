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

package me.dags.creativeblock.conversion.data;

import me.dags.creativeblock.CreativeBlock;

/**
 * @author dags <dags@dags.me>
 */

public class Blocks
{
    private static final String TEMPLATES = "creativeblock:templates/blockstates/";

    public static final JsonData block = JsonData.of(TEMPLATES + "block.json", "_" + CreativeBlock.blockNames().block);
    public static final JsonData directional = JsonData.of(TEMPLATES + "directional.json", "_" + CreativeBlock.blockNames().directional);
    public static final JsonData door = JsonData.of(TEMPLATES + "door.json", "_" + CreativeBlock.blockNames().door);
    public static final JsonData fence = JsonData.of(TEMPLATES + "fence.json", "_" + CreativeBlock.blockNames().fence);
    public static final JsonData fence_gate = JsonData.of(TEMPLATES + "fence_gate.json", "_" + CreativeBlock.blockNames().fence_gate);
    public static final JsonData ghost_block = JsonData.of(TEMPLATES + "ghost_block.json", "_" + CreativeBlock.blockNames().ghost_block);
    public static final JsonData ghost_pane = JsonData.of(TEMPLATES + "ghost_pane.json", "_" + CreativeBlock.blockNames().ghost_pane);
    public static final JsonData glass = JsonData.of(TEMPLATES + "glass.json", "_" + CreativeBlock.blockNames().glass);
    public static final JsonData glass_stained = JsonData.of(TEMPLATES + "glass_stained.json", "_" + CreativeBlock.blockNames().glass_stained);
    public static final JsonData half_door = JsonData.of(TEMPLATES + "half_door.json", "_" + CreativeBlock.blockNames().half_door);
    public static final JsonData leaves = JsonData.of(TEMPLATES + "leaves.json", "_" + CreativeBlock.blockNames().leaves);
    public static final JsonData light_web = JsonData.of(TEMPLATES + "light_web.json", "_" + CreativeBlock.blockNames().light_web);
    public static final JsonData log = JsonData.of(TEMPLATES + "directional.json", "_" + CreativeBlock.blockNames().log);
    public static final JsonData pane = JsonData.of(TEMPLATES + "pane.json", "_" + CreativeBlock.blockNames().pane);
    public static final JsonData pane_stained = JsonData.of(TEMPLATES + "pane_stained.json", "_" + CreativeBlock.blockNames().pane_stained);
    public static final JsonData paving = JsonData.of(TEMPLATES + "paving.json", "_" + CreativeBlock.blockNames().paving);
    public static final JsonData slab_double = JsonData.of(TEMPLATES + "slab_double.json", "_" + CreativeBlock.blockNames().double_slab);
    public static final JsonData slab_half = JsonData.of(TEMPLATES + "slab.json", "_" + CreativeBlock.blockNames().slab);
    public static final JsonData stairs = JsonData.of(TEMPLATES + "stairs.json", "_" + CreativeBlock.blockNames().stairs);
    public static final JsonData trapdoor = JsonData.of(TEMPLATES + "trapdoor.json", "_" + CreativeBlock.blockNames().trapdoor);
    public static final JsonData trunk = JsonData.of(TEMPLATES + "trunk.json", "_" + CreativeBlock.blockNames().trunk);
    public static final JsonData wall = JsonData.of(TEMPLATES + "wall.json", "_" + CreativeBlock.blockNames().wall);
    public static final JsonData web = JsonData.of(TEMPLATES + "web.json", "_" + CreativeBlock.blockNames().web);
}

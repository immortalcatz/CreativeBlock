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

package me.dags.creativeblock.app.conversion;

import me.dags.creativeblock.app.data.Models;
import me.dags.creativeblock.definition.BlockType;
import me.dags.creativeblock.app.data.Blocks;

import java.util.*;

/**
 * @author dags <dags@dags.me>
 */

public class Conversions
{
    private static final Map<BlockType, List<Conversion>> conversions = new EnumMap<BlockType, List<Conversion>>(BlockType.class);
    private static final List<Conversion> EMPTY = new ArrayList<Conversion>();

    static
    {
        add(BlockType.ANVIL, Conversion.of(Blocks.anvil, Models.anvil));
        add(BlockType.BLOCK, Conversion.of(Blocks.block, Models.block));
        add(BlockType.BUTTON, Conversion.of(Blocks.button, Models.button));
        add(BlockType.CARPET, Conversion.of(Blocks.carpet, Models.carpet));
        add(BlockType.CAULDRON, Conversion.of(Blocks.cauldron, Models.cauldron_empty, Models.cauldron_level1, Models.cauldron_level2, Models.cauldron_level3));
        add(BlockType.CHAIR, Conversion.of(Blocks.chair, Models.chair));
        add(BlockType.CROPS, Conversion.of(Blocks.crops, Models.crops));
        add(BlockType.DAY_SENSOR, Conversion.of(Blocks.day_sensor, Models.day_sensor));
        add(BlockType.DOOR, Conversion.of(Blocks.door, Models.door_bottom, Models.door_bottom_rh, Models.door_top, Models.door_top_rh));
        add(BlockType.DOUBLE_PLANT, Conversion.of(Blocks.double_plant, Models.double_plant_bottom, Models.double_plant_top));
        add(BlockType.FENCE, Conversion.of(Blocks.fence, Models.fence_n, Models.fence_ne, Models.fence_ns, Models.fence_nse, Models.fence_nsew, Models.fence_post));
        add(BlockType.FENCE_GATE, Conversion.of(Blocks.fence_gate, Models.fence_gate_closed, Models.fence_gate_open, Models.wall_gate_closed, Models.wall_gate_open));
        add(BlockType.FURNACE, Conversion.of(Blocks.furnace, Models.furnace));
        add(BlockType.GHOST_BLOCK, Conversion.of(Blocks.ghost_block, Models.ghost_block));
        add(BlockType.GHOST_PANE, Conversion.of(Blocks.ghost_pane, Models.ghost_pane_n, Models.ghost_pane_ne, Models.ghost_pane_new, Models.ghost_pane_ns, Models.ghost_pane_nse, Models.ghost_pane_nsew, Models.ghost_pane_nsw, Models.ghost_pane_nw, Models.ghost_pane_s, Models.ghost_pane_se, Models.ghost_pane_sew, Models.ghost_pane_sw));
        add(BlockType.GLASS, Conversion.of(Blocks.glass, Models.glass));
        add(BlockType.GLASS_STAINED, Conversion.of(Blocks.glass_stained, Models.glass_stained));
        add(BlockType.HALF_DOOR, Conversion.of(Blocks.half_door, Models.half_door_bottom, Models.half_door_bottom_rh));
        add(BlockType.ICE, Conversion.of(Blocks.ice, Models.ice));
        add(BlockType.LADDER, Conversion.of(Blocks.ladder, Models.ladder));
        add(BlockType.LEAVES, Conversion.of(Blocks.leaves, Models.leaves));
        add(BlockType.LIGHT_WEB, Conversion.of(Blocks.light_web, Models.light_web));
        add(BlockType.LOG, Conversion.of(Blocks.log, Models.directional_vertical, Models.directional_horizontal, Models.directional_none));
        add(BlockType.PANE, Conversion.of(Blocks.pane, Models.pane_n, Models.pane_ne, Models.pane_new, Models.pane_ns, Models.pane_nse, Models.pane_nsew, Models.pane_nsw, Models.pane_nw, Models.pane_s, Models.pane_se, Models.pane_sew, Models.pane_sw));
        add(BlockType.PANE_STAINED, Conversion.of(Blocks.pane_stained, Models.pane_stained_n, Models.pane_stained_ne, Models.pane_stained_new, Models.pane_stained_ns, Models.pane_stained_nse, Models.pane_stained_nsew, Models.pane_stained_nsw, Models.pane_stained_nw, Models.pane_stained_s, Models.pane_stained_se, Models.pane_stained_sew, Models.pane_stained_sw));
        add(BlockType.PILLAR, Conversion.of(Blocks.directional, Models.directional_vertical, Models.directional_horizontal, Models.directional_none));
        add(BlockType.PLANT, Conversion.of(Blocks.plant, Models.plant));
        add(BlockType.PLATE, Conversion.of(Blocks.plate, Models.plate));
        add(BlockType.PISTON_EXTENSION, Conversion.of(Blocks.piston_extension, Models.piston_extension));
        add(BlockType.POT, Conversion.of(Blocks.pot, Models.pot));
        add(BlockType.SHORT_CHAIR, Conversion.of(Blocks.short_chair, Models.short_chair));
        add(BlockType.SLAB, Conversion.of(Blocks.slab_half, Models.slab_half, Models.slab_upper));
        add(BlockType.SLAB, Conversion.of(Blocks.slab_double, Models.slab_double));
        add(BlockType.SLIM_SLAB, Conversion.of(Blocks.paving, Models.paving_bottom, Models.paving_top));
        add(BlockType.STAIRS, Conversion.of(Blocks.stairs, Models.stairs, Models.stairs_inner, Models.stairs_outer).item(Models.itemStair));
        add(BlockType.TORCH, Conversion.of(Blocks.torch, Models.torch, Models.torch_wall));
        add(BlockType.TRAP_DOOR, Conversion.of(Blocks.trapdoor, Models.trapdoor_bottom, Models.trapdoor_open, Models.trapdoor_top));
        add(BlockType.TRUNK, Conversion.of(Blocks.trunk, Models.trunk));
        add(BlockType.WALL, Conversion.of(Blocks.wall, Models.wall_n, Models.wall_ne, Models.wall_ns, Models.wall_nse, Models.wall_nsew, Models.wall_post, Models.wall_inventory));
        add(BlockType.WEB, Conversion.of(Blocks.web, Models.web));
    }

    public static void add(BlockType blockType, Conversion conversion)
    {
        List<Conversion> conversionList = conversions.get(blockType);
        if (conversionList == null)
        {
            conversions.put(blockType, conversionList = new ArrayList<Conversion>());
        }
        conversionList.add(conversion);
    }

    public static List<Conversion> forType(BlockType blockType)
    {
        List<Conversion> conversionList = conversions.get(blockType);
        if (conversionList == null)
        {
            return EMPTY;
        }
        return conversionList;
    }

    public static void clear()
    {
        conversions.clear();
    }
}

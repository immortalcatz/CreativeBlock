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

/**
 * @author dags <dags@dags.me>
 */

public class Models
{
    /**
     * Paths
     */
    private static final String BLOCK = "creativeblock:templates/models/block/";
    private static final String ITEM = "creativeblock:templates/models/item/";

    /**
     * Item Models
     */
    public static final JsonData itemBlock = JsonData.of(ITEM + "item_block.json", "");
    public static final JsonData itemFence = JsonData.of(ITEM + "item_fence.json", "");
    public static final JsonData itemStair = JsonData.of(ITEM + "item_stairs.json", "");

    /**
     * Block Models
     */
    public static final JsonData block = JsonData.of(BLOCK + "block.json", "_block");
    public static final JsonData directional_vertical = JsonData.of(BLOCK + "directional/directional_vertical.json", "_directional_vertical");
    public static final JsonData directional_horizontal = JsonData.of(BLOCK + "directional/directional_horizontal.json", "_directional_horizontal");
    public static final JsonData directional_none = JsonData.of(BLOCK + "directional/directional_none.json", "_directional_none");
    public static final JsonData door_bottom = JsonData.of(BLOCK + "door/door_bottom.json", "_door_bottom");
    public static final JsonData door_bottom_rh = JsonData.of(BLOCK + "door/door_bottom_rh.json", "_door_bottom_rh");
    public static final JsonData door_top = JsonData.of(BLOCK + "door/door_top.json", "_door_top");
    public static final JsonData door_top_rh = JsonData.of(BLOCK + "door/door_top_rh.json", "_door_top_rh");
    public static final JsonData fence_inventory = JsonData.of(BLOCK + "fence/fence_inventory.json", "_fence_inventory");
    public static final JsonData fence_n = JsonData.of(BLOCK + "fence/fence_n.json", "_fence_n");
    public static final JsonData fence_ne = JsonData.of(BLOCK + "fence/fence_ne.json", "_fence_ne");
    public static final JsonData fence_ns = JsonData.of(BLOCK + "fence/fence_ns.json", "_fence_ns");
    public static final JsonData fence_nse = JsonData.of(BLOCK + "fence/fence_nse.json", "_fence_nse");
    public static final JsonData fence_nsew = JsonData.of(BLOCK + "fence/fence_nsew.json", "_fence_nsew");
    public static final JsonData fence_post = JsonData.of(BLOCK + "fence/fence_post.json", "_fence_post");

    public static final JsonData fence_gate_closed = JsonData.of(BLOCK + "fencegate/fence_gate_closed.json", "_fence_gate_closed");
    public static final JsonData fence_gate_open = JsonData.of(BLOCK + "fencegate/fence_gate_open.json", "_fence_gate_open");
    public static final JsonData wall_gate_closed = JsonData.of(BLOCK + "fencegate/wall_gate_closed.json", "_wall_gate_closed");
    public static final JsonData wall_gate_open = JsonData.of(BLOCK + "fencegate/wall_gate_open.json", "_wall_gate_open");

    public static final JsonData ghost_block = JsonData.of(BLOCK + "block.json", "_ghost_block");

    public static final JsonData ghost_pane_n = JsonData.of(BLOCK + "pane/pane_n.json", "_ghost_pane_n");
    public static final JsonData ghost_pane_ne = JsonData.of(BLOCK + "pane/pane_ne.json", "_ghost_pane_ne");
    public static final JsonData ghost_pane_new = JsonData.of(BLOCK + "pane/pane_new.json", "_ghost_pane_new");
    public static final JsonData ghost_pane_ns = JsonData.of(BLOCK + "pane/pane_ns.json", "_ghost_pane_ns");
    public static final JsonData ghost_pane_nse = JsonData.of(BLOCK + "pane/pane_nse.json", "_ghost_pane_nse");
    public static final JsonData ghost_pane_nsew = JsonData.of(BLOCK + "pane/pane_nsew.json", "_ghost_pane_nsew");
    public static final JsonData ghost_pane_nsw = JsonData.of(BLOCK + "pane/pane_nsw.json", "_ghost_pane_nsw");
    public static final JsonData ghost_pane_nw = JsonData.of(BLOCK + "pane/pane_nw.json", "_ghost_pane_nw");
    public static final JsonData ghost_pane_s = JsonData.of(BLOCK + "pane/pane_s.json", "_ghost_pane_s");
    public static final JsonData ghost_pane_se = JsonData.of(BLOCK + "pane/pane_se.json", "_ghost_pane_se");
    public static final JsonData ghost_pane_sew = JsonData.of(BLOCK + "pane/pane_sew.json", "_ghost_pane_sew");
    public static final JsonData ghost_pane_sw = JsonData.of(BLOCK + "pane/pane_sw.json", "_ghost_pane_sw");

    public static final JsonData glass = JsonData.of(BLOCK + "block.json", "_glass");
    public static final JsonData glass_stained = JsonData.of(BLOCK + "block.json", "_stained_glass");

    public static final JsonData half_door_bottom = JsonData.of(BLOCK + "door/door_top.json", "_half_door_bottom");
    public static final JsonData half_door_bottom_rh = JsonData.of(BLOCK + "door/door_top_rh.json", "_half_door_bottom_rh");
    public static final JsonData leaves = JsonData.of(BLOCK + "block.json", "_leaves");
    public static final JsonData light_web = JsonData.of(BLOCK + "web.json", "_light_web");
    public static final JsonData paving_bottom = JsonData.of(BLOCK + "paving/paving_bottom.json", "_paving_bottom");
    public static final JsonData slab_half = JsonData.of(BLOCK + "slab/slab_half.json", "_half_slab");
    public static final JsonData pane_n = JsonData.of(BLOCK + "pane/pane_n.json", "_pane_n");
    public static final JsonData pane_ne = JsonData.of(BLOCK + "pane/pane_ne.json", "_pane_ne");
    public static final JsonData pane_new = JsonData.of(BLOCK + "pane/pane_new.json", "_pane_new");
    public static final JsonData pane_ns = JsonData.of(BLOCK + "pane/pane_ns.json", "_pane_ns");
    public static final JsonData pane_nse = JsonData.of(BLOCK + "pane/pane_nse.json", "_pane_nse");
    public static final JsonData pane_nsew = JsonData.of(BLOCK + "pane/pane_nsew.json", "_pane_nsew");
    public static final JsonData pane_nsw = JsonData.of(BLOCK + "pane/pane_nsw.json", "_pane_nsw");
    public static final JsonData pane_nw = JsonData.of(BLOCK + "pane/pane_nw.json", "_pane_nw");
    public static final JsonData pane_s = JsonData.of(BLOCK + "pane/pane_s.json", "_pane_s");
    public static final JsonData pane_se = JsonData.of(BLOCK + "pane/pane_se.json", "_pane_se");
    public static final JsonData pane_sew = JsonData.of(BLOCK + "pane/pane_sew.json", "_pane_sew");
    public static final JsonData pane_sw = JsonData.of(BLOCK + "pane/pane_sw.json", "_pane_sw");
    public static final JsonData pane_stained_n = JsonData.of(BLOCK + "pane/pane_n.json", "_pane_stained_n");
    public static final JsonData pane_stained_ne = JsonData.of(BLOCK + "pane/pane_ne.json", "_pane_stained_ne");
    public static final JsonData pane_stained_new = JsonData.of(BLOCK + "pane/pane_new.json", "_pane_stained_new");
    public static final JsonData pane_stained_ns = JsonData.of(BLOCK + "pane/pane_ns.json", "_pane_stained_ns");
    public static final JsonData pane_stained_nse = JsonData.of(BLOCK + "pane/pane_nse.json", "_pane_stained_nse");
    public static final JsonData pane_stained_nsew = JsonData.of(BLOCK + "pane/pane_nsew.json", "_pane_stained_nsew");
    public static final JsonData pane_stained_nsw = JsonData.of(BLOCK + "pane/pane_nsw.json", "_pane_stained_nsw");
    public static final JsonData pane_stained_nw = JsonData.of(BLOCK + "pane/pane_nw.json", "_pane_stained_nw");
    public static final JsonData pane_stained_s = JsonData.of(BLOCK + "pane/pane_s.json", "_pane_stained_s");
    public static final JsonData pane_stained_se = JsonData.of(BLOCK + "pane/pane_se.json", "_pane_stained_se");
    public static final JsonData pane_stained_sew = JsonData.of(BLOCK + "pane/pane_sew.json", "_pane_stained_sew");
    public static final JsonData pane_stained_sw = JsonData.of(BLOCK + "pane/pane_sw.json", "_pane_stained_sw");
    public static final JsonData paving_top = JsonData.of(BLOCK + "paving/paving_top.json", "_paving_top");
    public static final JsonData slab_upper = JsonData.of(BLOCK + "slab/slab_upper.json", "_upper_slab");
    public static final JsonData slab_double = JsonData.of(BLOCK + "block.json", "_double_slab");
    public static final JsonData stairs = JsonData.of(BLOCK + "stairs/stairs.json", "_stairs");
    public static final JsonData stairs_inner = JsonData.of(BLOCK + "stairs/stairs_inner.json", "_inner_stairs");
    public static final JsonData stairs_outer = JsonData.of(BLOCK + "stairs/stairs_outer.json", "_outer_stairs");
    public static final JsonData trapdoor_bottom = JsonData.of(BLOCK + "trapdoor/trapdoor_bottom.json", "_trapdoor_bottom");
    public static final JsonData trapdoor_open = JsonData.of(BLOCK + "trapdoor/trapdoor_open.json", "_trapdoor_open");
    public static final JsonData trapdoor_top = JsonData.of(BLOCK + "trapdoor/trapdoor_top.json", "_trapdoor_top");
    public static final JsonData trunk = JsonData.of(BLOCK + "trunk.json", "_trunk");
    public static final JsonData wall_inventory = JsonData.of(BLOCK + "wall/wall_inventory.json", "_wall_inventory");
    public static final JsonData wall_n = JsonData.of(BLOCK + "wall/wall_n.json", "_wall_n");
    public static final JsonData wall_ne = JsonData.of(BLOCK + "wall/wall_ne.json", "_wall_ne");
    public static final JsonData wall_ns = JsonData.of(BLOCK + "wall/wall_ns.json", "_wall_ns");
    public static final JsonData wall_nse = JsonData.of(BLOCK + "wall/wall_nse.json", "_wall_nse");
    public static final JsonData wall_nsew = JsonData.of(BLOCK + "wall/wall_nsew.json", "_wall_nsew");
    public static final JsonData wall_post = JsonData.of(BLOCK + "wall/wall_post.json", "_wall_post");
    public static final JsonData web = JsonData.of(BLOCK + "web.json", "_web");

}

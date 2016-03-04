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

package me.dags.creativeblock.app.data;

import me.dags.creativeblock.adapter.BlockNames;

/**
 * @author dags <dags@dags.me>
 */

public class Blocks
{
    private static final String TEMPLATES = "creativeblock:templates/blockstates/";

    public static final JsonData anvil = JsonData.of(TEMPLATES + "anvil.json", "_" + BlockNames.get().anvil);
    public static final JsonData block = JsonData.of(TEMPLATES + "block.json", "_" + BlockNames.get().block);
    public static final JsonData button = JsonData.of(TEMPLATES + "button.json", "_" + BlockNames.get().button);
    public static final JsonData carpet = JsonData.of(TEMPLATES + "carpet.json", "_" + BlockNames.get().carpet);
    public static final JsonData cauldron = JsonData.of(TEMPLATES + "cauldron.json", "_" + BlockNames.get().cauldron);
    public static final JsonData chair = JsonData.of(TEMPLATES + "chair.json", "_" + BlockNames.get().chair);
    public static final JsonData crops = JsonData.of(TEMPLATES + "crops.json", "_" + BlockNames.get().crops);
    public static final JsonData day_sensor = JsonData.of(TEMPLATES + "day_sensor.json", "_" + BlockNames.get().sensor);
    public static final JsonData directional = JsonData.of(TEMPLATES + "directional.json", "_" + BlockNames.get().directional);
    public static final JsonData door = JsonData.of(TEMPLATES + "door.json", "_" + BlockNames.get().door);
    public static final JsonData double_plant = JsonData.of(TEMPLATES + "double_plant.json", "_" + BlockNames.get().double_plant);
    public static final JsonData fence = JsonData.of(TEMPLATES + "fence.json", "_" + BlockNames.get().fence);
    public static final JsonData fence_gate = JsonData.of(TEMPLATES + "fence_gate.json", "_" + BlockNames.get().fence_gate);
    public static final JsonData furnace = JsonData.of(TEMPLATES + "furnace.json", "_" + BlockNames.get().furnace);
    public static final JsonData ghost_block = JsonData.of(TEMPLATES + "ghost_block.json", "_" + BlockNames.get().ghost_block);
    public static final JsonData ghost_pane = JsonData.of(TEMPLATES + "ghost_pane.json", "_" + BlockNames.get().ghost_pane);
    public static final JsonData glass = JsonData.of(TEMPLATES + "glass.json", "_" + BlockNames.get().glass);
    public static final JsonData glass_stained = JsonData.of(TEMPLATES + "glass_stained.json", "_" + BlockNames.get().glass_stained);
    public static final JsonData half_door = JsonData.of(TEMPLATES + "half_door.json", "_" + BlockNames.get().half_door);
    public static final JsonData ice = JsonData.of(TEMPLATES + "ice.json", "_" + BlockNames.get().ice);
    public static final JsonData ladder = JsonData.of(TEMPLATES + "ladder.json", "_" + BlockNames.get().ladder);
    public static final JsonData leaves = JsonData.of(TEMPLATES + "leaves.json", "_" + BlockNames.get().leaves);
    public static final JsonData light_web = JsonData.of(TEMPLATES + "light_web.json", "_" + BlockNames.get().light_web);
    public static final JsonData log = JsonData.of(TEMPLATES + "directional.json", "_" + BlockNames.get().log);
    public static final JsonData pane = JsonData.of(TEMPLATES + "pane.json", "_" + BlockNames.get().pane);
    public static final JsonData pane_stained = JsonData.of(TEMPLATES + "pane_stained.json", "_" + BlockNames.get().pane_stained);
    public static final JsonData paving = JsonData.of(TEMPLATES + "paving.json", "_" + BlockNames.get().paving);
    public static final JsonData plant = JsonData.of(TEMPLATES + "plant.json", "_" + BlockNames.get().plant);
    public static final JsonData plate = JsonData.of(TEMPLATES + "plate.json", "_" + BlockNames.get().plate);
    public static final JsonData piston_extension = JsonData.of(TEMPLATES + "piston_extension.json", "_" + BlockNames.get().piston_extension);
    public static final JsonData pot = JsonData.of(TEMPLATES + "pot.json", "_" + BlockNames.get().pot);
    public static final JsonData short_chair = JsonData.of(TEMPLATES + "short_chair.json", "_" + BlockNames.get().short_chair);
    public static final JsonData slab_double = JsonData.of(TEMPLATES + "slab_double.json", "_" + BlockNames.get().double_slab);
    public static final JsonData slab_half = JsonData.of(TEMPLATES + "slab.json", "_" + BlockNames.get().slab);
    public static final JsonData stairs = JsonData.of(TEMPLATES + "stairs.json", "_" + BlockNames.get().stairs);
    public static final JsonData torch = JsonData.of(TEMPLATES + "torch.json", "_" + BlockNames.get().torch);
    public static final JsonData trapdoor = JsonData.of(TEMPLATES + "trapdoor.json", "_" + BlockNames.get().trapdoor);
    public static final JsonData trunk = JsonData.of(TEMPLATES + "trunk.json", "_" + BlockNames.get().trunk);
    public static final JsonData wall = JsonData.of(TEMPLATES + "wall.json", "_" + BlockNames.get().wall);
    public static final JsonData web = JsonData.of(TEMPLATES + "web.json", "_" + BlockNames.get().web);
}

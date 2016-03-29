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

package me.dags.creativeblock.dynmap.models;

import org.dynmap.modsupport.BlockTextureRecord;
import org.dynmap.modsupport.BoxBlockModel;
import org.dynmap.modsupport.ModTextureDefinition;
import org.dynmap.modsupport.TransparencyMode;

/**
 * @author dags <dags@dags.me>
 */

public class BlockTrapdoor extends AbstractModel
{
    @Override
    void applyModel(ModTextureDefinition definition, String name)
    {
        top(definition, name, 8);
        top(definition, name, 9);
        top(definition, name, 10);
        top(definition, name, 11);

        bottom(definition, name, 0);
        bottom(definition, name, 1);
        bottom(definition, name, 2);
        bottom(definition, name, 3);

        south(definition, name);
        north(definition, name);
        east(definition, name);
        west(definition, name);
    }

    @Override
    void applyProperties(BlockTextureRecord record)
    {
        record.setTransparencyMode(TransparencyMode.SEMITRANSPARENT);
    }

    private void top(ModTextureDefinition definition, String name, int meta)
    {
        BoxBlockModel top = definition.getModelDefinition().addBoxModel(name);
        top.setMetaValue(meta);
        top.setYRange(0.9, 1);
        top.setXRange(0, 1);
        top.setZRange(0, 1);
    }

    private void bottom(ModTextureDefinition definition, String name, int meta)
    {
        BoxBlockModel bottom = definition.getModelDefinition().addBoxModel(name);
        bottom.setMetaValue(meta);
        bottom.setYRange(0, 0.1);
        bottom.setXRange(0, 1);
        bottom.setZRange(0, 1);
    }

    private void south(ModTextureDefinition definition, String name)
    {
        BoxBlockModel side4 = definition.getModelDefinition().addBoxModel(name);
        side4.setMetaValue(4);
        side4.setYRange(0, 1);
        side4.setXRange(0, 1);
        side4.setZRange(0.9, 1);

        BoxBlockModel side12 = definition.getModelDefinition().addBoxModel(name);
        side12.setMetaValue(12);
        side12.setYRange(0, 1);
        side12.setXRange(0, 1);
        side12.setZRange(0.9, 1);
    }

    private void north(ModTextureDefinition definition, String name)
    {
        BoxBlockModel side5 = definition.getModelDefinition().addBoxModel(name);
        side5.setMetaValue(5);
        side5.setYRange(0, 1);
        side5.setXRange(0, 1);
        side5.setZRange(0, 0.1);

        BoxBlockModel side13 = definition.getModelDefinition().addBoxModel(name);
        side13.setMetaValue(13);
        side13.setYRange(0, 1);
        side13.setXRange(0, 1);
        side13.setZRange(0, 0.1);
    }

    private void east(ModTextureDefinition definition, String name)
    {
        BoxBlockModel side6 = definition.getModelDefinition().addBoxModel(name);
        side6.setMetaValue(6);
        side6.setYRange(0, 1);
        side6.setXRange(0.9, 1);
        side6.setZRange(0, 1);

        BoxBlockModel side14 = definition.getModelDefinition().addBoxModel(name);
        side14.setMetaValue(14);
        side14.setYRange(0, 1);
        side14.setXRange(0.9, 1);
        side14.setZRange(0, 1);
    }

    private void west(ModTextureDefinition definition, String name)
    {
        BoxBlockModel side7 = definition.getModelDefinition().addBoxModel(name);
        side7.setMetaValue(7);
        side7.setYRange(0, 1);
        side7.setXRange(0, 0.1);
        side7.setZRange(0, 1);

        BoxBlockModel side15 = definition.getModelDefinition().addBoxModel(name);
        side15.setMetaValue(15);
        side15.setYRange(0, 1);
        side15.setXRange(0, 0.1);
        side15.setZRange(0, 1);
    }
}

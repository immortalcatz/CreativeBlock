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

package me.dags.creativeblock.block;

import me.dags.creativeblock.adapter.BlockAdapter;
import me.dags.creativeblock.adapter.BlockTypeAdapter;
import me.dags.creativeblock.definition.BlockDefinition;
import net.minecraft.block.Block;

/**
 * @author dags <dags@dags.me>
 */

public class CBLightWeb extends CBWeb
{
    public static BlockTypeAdapter adapter()
    {
        return new BlockAdapter("light_web")
        {
            @Override
            public void createBlock(String transformedName, BlockDefinition definition)
            {
                Block b0 = new CBLightWeb().setLightLevel(1.0F);
                Block b1 = new CBLightWeb().setLightLevel(0.75F);
                Block b2 = new CBLightWeb().setLightLevel(0.5F);

                super.setAttributes(b0, transformedName + "_0", definition);
                super.setAttributes(b1, transformedName + "_1", definition);
                super.setAttributes(b2, transformedName + "_2", definition);

                super.registerBlock(b0, definition);
                super.registerBlock(b1, definition);
                super.registerBlock(b2, definition);
            }
        };
    }
}

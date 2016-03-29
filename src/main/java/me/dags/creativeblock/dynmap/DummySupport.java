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

package me.dags.creativeblock.dynmap;

import me.dags.creativeblock.CreativeBlock;
import me.dags.creativeblock.definition.BlockTextures;
import me.dags.creativeblock.definition.BlockType;
import me.dags.creativeblock.util.LogUtil;
import net.minecraft.block.Block;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author dags <dags@dags.me>
 */

public class DummySupport implements IDynmapSupport
{
    @Override
    public void copyTextures()
    {}

    @Override
    public void copyTexture(String name, InputStream resourceStream) throws IOException
    {}

    @Override
    public void registerBlock(Block block, BlockType type, BlockTextures textures)
    {}

    @Override
    public void publish()
    {

    }

    public static IDynmapSupport getInstance(CreativeBlock creativeBlock)
    {
        try
        {
            Class.forName("org.dynmap.modsupport.ModSupportAPI");
        }
        catch (ClassNotFoundException e)
        {
            LogUtil.info(IDynmapSupport.class, "Dynmap not detected, bypassing dynmap support");
            return new DummySupport();
        }
        LogUtil.info(IDynmapSupport.class, "Dynmap detected, providing modblock support!");
        return DynmapSupport.newInstance(creativeBlock);
    }
}

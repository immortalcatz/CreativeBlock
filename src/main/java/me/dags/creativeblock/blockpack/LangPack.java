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

package me.dags.creativeblock.blockpack;

import me.dags.creativeblock.CreativeBlock;
import me.dags.creativeblock.Tabs;
import me.dags.creativeblock.definition.BlockDefinition;
import me.dags.creativeblock.util.FileUtil;
import me.dags.creativeblock.util.LogUtil;
import net.minecraftforge.fml.client.FMLFolderResourcePack;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dags <dags@dags.me>
 */

public class LangPack extends BlockPack
{
    public LangPack(CreativeBlock creativeBlock)
    {
        super(FileUtil.getDir(creativeBlock.blockpackDir(), "langpack-1.0"));
        LogUtil.blockpack(this, "Generating language pack...");
        File langDir = FileUtil.getDir(this.getSource(), "assets", creativeBlock.domain(), "lang");
        File langFile = new File(langDir, "en_US.lang");
        try
        {
            if (!langFile.exists() && langFile.createNewFile())
            {
                LogUtil.io(this, "Creating new lang file {}", langFile);
            }
            List<String> lines = new ArrayList<>();
            lines.addAll(creativeBlock.registrar().getBlockNames());
            lines.addAll(Tabs.getLang());
            LogUtil.io(this, "Writing {} lines to lang file {}", lines.size(), langFile);
            Files.write(langFile.toPath(), lines, Charset.defaultCharset());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Class<?> getCustomResourcePackClass()
    {
        return FMLFolderResourcePack.class;
    }

    @Override
    public List<BlockDefinition> getDefinitions()
    {
        return new ArrayList<>();
    }

    @Override
    public void copyServerTextures() throws IOException
    {

    }
}

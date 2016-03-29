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
import me.dags.creativeblock.definition.BlockDefinition;
import me.dags.creativeblock.definition.serialize.DefinitionSerializable;
import me.dags.creativeblock.util.FileUtil;
import me.dags.creativeblock.util.JsonUtil;
import net.minecraftforge.fml.client.FMLFolderResourcePack;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @author dags <dags@dags.me>
 */

public class FolderBlockPack extends BlockPack
{
    private final CreativeBlock creativeBlock;

    public FolderBlockPack(CreativeBlock creativeBlock, File file)
    {
        super(file);
        this.creativeBlock = creativeBlock;
    }

    public String getTabName(File definitions, File file)
    {
        File f = file;
        while (!f.getParentFile().equals(definitions))
        {
            f = f.getParentFile();
        }
        return f.equals(file)  ? "general" : f.getName().toLowerCase();
    }

    @Override
    public List<BlockDefinition> getDefinitions()
    {
        List<BlockDefinition> definitions = new ArrayList<>();
        final File definitionsDir = FileUtil.getDir(this.getSource(), "assets", creativeBlock.domain(), "definitions");
        final File texturesDir = FileUtil.getDir(this.getSource(), "assets", creativeBlock.domain(), "textures", "blocks");

        Iterator<File> defIterator = FileUtils.iterateFilesAndDirs(definitionsDir, definitionsFilter(), TrueFileFilter.INSTANCE);
        while (defIterator.hasNext())
        {
            File file = defIterator.next();
            Optional<DefinitionSerializable> optional = JsonUtil.deserialize(file, DefinitionSerializable.class);
            if (optional.isPresent())
            {
                DefinitionSerializable jsonDefinition = optional.get();
                jsonDefinition.tabId = getTabName(definitionsDir, file);
                BlockDefinition definition = BlockDefinition.from(optional.get());
                definitions.add(definition);
            }
        }
        return definitions;
    }

    @Override
    public void copyServerTextures() throws IOException
    {}

    @Override
    public Class<?> getCustomResourcePackClass()
    {
        return FMLFolderResourcePack.class;
    }

    private static IOFileFilter definitionsFilter()
    {
        return new IOFileFilter()
        {
            @Override
            public boolean accept(File file)
            {
                return file.isFile();
            }

            @Override
            public boolean accept(File dir, String name)
            {
                return name.endsWith(".json");
            }
        };
    }

    private static IOFileFilter textureFilter()
    {
        return new IOFileFilter()
        {
            @Override
            public boolean accept(File file)
            {
                return file.isFile();
            }

            @Override
            public boolean accept(File dir, String name)
            {
                return name.endsWith(".png");
            }
        };
    }
}

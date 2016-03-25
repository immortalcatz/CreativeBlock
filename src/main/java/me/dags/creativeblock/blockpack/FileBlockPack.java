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

import com.google.gson.stream.JsonReader;
import me.dags.creativeblock.CreativeBlock;
import me.dags.creativeblock.definition.BlockDefinition;
import me.dags.creativeblock.definition.serialize.DefinitionSerializable;
import me.dags.creativeblock.util.JsonUtil;
import net.minecraftforge.fml.client.FMLFileResourcePack;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author dags <dags@dags.me>
 */

public class FileBlockPack extends BlockPack
{
    private final String definitionsDir;

    public FileBlockPack(CreativeBlock creativeBlock, File file)
    {
        super(file);
        definitionsDir = "assets/" + creativeBlock.domain() + "/definitions";
    }

    @Override
    public List<BlockDefinition> getDefinitions()
    {
        List<BlockDefinition> definitions = new ArrayList<BlockDefinition>();

        try
        {
            populate(definitions);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return definitions;
    }

    private String getTabName(String path)
    {
        String p = path.substring(definitionsDir.length() + 1);
        int index = p.indexOf("/");
        if (index > 0)
        {
            return p.substring(0, index);
        }
        return "general";
    }

    private void populate(List<BlockDefinition> definitions ) throws IOException
    {
        ZipFile zipFile = new ZipFile(getSource());
        Enumeration<? extends ZipEntry> entries = zipFile.entries();

        while (entries.hasMoreElements())
        {
            ZipEntry entry = entries.nextElement();
            if (entry.getName().startsWith(definitionsDir) && entry.getName().endsWith(".json"))
            {
                JsonReader reader = new JsonReader(new InputStreamReader(zipFile.getInputStream(entry)));
                DefinitionSerializable definition = JsonUtil.deserialize(reader, DefinitionSerializable.class);
                definition.tabId = getTabName(entry.getName());
                definitions.add(BlockDefinition.from(definition));
            }
        }
    }

    @Override
    public Class<?> getCustomResourcePackClass()
    {
        return FMLFileResourcePack.class;
    }
}

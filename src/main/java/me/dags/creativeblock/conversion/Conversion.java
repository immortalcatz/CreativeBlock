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

package me.dags.creativeblock.conversion;

import me.dags.creativeblock.conversion.data.JsonData;
import me.dags.creativeblock.conversion.data.Models;
import me.dags.creativeblock.conversion.replacefunction.ReplaceFunction;
import me.dags.creativeblock.conversion.replacefunction.StringReplacer;
import me.dags.creativeblock.conversion.replacefunction.TextureReplacer;
import me.ardacraft.acblocks.legacy.TextureWrapper;
import me.dags.creativeblock.CreativeBlock;
import me.dags.creativeblock.definition.BlockType;
import me.dags.creativeblock.util.FileUtil;
import me.dags.creativeblock.util.LogUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author dags <dags@dags.me>
 */

public class Conversion
{
    private static final StringReplacer itemReplacer = StringReplacer.of("#insert");

    private final JsonData blockState;
    private final List<JsonData> models;
    private final String itemSuffix;
    private JsonData item = Models.itemBlock;
    private ReplaceFunction<String> blockStateEdits;
    private ReplaceFunction<TextureWrapper> modelEdits;

    private Conversion(JsonData block, String item, JsonData... model)
    {
        blockState = block;
        models = Arrays.asList(model);
        itemSuffix = item;
        blockStateEdits = StringReplacer.of("#insert");
        modelEdits = new TextureReplacer();
    }

    public Conversion item(JsonData data)
    {
        item = data;
        return this;
    }

    public Conversion stateEdit(ReplaceFunction<String> replacer)
    {
        blockStateEdits = replacer;
        return this;
    }

    public Conversion modelEdit(ReplaceFunction<TextureWrapper> replacer)
    {
        modelEdits = replacer;
        return this;
    }

    public void writeStateFor(BlockType blockType, File root, String name) throws IOException
    {
        File outDir = FileUtil.getDir(root, "blockstates");

        for (String suff : blockType.suffixes())
        {
            String fileName = name + blockState.suffix() + suff;
            String content = blockState.replace(CreativeBlock.domain() + ":" + name.toLowerCase(), blockStateEdits);

            writeFile(new File(outDir, fileName + ".json"), content);
        }
    }

    public void writeModelsFor(BlockType blockType, File root, String name, TextureWrapper textures) throws IOException
    {
        File blockDir = FileUtil.getDir(root, "models", "block");
        File itemDir = FileUtil.getDir(root, "models", "item");

        for (JsonData data : models)
        {
            // blockModel can be lowercase
            String bModelName = (name + data.suffix()).toLowerCase();
            String blockModel = data.replace(textures, modelEdits);
            writeFile(new File(blockDir, bModelName + ".json"), blockModel);

            for (String suff : blockType.suffixes())
            {
                String iModelName = name + blockState.suffix() + suff;
                String itemModel = item.replace(CreativeBlock.domain() + ":block/" + bModelName, itemReplacer);
                writeFile(new File(itemDir, iModelName + ".json"), itemModel);
            }
        }
    }

    private void writeFile(File out, String content) throws IOException
    {
        if (!out.exists() && out.createNewFile())
        {
            LogUtil.io(this, "Creating file {}", out);
            FileWriter writer = new FileWriter(out);
            writer.write(content);
            writer.flush();
            writer.close();
        }
    }

    public static Conversion of(JsonData block, String itemModel, JsonData... model)
    {
        return new Conversion(block, itemModel, model);
    }

    public static Conversion of(JsonData block, JsonData... model)
    {
        return of(block, block.suffix(), model);
    }
}

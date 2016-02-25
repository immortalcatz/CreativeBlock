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

package me.dags.creativeblock.definition;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author dags <dags@dags.me>
 */

public class BlockDefinition
{
    public final String name;
    public final String tabId;
    public final BaseMaterial material;
    public final List<BlockType> blockTypes;

    private BlockDefinition(String name, String tabId, BaseMaterial material, List<BlockType> types)
    {
        this.name = name;
        this.tabId = tabId;
        this.material = material;
        this.blockTypes = types;
    }

    public void register()
    {
        for (BlockType type : blockTypes)
        {
            type.register(this);
        }
    }

    public JsonDefinition toJsonDefinition()
    {
        String mat = material.name();
        String[] types = new String[blockTypes.size()];
        for (int i = 0; i < types.length; i++)
        {
            types[i] = blockTypes.get(i).name();
        }
        JsonDefinition definition = new JsonDefinition();
        definition.name = name;
        definition.base = mat;
        definition.types = types;
        return definition;
    }

    public static BlockDefinition from(JsonDefinition definition)
    {
        if (!definition.validate())
        {
            throw new UnsupportedOperationException("Invalid definition!");
        }
        Optional<BaseMaterial> matOptional = BaseMaterial.from(definition.base);
        List<BlockType> types = new ArrayList<BlockType>();
        for (String s : definition.types)
        {
            Optional<BlockType> typeOptional = BlockType.from(s);
            if (typeOptional.isPresent())
            {
                types.add(typeOptional.get());
            }
        }
        return new BlockDefinition(definition.name, definition.tabId, matOptional.get(), types);
    }
}

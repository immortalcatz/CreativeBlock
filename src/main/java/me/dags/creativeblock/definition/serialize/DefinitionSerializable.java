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

package me.dags.creativeblock.definition.serialize;

import me.dags.creativeblock.definition.BaseMaterial;
import me.dags.creativeblock.util.LogUtil;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author dags <dags@dags.me>
 */

public class DefinitionSerializable
{
    private static final String LOG_FORMAT = "{name:%1s, base:%2s, tabId:%3s, types:%4s}";

    public String name = "";
    public String base = "";
    public String[] types = {};
    public Map<String, String> textures = new LinkedHashMap<>();
    public transient String tabId = "";
    public transient String file;

    public boolean validate()
    {
        if (name.isEmpty())
        {
            LogUtil.info("[WARN] BlockDefinition Name cannot be empty!");
            return false;
        }
        if (!BaseMaterial.has(base))
        {
            LogUtil.info("[WARN] BlockDefinition Sound does not exist!");
            return false;
        }
        if (types.length == 0)
        {
            LogUtil.info("[WARN] BlockDefinition no Types defined!");
            return false;
        }
        if (tabId.isEmpty())
        {
            LogUtil.info("[WARN] BlockDefinition TabId cannot be empty!");
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return String.format(LOG_FORMAT, name, base, tabId, Arrays.toString(types));
    }

    public String toJson()
    {
        String indent = "  ";
        String newLine = "\n";
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(newLine).append(indent).append("\"name\": \"").append(name).append("\",");
        sb.append(newLine).append(indent).append("\"base\": \"").append(base).append("\",");
        sb.append(newLine).append(indent).append("\"textures\": {");
        int c = textures.size();
        for (Map.Entry<String, String> side : textures.entrySet())
        {
            sb.append(newLine).append(indent).append(indent).append('"').append(side.getKey()).append("\": ").append('"').append(side.getValue()).append('"');
            if (c-- > 0)
            {
                sb.append(',');
            }
        }
        sb.append(newLine).append(indent).append('}').append(',');
        sb.append(newLine).append(indent).append("\"types\": [");
        for (int i = 0; i < types.length; i++)
        {
            sb.append(newLine).append(indent).append(indent).append('"').append(types[i]).append('"');
            if (i < types.length - 1)
            {
                sb.append(',');
            }
        }
        sb.append(newLine).append(indent).append(']');
        sb.append(newLine).append('}');
        return sb.toString();
    }
}

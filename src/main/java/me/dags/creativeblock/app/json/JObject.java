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

package me.dags.creativeblock.app.json;

import java.util.LinkedHashMap;
import java.util.Map;

public class JObject extends JValue
{
    public JObject()
    {
        super(new LinkedHashMap<String, JValue>());
    }

    public JObject add(String key, JValue value)
    {
        value = value == null ? JValue.empty : value;
        this.map().put(key, value);
        return this;
    }

    public JObject add(String key, Object value)
    {
        JValue jvalue = value == null ? JValue.empty : new JValue(value);
        this.map().put(key, jvalue);
        return this;
    }

    public JValue get(String key)
    {
        return this.map().get(key);
    }

    @Override
    public JObject asObject()
    {
        return this;
    }

    @Override
    public boolean isObject()
    {
        return true;
    }

    @Override
    public String toJson()
    {
        StringBuilder sb = new StringBuilder("{");
        Map<String, JValue> object = map();
        int i = object.size();
        for (Map.Entry<String, JValue> e : object.entrySet())
        {
            sb.append('"').append(e.getKey()).append('"').append(":").append(e.getValue().toJson());
            sb.append(--i > 0 ? "," : "");
        }
        return sb.append('}').toString();
    }

    protected void toJson(JFormatter formatter)
    {
        formatter.append("{").incIndent();
        Map<String, JValue> object = map();
        int i = object.size();
        for (Map.Entry<String, JValue> e : object.entrySet())
        {
            formatter.startLine("\"" + e.getKey() + "\": ");
            e.getValue().toJson(formatter);
            formatter.append((--i > 0 ? "," : ""));
        }
        formatter.decIndent().startLine("}");
    }
}

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

import java.util.ArrayList;
import java.util.List;

public class JArray extends JValue
{
    public JArray()
    {
        super(new ArrayList<JValue>());
    }

    public JArray add(JValue value)
    {
        value = value == null ? JValue.empty : value;
        this.list().add(value);
        return this;
    }

    public JArray add(Object value)
    {
        JValue jvalue = value == null ? JValue.empty : new JValue(value);
        this.list().add(jvalue);
        return this;
    }

    @Override
    public JArray asArray()
    {
        return this;
    }

    @Override
    public boolean isArray()
    {
        return true;
    }

    public int size()
    {
        return list().size();
    }

    public boolean containsType(Class<?>... type)
    {
        for (Class<?> c : type)
        {
            boolean result = false;
            for (JValue val : this.list())
            {
                if (c.equals(val.getClass()))
                {
                    result = true;
                    break;
                }
            }
            if (!result)
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toJson()
    {
        StringBuilder sb = new StringBuilder("[");
        List<JValue> list = list();
        int i = list.size();
        for (JValue value : list)
        {
            sb.append(value.toJson());
            sb.append(--i > 0 ? "," : "");
        }
        return sb.append(']').toString();
    }

    @Override
    protected void toJson(JFormatter formatter)
    {
        formatter.append("[").incIndent().startLine("");
        List<JValue> list = list();
        int i = list.size();
        for (JValue value : list)
        {
            value.toJson(formatter);
            if (--i > 0)
            {
                formatter.append(",");
                formatter.startLine("");
            }
        }
        formatter.decIndent().startLine("]");
    }
}

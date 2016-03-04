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

public class JFormatter
{
    private int indents = 0;
    private final StringBuilder sb = new StringBuilder();

    public String format(JValue value)
    {
        value.toJson(this);
        return sb.toString();
    }

    protected JFormatter startLine(String s)
    {
        sb.append("\n");
        indent();
        sb.append(s);
        return this;
    }

    protected JFormatter append(String s)
    {
        sb.append(s);
        return this;
    }

    protected JFormatter incIndent()
    {
        indents++;
        return this;
    }

    protected JFormatter decIndent()
    {
        indents--;
        return this;
    }

    protected void indent()
    {
        for (int i = indents; i > 0; i--)
        {
            sb.append("  ");
        }
    }
}

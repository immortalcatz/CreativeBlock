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

package me.dags.creativeblock.util;

/**
 * @author dags <dags@dags.me>
 */

public class NamingUtil
{
    public static String format(String in, String suffix)
    {
        return in.toLowerCase() + suffix.toLowerCase();
    }

    public static String langFormat(String in)
    {
        return capitalize(in.replace("_", " "));
    }

    public static String capitalize(String in)
    {
        char[] buf = new char[in.length()];
        boolean capitalize = true;
        for (int i = 0; i < in.length(); i++)
        {
            char c = in.charAt(i);
            if (capitalize && Character.isAlphabetic(c))
            {
                c = Character.toUpperCase(c);
            }
            capitalize = c == ' ' || c == '_' || c == '-';
            buf[i] = c;
        }
        if (in.startsWith("tile"))
        {
            buf[0] = 't';
        }
        return new String(buf);
    }
}

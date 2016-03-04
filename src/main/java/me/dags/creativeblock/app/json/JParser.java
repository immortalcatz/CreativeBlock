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

import java.io.IOException;
import java.io.InputStream;

public class JParser
{
    private final InputStream in;
    private char current = (char) -1;

    public JParser(InputStream input)
    {
        in = input;
    }

    public JValue parse()
    {
        try
        {
            return value();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return JValue.empty;
        }
    }

    private boolean hasNext() throws IOException
    {
        return in.available() > 0;
    }

    private void readNext() throws IOException
    {
        if (!hasNext()) throw new UnsupportedOperationException("END!");

        while (hasNext() && Character.isWhitespace(current = (char) in.read()))
        {}
    }

    private void readRaw() throws IOException
    {
        if (!hasNext()) throw new UnsupportedOperationException("END!");

        current = (char) in.read();
    }

    private void safeNext() throws IOException
    {
        while (hasNext() && Character.isWhitespace(current = (char) in.read()))
        {}
    }

    private void skipWhite() throws IOException
    {
        while (hasNext() && Character.isWhitespace(current))
        {
            current = (char) in.read();
        }
    }

    private void skip(int count) throws IOException
    {
        while (count > 0)
        {
            count--;
            readRaw();
        }
    }

    private JValue value() throws IOException
    {
        switch (current)
        {
            case '[':
                JArray array = readArray();
                //System.out.println("A: " + current);
                return array;
            case '{':
                JObject object = readObject();
                //System.out.println("O: " + current);
                return object;
            case '"':
                JValue string = new JValue(readString());
                //System.out.println("S: " + current);
                return string;
            case 'n':
            case 'N':
                skip(3);
                safeNext();
                JValue empty = JValue.empty;
                //System.out.println("Nu: " + current);
                return empty;
            case 'f':
            case 'F':
                skip(4);
                safeNext();
                JValue fal = new JValue(false);
                return fal;
            case 't':
            case 'T':
                skip(3);
                safeNext();
                JValue tru = new JValue(true);
                //System.out.println("T: " + current);
                return tru;
            default:
                if (current == '.' || current == '-' || Character.isDigit(current))
                {
                    JValue number = new JValue(readNumber());
                    //System.out.println("N: " + current);
                    return number;
                }
        }
        if (hasNext())
        {
            readNext();
            return value();
        }
        System.out.println("#### " + current);
        throw new UnsupportedOperationException("Parse error!");
    }

    private JObject readObject() throws IOException
    {
        safeNext();
        JObject object = new JObject();
        while (current != '}')
        {
            JValue key = value();
            skipWhite();

            JValue value = value();
            skipWhite();

            object.add(key.string(), value);
        }
        safeNext();
        return object;
    }

    private JArray readArray() throws IOException
    {
        safeNext();
        JArray array = new JArray();
        while (current != ']')
        {
            JValue value = value();
            array.add(value);
            skipWhite();
        }
        safeNext();
        return array;
    }

    private String readString() throws IOException
    {
        StringBuilder sb = new StringBuilder();
        boolean escaped = false;
        while (hasNext())
        {
            readRaw();
            if (current == '"' && !escaped)
            {
                break;
            }
            sb.append(current);
            escaped = current == '\\';
        }
        safeNext();
        return sb.toString();
    }

    private Number readNumber() throws IOException
    {
        long sign = current == '-' ? -1 : 1, num = 0, dec = 0, decplaces = 0;
        boolean skip = true;

        if (sign == 1 && current != '.')
        {
            num = current - '0';
        }

        while (hasNext())
        {
            // current is already start of number so skip to next now
            readRaw();

            if (!Character.isDigit(current) && current != '.')
            {
                skip = false;
                break;
            }

            if (current == '.')
            {
                if (decplaces != 0)
                {
                    return Double.NaN;
                }
                decplaces = 1;
                continue;
            }
            if (decplaces != 0)
            {
                dec = dec * 10 + current - '0';
                decplaces *= 10;
            }
            else
            {
                num = (num * 10) + current - '0';
            }
        }
        if (skip)
        {
            safeNext();
        }
        if (decplaces == 0)
        {
            return sign * num;
        }
        return sign * ((double) num + ((double) dec / (double) decplaces));
    }
}

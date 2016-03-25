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

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class JValue
{
    public static final JValue empty = new JValue();
    private final Object value;

    private JValue()
    {
        value = new Object();
    }

    public JValue(Object in)
    {
        value = in;
    }

    public String string()
    {
        return unEscapeString(value.toString());
    }

    public Number number()
    {
        return (Number) value;
    }

    public boolean bool()
    {
        return (Boolean) value;
    }

    public boolean isArray()
    {
        return false;
    }

    public boolean isObject()
    {
        return false;
    }

    public boolean isPrimitive()
    {
        return true;
    }

    public JArray asArray()
    {
        return null;
    }

    public JObject asObject()
    {
        return null;
    }

    @SuppressWarnings("unchecked")
    protected List<JValue> list()
    {
        return (List<JValue>) value;
    }

    @SuppressWarnings("unchecked")
    protected Map<String, JValue> map()
    {
        return (Map<String, JValue>) value;
    }

    public String toJson()
    {
        if (empty.equals(this))
        {
            return "null";
        }
        if (value instanceof String)
        {
            return '"' + escapeString(string()) + '"';
        }
        return value.toString();
    }

    public String toPrettyJson()
    {
        return new JFormatter().format(this);
    }

    protected void toJson(JFormatter formatter)
    {
        formatter.append(this.toJson());
    }

    public static String escapeString(String s)
    {
        StringBuilder b = new StringBuilder();
        for (char c : s.toCharArray())
        {
            if (c >= 128 || c == '"' || c == '\\')
                b.append("\\u").append(String.format("%04X", (int) c));
            else
                b.append(c);
        }
        return b.toString();
    }

    public static String unEscapeString(String st)
    {
        StringBuilder sb = new StringBuilder(st.length());
        for (int i = 0; i < st.length(); i++)
        {
            char ch = st.charAt(i);
            if (ch == '\\')
            {
                char nextChar = (i == st.length() - 1) ? '\\' : st.charAt(i + 1);
                if (nextChar >= '0' && nextChar <= '7')
                {
                    String code = "" + nextChar;
                    i++;
                    if ((i < st.length() - 1) && st.charAt(i + 1) >= '0' && st.charAt(i + 1) <= '7')
                    {
                        code += st.charAt(i + 1);
                        i++;
                        if ((i < st.length() - 1) && st.charAt(i + 1) >= '0' && st.charAt(i + 1) <= '7')
                        {
                            code += st.charAt(i + 1);
                            i++;
                        }
                    }
                    sb.append((char) Integer.parseInt(code, 8));
                    continue;
                }
                switch (nextChar)
                {
                    case '\\':
                        ch = '\\';
                        break;
                    case 'b':
                        ch = '\b';
                        break;
                    case 'f':
                        ch = '\f';
                        break;
                    case 'n':
                        ch = '\n';
                        break;
                    case 'r':
                        ch = '\r';
                        break;
                    case 't':
                        ch = '\t';
                        break;
                    case '\"':
                        ch = '\"';
                        break;
                    case '\'':
                        ch = '\'';
                        break;
                    case 'u':
                        if (i >= st.length() - 5)
                        {
                            ch = 'u';
                            break;
                        }
                        int code = Integer.parseInt("" + st.charAt(i + 2) + st.charAt(i + 3) + st.charAt(i + 4) + st.charAt(i + 5), 16);
                        sb.append(Character.toChars(code));
                        i += 5;
                        continue;
                }
                i++;
            }
            sb.append(ch);
        }
        return sb.toString();
    }

    public static JValue from(InputStream in)
    {
        return new JParser(new BufferedInputStream(in)).parse();
    }

    public static JValue fromJson(String jsonString)
    {
        try
        {
            return from(new ByteArrayInputStream(jsonString.getBytes("UTF-8")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return empty;
        }
    }

    public static JValue fromUrl(String urlAddress)
    {
        try
        {
            return from(new URL(urlAddress).openConnection().getInputStream());
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return empty;
        }
    }

    public static JValue fromFile(File file)
    {
        try
        {
            return from(new FileInputStream(file));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return empty;
        }
    }

    public static JValue fromPath(Path path)
    {
        try
        {
            return from(Files.newInputStream(path));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return empty;
        }
    }
}

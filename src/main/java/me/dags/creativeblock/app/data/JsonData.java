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

package me.dags.creativeblock.app.data;

import me.dags.creativeblock.app.conversion.replacefunction.ReplaceFunction;
import me.dags.creativeblock.app.util.DataProvider;
import me.dags.creativeblock.app.util.JsonDataProvider;

/**
 * @author dags <dags@dags.me>
 */

public class JsonData
{
    private static JsonDataProvider provider = new DataProvider();

    private final String data;
    private final String suffix;

    public JsonData(String data, String suffix)
    {
        this.data = data;
        this.suffix = suffix;
    }

    public String data()
    {
        return data;
    }

    public String suffix()
    {
        return suffix;
    }

    public <T> String replace(T info, ReplaceFunction<T> function)
    {
        return function.replace(data, info);
    }

    public static JsonData of(String path, String suffix)
    {
        return provider.of(path, suffix);
    }

    public static void setProvider(JsonDataProvider provider)
    {
        if (provider != null)
        {
            JsonData.provider = provider;
        }
    }
}

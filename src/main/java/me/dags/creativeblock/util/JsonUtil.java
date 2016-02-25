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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.Optional;

/**
 * @author dags <dags@dags.me>
 */

public class JsonUtil
{
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void serialize(File outFile, Object source)
    {
        try
        {
            if (!outFile.getParentFile().exists() && outFile.getParentFile().mkdirs()) {}
            if (!outFile.exists() && outFile.createNewFile()){}
            FileWriter writer = new FileWriter(outFile);
            writer.write(GSON.toJson(source));
            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static <T> T deserialize(JsonReader reader, Class<T> type) throws IOException
    {
        T t = GSON.fromJson(reader, type);
        reader.close();
        return t;
    }


    public static <T> Optional<T> deserialize(File inFile, Class<T> type)
    {
        try
        {
            JsonReader reader = new JsonReader(new FileReader(inFile));
            return Optional.of(deserialize(reader, type));
        }
        catch (FileNotFoundException e)
        {
            return Optional.empty();
        }
        catch (IOException e)
        {
            return Optional.empty();
        }
    }
}

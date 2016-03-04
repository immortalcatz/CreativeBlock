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

package me.dags.creativeblock.app.util;

import me.dags.creativeblock.app.data.JsonData;

import java.io.*;
import java.net.URL;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * @author dags <dags@dags.me>
 */

public class DataProvider implements JsonDataProvider
{
    private final String domain = "creativeblock";

    @Override
    public JsonData of(String location, String suffix)
    {
        location = location.replace(domain + ":", "assets/" + domain + "/");
        try
        {
            InputStream inputStream = getResource(location);
            if (inputStream != null)
            {
                StringBuilder lines = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                while (reader.ready())
                {
                    lines.append(reader.readLine()).append("\n");
                }
                lines.deleteCharAt(lines.lastIndexOf("\n"));
                return new JsonData(lines.toString(), suffix);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream getResource(String location) throws IOException
    {
        URL loc = DataProvider.class.getProtectionDomain().getCodeSource().getLocation();
        File file = new File(loc.getPath());
        if (file.isFile())
        {
            return zipResource(file, location);
        }
        else
        {
            return dirResource(file, location);
        }
    }

    public static InputStream zipResource(File jarFile, String path) throws IOException
    {
        JarFile jar = new JarFile(jarFile);
        ZipEntry entry = jar.getEntry(path);
        if (entry != null)
        {
            InputStream in = jar.getInputStream(entry);
            jar.close();
            return in;
        }
        jar.close();
        return null;
    }

    public static InputStream dirResource(File dir, String path) throws FileNotFoundException
    {
        File file = new File(dir, path);
        if (file.exists())
        {
            return new FileInputStream(file);
        }
        return null;
    }
}

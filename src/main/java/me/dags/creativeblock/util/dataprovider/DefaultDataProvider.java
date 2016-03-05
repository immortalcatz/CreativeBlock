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

package me.dags.creativeblock.util.dataprovider;

import me.dags.creativeblock.app.data.JsonData;

import java.io.*;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * @author dags <dags@dags.me>
 */

public class DefaultDataProvider implements DataProvider
{
    private final String domain = "creativeblock";

    @Override
    public JsonData of(String location, String suffix)
    {
        location = location.replace(domain + ":", "assets/" + domain + "/");
        try
        {
            File file = new File(DefaultDataProvider.class.getProtectionDomain().getCodeSource().getLocation().getFile());

            StringBuilder lines = new StringBuilder();
            InputStream inputStream;
            JarFile jar = null;

            if (file.isFile())
            {
                jar = new JarFile(file);
                inputStream = zipResource(jar, location);
            }
            else
            {
                inputStream = dirResource(file, location);
            }

            if (inputStream != null)
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                while (reader.ready())
                {
                    lines.append(reader.readLine()).append("\n");
                }
                int i = lines.lastIndexOf("\n");
                if (i != -1) lines.deleteCharAt(i);
                inputStream.close();
            }

            if (jar != null)
            {
                jar.close();
            }

            return new JsonData(lines.toString(), suffix);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream zipResource(JarFile jar, String path) throws IOException
    {
        ZipEntry entry = jar.getEntry(path);
        if (entry != null)
        {
            return jar.getInputStream(entry);
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

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

package me.dags.creativeblock.util.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * @author dags <dags@dags.me>
 */

public class DefaultLogger extends Handler implements CBLogger
{
    private final Logger logger = Logger.getLogger("CreativeBlock");
    private boolean open = false;
    private FileWriter fileWriter;

    public DefaultLogger()
    {
        logger.addHandler(this);
    }

    @Override
    public void info(String message, Object... args)
    {
        logger.log(Level.INFO, logify(message, args));
    }

    private String logify(String in, Object... args)
    {
        StringBuilder builder = new StringBuilder();
        for (int i = 0, count = 0; i < in.length(); i++)
        {
            char c = in.charAt(i);
            if (c == '{' && count < args.length && i + 1 < in.length() && in.charAt(i + 1) == '}')
            {
                builder.append(args[count++]);
                i++;
                continue;
            }
            builder.append(c);
        }
        return builder.toString();
    }

    @Override
    public void publish(LogRecord record)
    {
        try
        {
            if (!open)
            {
                File loc = new File(DefaultLogger.class.getProtectionDomain().getCodeSource().getLocation().getFile());
                File out = new File(loc.getParentFile(), "creativeblock.log");
                if (!out.exists() && out.createNewFile());
                fileWriter = new FileWriter(out);
                open = true;
            }
            fileWriter.write(record.getMessage());
            fileWriter.write("\n");
            fileWriter.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void flush()
    {
        if (fileWriter != null && open)
        {
            try
            {
                fileWriter.flush();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void close() throws SecurityException
    {
        if (fileWriter != null && open)
        {
            try
            {
                fileWriter.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}

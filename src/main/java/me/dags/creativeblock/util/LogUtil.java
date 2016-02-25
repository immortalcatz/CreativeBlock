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

import me.dags.creativeblock.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author dags <dags@dags.me>
 */

public class LogUtil
{
    private final Logger logger = LogManager.getLogger("CreativeBlock");
    private static final LogUtil instance = new LogUtil();

    private boolean logIo = true;
    private boolean logRegisters = true;
    private boolean logBlockpacks = true;

    private LogUtil()
    {}

    public static void setOptions(Config config)
    {
        instance.logIo = config.logIoOperations;
        instance.logRegisters = config.logRegisterOperations;
        instance.logBlockpacks = config.logBlockpackOperations;
    }

    public static void info(String message, Object... args)
    {
        instance.logger.info(message, args);
    }

    public static void info(Class<?> caller, String message, Object... args)
    {
        info("(" + caller.getSimpleName() + ") " + message, args);
    }

    public static void info(Object caller, String message, Object... args)
    {
        info(Class.class.isInstance(caller) ? Class.class.cast(caller) : caller.getClass(), message, args);
    }

    public static void register(Object caller, String message, Object... args)
    {
        if (instance.logRegisters) info(caller, message, args);
    }

    public static void io(Object caller, String message, Object... args)
    {
        if (instance.logIo) info(caller, message, args);
    }

    public static void blockpack(Object caller, String message, Object... args)
    {
        if (instance.logBlockpacks) info(caller, message, args);
    }
}

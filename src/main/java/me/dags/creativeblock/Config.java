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

package me.dags.creativeblock;

import me.dags.creativeblock.util.JsonUtil;
import me.dags.creativeblock.util.LogUtil;

import java.io.File;
import java.util.Optional;

/**
 * @author dags <dags@dags.me>
 */

public class Config
{
    public boolean logIoOperations = false;
    public boolean logRegisterOperations = false;
    public boolean logBlockpackOperations = false;
    public boolean enableDynmapSupport = true;


    public static Config load(File configDir, String modId)
    {
        LogUtil.info(Config.class, "Loading config from {}", configDir);
        File configFile = new File(configDir, modId + ".json");

        Optional<Config> optional = JsonUtil.deserialize(configFile, Config.class);
        Config config = optional.isPresent() ? optional.get() : new Config();
        JsonUtil.serialize(configFile, config);

        return config;
    }
}

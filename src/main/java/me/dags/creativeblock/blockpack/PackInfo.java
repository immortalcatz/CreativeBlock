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

package me.dags.creativeblock.blockpack;

/**
 * @author dags <dags@dags.me>
 */

public class PackInfo implements Versioned<PackInfo>
{
    public final String name;
    public final String version;
    public final String friendlyVersion;

    public PackInfo(String name, String version, String friendlyVersion)
    {
        this.name = name;
        this.version = version;
        this.friendlyVersion = friendlyVersion;
    }

    @Override
    public PackInfo latest(PackInfo info)
    {
        int result = compareTo(info);
        return result > 0 ? this : result < 0 ? info : this;
    }

    @Override
    public int compareTo(PackInfo info)
    {
        String s = version;
        String s2 = info.version;
        return s.compareTo(s2);
    }

    @Override
    public String toString()
    {
        return name + ":" + friendlyVersion;
    }

    public static PackInfo from(String in)
    {
        String name = in = in.replace(".zip", "").replace("[converted]", "");

        int index = 0;
        for (; index < name.length(); index++)
        {
            if (Character.isDigit(name.charAt(index)))
            {
                name = name.substring(0, Character.isAlphabetic(in.charAt(index - 1)) ? index : index - 1);
                break;
            }
        }

        StringBuilder major = new StringBuilder();
        StringBuilder minor = new StringBuilder();
        StringBuilder patch = new StringBuilder();
        StringBuilder current = major;

        for (; index < in.length(); index++)
        {
            char c = in.charAt(index);
            if (c == '.')
            {
                if (minor.length() == 0) current = minor;
                else if (patch.length() == 0) current = patch;
                else current = new StringBuilder();
            }
            else if (Character.isDigit(c))
            {
                current.append(c);
            }
        }

        String version;
        String friendlyVersion;

        if (major.length() == 0) major.append(0);
        if (minor.length() == 0) minor.append(0);
        if (patch.length() == 0) patch.append(0);
        friendlyVersion = major.toString() + "." + minor.toString() + "." + patch.toString();

        while (major.length() < 5) major.insert(0, 0);
        while (minor.length() < 5) minor.insert(0, 0);
        while (patch.length() < 5) patch.insert(0, 0);
        version = major.append(".").append(minor).append(".").append(patch).toString();

        return new PackInfo(name, version, friendlyVersion);
    }
}

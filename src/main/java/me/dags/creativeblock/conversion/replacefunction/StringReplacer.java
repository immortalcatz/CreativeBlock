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

package me.dags.creativeblock.conversion.replacefunction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dags <dags@dags.me>
 */

public class StringReplacer implements ReplaceFunction<String>
{
    private final List<Match> matches = new ArrayList<Match>();

    public StringReplacer with(String match)
    {
        return with(match, Match.insert);
    }

    public StringReplacer with(String match, String insert)
    {
        matches.add(new Match(match, insert));
        return this;
    }

    @Override
    public String replace(String data, String in)
    {
        for (Match match : matches)
        {
            data = data.replace(match.replace, match.insert(in));
        }
        return data;
    }

    public static StringReplacer get()
    {
        return new StringReplacer();
    }

    public static StringReplacer of(String s)
    {
        return new StringReplacer().with(s);
    }

    public static StringReplacer of(String match, String insert)
    {
        return new StringReplacer().with(match, insert);
    }

    public static class Match
    {
        private static final String insert = "#insert";

        private final String replace;
        private final String with;

        public Match(String s, String s1)
        {
            replace = s;
            with = s1;
        }

        public String insert(String s)
        {
            return with.replace(insert, s);
        }
    }
}

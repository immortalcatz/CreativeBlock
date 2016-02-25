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

import me.dags.creativeblock.util.NamingUtil;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.*;

/**
 * @author dags <dags@dags.me>
 */

public class Tabs extends CreativeTabs
{
    private static final Map<String, Tabs> tabs = new HashMap<String, Tabs>();

    private final Item icon;

    private Tabs(String label, Block block, int id)
    {
        super(id, label);
        this.icon = Item.getItemFromBlock(block);
    }

    @Override
    public Item getTabIconItem()
    {
        return icon;
    }

    public static CreativeTabs tabFor(String tabName, Block block)
    {
        Tabs tab = tabs.get(tabName);
        if (tab == null)
        {
            tabs.put(tabName, tab = new Tabs(tabName, block, CreativeTabs.creativeTabArray.length));
        }
        return tab;
    }

    public static List<String> getLang()
    {
        List<String> list = new ArrayList<String>();
        for (Map.Entry<String, Tabs> e : tabs.entrySet())
        {
            String key = e.getValue().getTranslatedTabLabel();
            String name = NamingUtil.langFormat(e.getKey());
            list.add(key + "=" + name);
        }
        Collections.sort(list);
        return list;
    }
}

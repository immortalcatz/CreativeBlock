package me.dags.creativeblock.definition;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author dags <dags@dags.me>
 */

public class BlockTextures
{
    private static final Map<String, Function<BlockTextures, String>> sidesMap = map();

    static Map<String, Function<BlockTextures, String>> map()
    {
        Map<String, Function<BlockTextures, String>> map = new HashMap<>();
        map.put("#down", BlockTextures::bottom);
        map.put("#bottom", BlockTextures::bottom);
        map.put("#up", BlockTextures::top);
        map.put("#top", BlockTextures::top);
        map.put("#side", BlockTextures::sides);
        map.put("#all", BlockTextures::normal);
        map.put("#north", BlockTextures::sides);
        map.put("#east", BlockTextures::sides);
        map.put("#south", BlockTextures::sides);
        map.put("#west", BlockTextures::sides);
        return map;
    }

    private String normal;
    private String top;
    private String bottom;
    private String sides;

    public BlockTextures(String s1, String s2, String s3, String s4)
    {
        normal = s1 == null ? "" : s1;
        top = s2 == null ? "" : s2;
        bottom = s3 == null ? "" : s3;
        sides = s4 == null ? "" : s4;
    }

    public BlockTextures(Map<String, String> map)
    {
        normal = map.containsKey("normal") ? map.get("normal") : "";
        top = map.containsKey("top") ? map.get("top") : "";
        bottom = map.containsKey("bottom") ? map.get("bottom") : "";
        sides = map.containsKey("sides") ? map.get("sides") : "";
    }

    public boolean valid()
    {
        return !normal.isEmpty() || (!top.isEmpty() && !bottom.isEmpty() && !sides.isEmpty());
    }

    private String normal()
    {
        return normal;
    }

    private String top()
    {
        return top.isEmpty() ? normal : top;
    }

    private String bottom()
    {
        return bottom.isEmpty() ? normal : bottom;
    }

    private String sides()
    {
        return sides.isEmpty() ? normal : sides;
    }

    public String get(String id)
    {
        return sidesMap.get(id).apply(this);
    }
}

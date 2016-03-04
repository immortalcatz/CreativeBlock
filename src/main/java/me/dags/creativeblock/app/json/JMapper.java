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

package me.dags.creativeblock.app.json;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JMapper
{
    private static final Map<Class<?>, Function<JValue, Object>> fromAdapters = fromAdapters();

    private static Map<Class<?>, Function<JValue, Object>> fromAdapters()
    {
        Map<Class<?>, Function<JValue, Object>> adapters = new HashMap<>();
        adapters.put(byte.class, v -> v.number().byteValue());
        adapters.put(Byte.class, adapters.get(byte.class));
        adapters.put(boolean.class, v -> v.bool());
        adapters.put(Boolean.class, adapters.get(boolean.class));
        adapters.put(char.class, v -> v.string().charAt(0));
        adapters.put(Character.class, adapters.get(char.class));
        adapters.put(double.class, v -> v.number().doubleValue());
        adapters.put(Double.class, adapters.get(double.class));
        adapters.put(float.class, v -> v.number().floatValue());
        adapters.put(Float.class, adapters.get(float.class));
        adapters.put(int.class, v -> v.number().intValue());
        adapters.put(Integer.class, adapters.get(int.class));
        adapters.put(long.class, v -> v.number().floatValue());
        adapters.put(Long.class, adapters.get(long.class));
        adapters.put(short.class, v -> v.number().shortValue());
        adapters.put(Short.class, adapters.get(short.class));
        adapters.put(String.class, v -> v.string());
        return Collections.unmodifiableMap(adapters);
    }

    public static <T> T fromJson(JValue data, Class<T> type)
    {
        try
        {
            return type.cast(from(data, type));
        }
        catch (Throwable t)
        {
            t.printStackTrace();
            return null;
        }
    }

    private static Object from(JValue data, Class<?> type) throws InstantiationException, IllegalAccessException
    {
        if (data.isObject())
        {
            Object o = type.newInstance();
            populate(o, data.asObject());
            return o;
        }
        if (data.isArray())
        {
            if (type.isArray())
            {
                return toArray(type.getComponentType(), data.asArray());
            }
        }
        Function<JValue, Object> adapter = fromAdapters.get(type);
        if (adapter != null)
        {
            return adapter.apply(data);
        }
        return null;
    }

    private static void populate(Object object, JObject data) throws IllegalArgumentException, IllegalAccessException, InstantiationException
    {
        Class<?> c = object.getClass();
        do
        {
            for (Field f : c.getDeclaredFields())
            {
                JValue jval = data.get(f.getName());
                if (jval == null)
                {
                    continue;
                }
                Object value = from(jval, f.getType());
                f.setAccessible(true);
                f.set(object, value);
            }
            c = c.getSuperclass();
        }
        while (!c.equals(Object.class));
    }

    private static Object toArray(Class<?> type, JArray jarray) throws InstantiationException, IllegalAccessException
    {
        Object array = Array.newInstance(type, jarray.size());
        int index = 0;
        for (JValue value : jarray.list())
        {
            Object val = from(value, type);
            Array.set(array, index++, val);
        }
        return array;
    }




    private static final Map<Class<?>, Function<Object, JValue>> toAdapters = toAdapters();

    private static Map<Class<?>, Function<Object, JValue>> toAdapters()
    {
        Map<Class<?>, Function<Object, JValue>> adapters = new HashMap<>();
        adapters.put(byte.class, o -> new JValue(o));
        adapters.put(Byte.class, adapters.get(byte.class));
        adapters.put(boolean.class, adapters.get(byte.class));
        adapters.put(Boolean.class, adapters.get(byte.class));
        adapters.put(char.class, o -> new JValue(o.toString()));
        adapters.put(Character.class, adapters.get(char.class));
        adapters.put(double.class, adapters.get(byte.class));
        adapters.put(Double.class, adapters.get(byte.class));
        adapters.put(float.class, adapters.get(byte.class));
        adapters.put(Float.class, adapters.get(byte.class));
        adapters.put(int.class, adapters.get(byte.class));
        adapters.put(Integer.class, adapters.get(byte.class));
        adapters.put(long.class, adapters.get(byte.class));
        adapters.put(Long.class, adapters.get(byte.class));
        adapters.put(short.class, adapters.get(byte.class));
        adapters.put(Short.class, adapters.get(byte.class));
        adapters.put(String.class, adapters.get(byte.class));
        return Collections.unmodifiableMap(adapters);
    }

    public static JValue toJson(Object object)
    {
        try
        {
            return toJValue(object);
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
        return JValue.empty;
    }

    private static JValue toJValue(Object object) throws IllegalArgumentException, IllegalAccessException
    {
        if (object == null)
        {
            return JValue.empty;
        }
        if (Collection.class.isInstance(object))
        {
            object = ((Collection<?>) object).toArray();
        }
        if (object.getClass().isArray())
        {
            return toJArray(object);
        }
        Function<Object, JValue> adapter = toAdapters.get(object.getClass());
        if (adapter == null)
        {
            return toJObject(object);
        }
        return adapter.apply(object);
    }

    private static JArray toJArray(Object in) throws ArrayIndexOutOfBoundsException, IllegalArgumentException, IllegalAccessException
    {
        JArray array = new JArray();
        for (int i = 0; i < Array.getLength(in); i++)
        {
            JValue val = toJValue(Array.get(in, i));
            array.add(val);
        }
        return array;
    }

    private static JObject toJObject(Object in) throws IllegalArgumentException, IllegalAccessException
    {
        JObject object = new JObject();
        Class<?> c = in.getClass();
        do
        {
            for (Field f : c.getDeclaredFields())
            {
                f.setAccessible(true);
                String name = f.getName();
                JValue value = toJValue(f.get(in));
                object.add(name, value);
            }
            c = c.getSuperclass();
        }
        while (!c.equals(Object.class));
        return object;
    }
}

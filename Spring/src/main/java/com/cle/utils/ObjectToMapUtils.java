package com.cle.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectToMapUtils {

    public static Map<String, Object> convertObjToMap(Object obj) throws Exception{

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] fields = obj.getClass().getDeclaredFields();

        for(int i = 0; i < fields.length; i++){

            Field field = obj.getClass().getDeclaredField(fields[i].getName());
            field.setAccessible(true);
            Object value = field.get(obj);

            map.put(fields[i].getName(), value);
        }

        return map;
    }

}
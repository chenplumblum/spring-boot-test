package com.plumblum.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author chenpeibin
 * @version 1.0
 * @date 2020/3/27 16:12
 */
public class AbstractObject {
    public <T> T clone(Class<T> clazz, Integer cloneDirection) {
        try {

            // 先完成基本字段的浅克隆
            T target = clazz.newInstance();
            BeanCopierUtils.copyProperties(this, target);

            // 完成内部的AbstractObject、List<ObjectObject>类型字段的深度克隆
            Class<?> thisClazz = this.getClass();
            Field[] fields = thisClazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);

                // 如果判断某个字段是List类型的，Set,Map
                if (field.getType() == List.class) {
                    List<?> list = (List<?>) field.get(this);
                    if (list == null || list.size() == 0) {
                        continue;
                    }

                    // 获取List集合中的泛型类型
                    Field targetField = null;
                    try {
                        targetField = clazz.getDeclaredField(field.getName());
                    } catch(NoSuchFieldException e) {
                        continue;
                    }
                    if(targetField != null) {
                        Class<?> cloneTargetClazz = getListGenericType(targetField);
                        // 获取要克隆的目标类型
                        //Class<?> cloneTargetClazz = getCloneTargetClazz(listGenericClazz, cloneDirection);
                        // 将list集合克隆到目标list集合中去
                        List clonedList = new ArrayList();
                        cloneList(list, clonedList, cloneTargetClazz, cloneDirection);

                        // 获取设置克隆好的list的方法名称
                        Method setFieldMethod = getSetCloneFieldMethodName(field, clazz);
                        setFieldMethod.invoke(target, clonedList);
                    }

                } else if(field.getType() == Set.class){
                    Set<?> set = (Set<?>) field.get(this);
                    // 获取List集合中的泛型类型
                    Field targetField = null;
                    try {
                        targetField = clazz.getDeclaredField(field.getName());
                    } catch(NoSuchFieldException e) {
                        continue;
                    }
                    if(targetField != null) {
                        Class<?> cloneTargetClazz = getListGenericType(targetField);
                        // 获取要克隆的目标类型
                        //Class<?> cloneTargetClazz = getCloneTargetClazz(listGenericClazz, cloneDirection);
                        // 将list集合克隆到目标list集合中去
                        Set clonedSet = new HashSet();
                        cloneSet(set, clonedSet, cloneTargetClazz, cloneDirection);

                        // 获取设置克隆好的clonedSet的方法名称
                        Method setFieldMethod = getSetCloneFieldMethodName(field, clazz);
                        setFieldMethod.invoke(target, clonedSet);
                    }
                } else if(field.getType() == Map.class){
                    Map<?,?> map = (Map<?, ?>) field.get(this);
                    // 获取List集合中的泛型类型
                    Field targetField = null;
                    try {
                        targetField = clazz.getDeclaredField(field.getName());
                    } catch(NoSuchFieldException e) {
                        continue;
                    }
                    if(targetField != null) {
                        Class<?> test = getListGenericType(targetField);
                        Class<?> cloneTargetClazz = getListGenericType(targetField);
                        // 获取要克隆的目标类型
                        // 将list集合克隆到目标list集合中去
                        HashMap clonedMap = new HashMap<>();
                        // TODO: 2020/3/27  
                        cloneMap(map, clonedMap, cloneTargetClazz, cloneDirection);

                        // 获取设置克隆好的clonedSet的方法名称
                        Method setFieldMethod = getSetCloneFieldMethodName(field, clazz);
                        setFieldMethod.invoke(target, clonedMap);
                    }
                }else {
                    Class<?> sourceFeildClazz = field.getType();
                    if (sourceFeildClazz == String.class || sourceFeildClazz == Long.class
                            || sourceFeildClazz.toString().equals("long") || field.getType() == Integer.class
                            || sourceFeildClazz.toString().equals("int") || sourceFeildClazz == Short.class
                            || sourceFeildClazz.toString().equals("short") || sourceFeildClazz == Double.class
                            || sourceFeildClazz.toString().equals("double") || sourceFeildClazz == Float.class
                            || sourceFeildClazz.toString().equals("float") || sourceFeildClazz == BigDecimal.class
                            || sourceFeildClazz == Boolean.class || sourceFeildClazz.toString().equals("boolean")
                            || sourceFeildClazz == Date.class || sourceFeildClazz == Character.class
                            || sourceFeildClazz.toString().equals("char") || sourceFeildClazz == Byte.class
                            || sourceFeildClazz.toString().equals("byte") || sourceFeildClazz == java.sql.Date.class) {
                        continue;
                    }
                    // 判断某个字段是否AbstractObject类型的
                    try {
                        if (!(field.getType().newInstance() instanceof AbstractObject)) {
                            continue;
                        }
                    } catch (Exception e) {
                        if (e instanceof InstantiationException) {
                            continue;
                        }
                        throw new RuntimeException("error", e);
                    }
                    AbstractObject sourceObj = (AbstractObject) (field.get(this));
                    if (sourceObj == null) {
                        continue;
                    }

                    // 获取要克隆的目标类型
                    //Class<?> cloneTargetClazz = getCloneTargetClazz(field.getType(), cloneDirection);
                    Field targetField = null;
                    try {
                        targetField = clazz.getDeclaredField(field.getName());
                    } catch(NoSuchFieldException e) {
                        continue;
                    }
                    if(targetField != null) {
                        Class<?> cloneTargetClazz = targetField.getType();
                        AbstractObject clonedObj = (AbstractObject) sourceObj.clone(cloneTargetClazz, cloneDirection);
                        // 获取设置克隆好的对象的方法名称
                        Method setFieldMethod = getSetCloneFieldMethodName(field, clazz);
                        setFieldMethod.invoke(target, clonedObj);
                    }
                }

            }

            return target;
        } catch (Exception e) {
            throw new RuntimeException("error", e);
        }
    }

    /**
     * 获取List集合的泛型类型
     *
     * @param field
     * @return
     */
    private Class<?> getListGenericType(Field field) {
        Type genericType = field.getGenericType();
        if (genericType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            return (Class<?>) parameterizedType.getActualTypeArguments()[0];
        }
        return null;
    }

    /**
     * 获取List集合的泛型类型
     *
     * @param field
     * @return
     */
    private ParameterizedType parameterizedType(Field field) {
        Type genericType = field.getGenericType();
        if (genericType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            return parameterizedType;
        }
        return null;
    }

    /**
     * 将一个List克隆到另外一个List
     *
     * @param sourceList
     * @param targetList
     * @param cloneTargetClazz
     * @param cloneDirection
     */
    private void cloneList(List sourceList, List targetList, Class cloneTargetClazz, Integer cloneDirection) {
        for (Object object : sourceList) {
            if(object instanceof AbstractObject) {
                AbstractObject targetObject = (AbstractObject) object;
                AbstractObject clonedObject = (AbstractObject) targetObject.clone(cloneTargetClazz, cloneDirection);
                targetList.add(clonedObject);
            } else {
                // 非List<? extends AbstractObject>类型的集合字段，直接复用原对象的字段值
                targetList.add(object);
            }
        }
    }

    /**
     * 将一个Set克隆到另外一个Set
     *
     * @param sourceList
     * @param targetList
     * @param cloneTargetClazz
     * @param cloneDirection
     */
    private void cloneSet(Set sourceList, Set targetList, Class cloneTargetClazz, Integer cloneDirection) {
        for (Object object : sourceList) {
            if(object instanceof AbstractObject) {
                AbstractObject targetObject = (AbstractObject) object;
                AbstractObject clonedObject = (AbstractObject) targetObject.clone(cloneTargetClazz, cloneDirection);
                targetList.add(clonedObject);
            } else {
                // 非List<? extends AbstractObject>类型的集合字段，直接复用原对象的字段值
                targetList.add(object);
            }
        }
    }

    /**
     * 将一个Map克隆到另外一个Map
     *
     * @param sourceList
     * @param targetList
     * @param cloneTargetClazz
     * @param cloneDirection
     */
    private void cloneMap(Map sourceList, Map targetList, Class cloneTargetClazz, Integer cloneDirection) {

    }

    /**
     * 获取设置克隆好的对象或List属性的方法名称
     *
     * @param field
     * @param clazz
     * @return
     */
    private Method getSetCloneFieldMethodName(Field field, Class<?> clazz) {
        String name = field.getName();
        String setMethodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);

        Method setFieldMethod = null;

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getName().equals(setMethodName)) {
                setFieldMethod = method;
                break;
            }
        }

        return setFieldMethod;
    }
}

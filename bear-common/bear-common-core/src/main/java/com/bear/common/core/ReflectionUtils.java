package com.bear.common.core;

import com.google.common.collect.Lists;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by mby on 2019/4/17.
 */
public class ReflectionUtils {

    /**
     * 对象中的属性属否全部为空
     *
     * @param obj
     */
    public static boolean isEmpty(Object obj) {
        Class<?> objClass = obj.getClass();
        Method[] objmethods = objClass.getDeclaredMethods();
        Map<String, Method> objMeMap = new HashMap<String, Method>();
        for (int i = 0; i < objmethods.length; i++) {
            Method method = objmethods[i];
            objMeMap.put(method.getName(), method);
        }
        for (int i = 0; i < objmethods.length; i++) {
            String methodName = objmethods[i].getName();
            if (methodName != null && methodName.startsWith("get")) {
                try {
                    Object returnObj = objmethods[i].invoke(obj, new Object[0]);
                    if (returnObj != null) {
                        return false;
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;

    }

    /**
     * 清空对象
     *
     * @param obj
     */
    public static void clear(Object obj) {

        Class<?> objClass = obj.getClass();
        Method[] objmethods = objClass.getDeclaredMethods();
        Map<String, Method> objMeMap = new HashMap<String, Method>();
        for (int i = 0; i < objmethods.length; i++) {
            Method method = objmethods[i];
            objMeMap.put(method.getName(), method);
        }
        for (int i = 0; i < objmethods.length; i++) {
            String methodName = objmethods[i].getName();
            if (methodName != null && methodName.startsWith("get")) {
                try {
                    Object returnObj = objmethods[i].invoke(obj, new Object[0]);
                    Method setmethod = (Method) objMeMap.get("set" + methodName.split("get")[1]);
                    if (returnObj != null) {
                        returnObj = null;
                    }
                    setmethod.invoke(obj, returnObj);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数.
     */
    public static Object getFieldValue(final Object object, final String fieldName) {
        Field field = getDeclaredField(object, fieldName);

        if (field == null)
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");

        makeAccessible(field);

        Object result = null;
        try {
            result = field.get(object);
        } catch (IllegalAccessException e) {
            System.err.println("不可能抛出的异常{}"+ e.getMessage());
        }
        return result;
    }

    /**
     * 直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.
     */
    public static void setFieldValue(final Object object, final String fieldName, final Object value) {
        Field field = getDeclaredField(object, fieldName);

        if (field == null)
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");

        makeAccessible(field);

        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            System.err.println("不可能抛出的异常:{}"+ e.getMessage());
        }
    }

    /**
     * 直接调用对象方法, 无视private/protected修饰符.
     */
    public static Object invokeMethod(final Object object, final String methodName, final Class<?>[] parameterTypes, final Object[] parameters) {
        Method method = getDeclaredMethod(object, methodName, parameterTypes);
        if (method == null)
            throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + object + "]");

        method.setAccessible(true);

        try {
            return method.invoke(object, parameters);
        } catch (Exception e) {
            throw convertReflectionExceptionToUnchecked(e);
        }
    }

    /**
     * 循环向上转型, 获取对象的DeclaredField.
     *
     * 如向上转型到Object仍无法找到, 返回null.
     */
    protected static Field getDeclaredField(final Object object, final String fieldName) {
        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                // Field不在当前类定义,继续向上转型
            }
        }
        return null;
    }

    /**
     * 强行设置Field可访问.
     */
    protected static void makeAccessible(final Field field) {
        if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
            field.setAccessible(true);
        }
    }

    /**
     * 循环向上转型,获取对象的DeclaredMethod.
     *
     * 如向上转型到Object仍无法找到, 返回null.
     */
    protected static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes) {
        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException e) {
                // Method不在当前类定义,继续向上转型
            }
        }
        return null;
    }

    /**
     * 通过反射,获得Class定义中声明的父类的泛型参数的类型. 如无法找到, 返回Object.class. eg. public UserDao extends HibernateDao<User>
     *
     * @param clazz
     *            The class to introspect
     * @return the first generic declaration, or Object.class if cannot be determined
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getSuperClassGenricType(final Class<?> clazz) {
        return (Class<T>) getSuperClassGenricType(clazz, 0);
    }

    /**
     * 通过反射,获得定义Class时声明的父类的泛型参数的类型. 如无法找到, 返回Object.class.
     *
     * 如public UserDao extends HibernateDao<User,Long>
     *
     * @param clazz
     *            clazz The class to introspect
     * @param index
     *            the Index of the generic ddeclaration,start from 0.
     * @return the index generic declaration, or Object.class if cannot be determined
     */
    public static Class<?> getSuperClassGenricType(final Class<?> clazz, final int index) {

        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            System.out.println(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            System.out.println("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            System.out.println(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
            return Object.class;
        }

        return (Class<?>) params[index];
    }

    /**
     * 提取集合中的对象的属性(通过getter函数), 组合成List.
     *
     * @param collection
     *            来源集合.
     * @param propertyName
     *            要提取的属性名.
     */
    public static List<Object> convertElementPropertyToList(final Collection<?> collection, final String propertyName) {
        List<Object> list = new ArrayList<Object>();

        try {
            for (Object obj : collection) {
                list.add(PropertyUtils.getProperty(obj, propertyName));
            }
        } catch (Exception e) {
            throw convertReflectionExceptionToUnchecked(e);
        }

        return list;
    }

    /**
     * 提取集合中的对象的属性(通过getter函数), 组合成由分割符分隔的字符串.
     *
     * @param collection
     *            来源集合.
     * @param propertyName
     *            要提取的属性名.
     * @param separator
     *            分隔符.
     */
    public static String convertElementPropertyToString(final Collection<?> collection, final String propertyName, final String separator) {
        List<Object> list = convertElementPropertyToList(collection, propertyName);
        return StringUtils.join(list, separator);
    }

    /**
     * 转换字符串类型到clazz的property类型的值.
     *
     * @param value
     *            待转换的字符串
     * @param toType
     *            提供类型信息的Class
     */
    public static Object convertValue(Object value, Class<?> toType) {
        try {
            DateConverter dc = new DateConverter();
            dc.setUseLocaleFormat(true);
            dc.setPatterns(new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" });
            ConvertUtils.register(dc, Date.class);
            return ConvertUtils.convert(value, toType);
        } catch (Exception e) {
            throw convertReflectionExceptionToUnchecked(e);
        }
    }

    /**
     * 将反射时的checked exception转换为unchecked exception.
     */
    public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
        if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException || e instanceof NoSuchMethodException)
            return new IllegalArgumentException("Reflection Exception.", e);
        else if (e instanceof InvocationTargetException)
            return new RuntimeException("Reflection Exception.", ((InvocationTargetException) e).getTargetException());
        else if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        }
        return new RuntimeException("Unexpected Checked Exception.", e);
    }

    public static void RandData(Object obj) {

        Class<?> objClass = obj.getClass();
        Method[] objmethods = objClass.getDeclaredMethods();

        Map<String, Method> objMeMap = new HashMap<String, Method>();
        for (int i = 0; i < objmethods.length; i++) {
            Method method = objmethods[i];
            objMeMap.put(method.getName(), method);
        }
        for (int i = 0; i < objmethods.length; i++) {
            String methodName = objmethods[i].getName();
            if (methodName != null && methodName.startsWith("get")) {
                try {
                    Method getmethod = objMeMap.get(methodName);
                    String fieldName = methodName.split("get")[1];
                    if(Lists.newArrayList("id","del","createDate","updateDate").contains(fieldName)){
                        continue;
                    }
                    Object returnObj = objmethods[i].invoke(obj, new Object[0]);
                    Method setmethod = objMeMap.get("set" + fieldName);
                    Class<?> returnType = getmethod.getReturnType();
                    if (returnType.isEnum()) {
                        returnObj = Arrays.asList(returnType.getEnumConstants()).get(0);
                    }
                    if (returnType.getName().equals(Boolean.class.getName())) {
                        returnObj = RandomUtils.nextBoolean();
                    }
                    if (returnType.getName().equals(String.class.getName())) {
                        returnObj = RandomStringUtils.randomAlphabetic(10);
                    }

                    if (returnType.getName().equals(Double.class.getName())) {
                        returnObj = RandomUtils.nextDouble();
                    }
                    if (returnType.getName().equals(Float.class.getName())) {
                        returnObj = RandomUtils.nextFloat();
                    }
                    if (returnType.getName().equals(Integer.class.getName())) {
                        returnObj = RandomUtils.nextInt();
                    }
                    if (returnType.getName().equals(Long.class.getName())) {

                        returnObj = RandomUtils.nextLong();
                    }
                    if (returnType.getName().equals(BigInteger.class.getName())) {

                        returnObj = new BigDecimal(RandomUtils.nextInt());
                    }
                    if (returnType.getName().equals(BigDecimal.class.getName())) {

                        returnObj = new BigDecimal(RandomUtils.nextDouble());
                    }
                    if (returnType.getName().equals(Date.class.getName())) {
                        returnObj = new Date();
                    }
                    if (returnObj != null) {
                        setmethod.invoke(obj, returnObj);
                    }
                } catch (Exception e) {}
            }
        }
    }
}

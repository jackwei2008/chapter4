package com.smart.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTest {
    public static void genCar() {

        try {
            Car car = null;
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Class clazz = null;
            Constructor constructor = null;
            Method method = null;

            clazz = classLoader.loadClass("com.smart.reflect.Car");
//            constructor = clazz.getDeclaredConstructor((Class[])null);//这样写也可以
            constructor = clazz.getDeclaredConstructor();
            car = (Car) constructor.newInstance();

            Constructor constructor1 = clazz.getDeclaredConstructor(String.class, String.class, int.class);
            Car car1 = (Car) constructor1.newInstance("奔驰", "red", 400);

            Field field = clazz.getDeclaredField("brand");
            field.setAccessible(true);
            field.set(car, "丰田");

            Method method1 = clazz.getDeclaredMethod("setMaxSpeed", int.class);
            method1.invoke(car, 200);

            Method method2 = clazz.getDeclaredMethod("setColor", String.class);
            method2.invoke(car, "yellow");

            method = clazz.getDeclaredMethod("run");
            method.invoke(car);
            method.invoke(car1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        genCar();
    }
}

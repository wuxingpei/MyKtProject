package com.example.myapplication.utils;

import android.content.Context;
import android.os.IBinder;

import com.example.myapplication.entity.UserCollectDetail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
//https://www.jianshu.com/p/9be58ee20dee
public class ReflectClassJava {

    // 创建对象
    public static void reflectNewInstance() {
        try {
            Class<?> classUserCollectDetail = Class.forName("com.example.myapplication.entity.UserCollectDetail"); //笔记 forName根据类名返回类的对象
            Object objectBook = classUserCollectDetail.newInstance();//笔记 newInstance创建类的实例
            UserCollectDetail userCollectDetail = (UserCollectDetail) objectBook;
            userCollectDetail.setTitle("Android进阶之光");
            userCollectDetail.setAuthor("刘望舒");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 反射私有的构造方法
    public static void reflectPrivateConstructor() {
        try {
            Class<?> classUserCollectDetail = Class.forName("com.example.myapplication.entity.UserCollectDetail");
            Constructor<?> declaredConstructorBook = classUserCollectDetail.getDeclaredConstructor(String.class, String.class);//笔记 getDeclaredConstructor获得该类中与参数类型匹配的构造方法
            declaredConstructorBook.setAccessible(true);
            Object objectBook = declaredConstructorBook.newInstance("Android开发艺术探索", "任玉刚");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 反射私有属性
    public static void reflectPrivateField() {
        try {
            Class<?> classUserCollectDetail = Class.forName("com.example.myapplication.entity.UserCollectDetail");
            Object objectBook = classUserCollectDetail.newInstance();
            Field fieldTag = classUserCollectDetail.getDeclaredField("TAG");//笔记 getDeclaredField获得某个属性对象
            fieldTag.setAccessible(true);
            String tag = (String) fieldTag.get(objectBook);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 反射私有方法
    public static void reflectPrivateMethod() {
        try {
            Class<?> classUserCollectDetail = Class.forName("com.example.myapplication.entity.UserCollectDetail");
            Method methodBook = classUserCollectDetail.getDeclaredMethod("declaredMethod", int.class);//笔记 getDeclaredMethod获得该类某个方法
            methodBook.setAccessible(true);
            Object objectBook = classUserCollectDetail.newInstance();
            String string = (String) methodBook.invoke(objectBook, 0);//笔记 invoke传递object对象及参数调用该对象对应的方法

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 获得系统Zenmode值
    public static int getZenMode() {
        int zenMode = -1;
        try {
            Class<?> cServiceManager = Class.forName("android.os.ServiceManager");
            Method mGetService = cServiceManager.getMethod("getService", String.class);
            Object oNotificationManagerService = mGetService.invoke(null, Context.NOTIFICATION_SERVICE);
            Class<?> cINotificationManagerStub = Class.forName("android.app.INotificationManager$Stub");
            Method mAsInterface = cINotificationManagerStub.getMethod("asInterface", IBinder.class);
            Object oINotificationManager = mAsInterface.invoke(null, oNotificationManagerService);
            Method mGetZenMode = cINotificationManagerStub.getMethod("getZenMode");
            zenMode = (int) mGetZenMode.invoke(oINotificationManager);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return zenMode;
    }

    // 关闭手机
    public static void shutDown() {
        try {
            Class<?> cServiceManager = Class.forName("android.os.ServiceManager");
            Method mGetService = cServiceManager.getMethod("getService", String.class);
            Object oPowerManagerService = mGetService.invoke(null, Context.POWER_SERVICE);
            Class<?> cIPowerManagerStub = Class.forName("android.os.IPowerManager$Stub");
            Method mShutdown = cIPowerManagerStub.getMethod("shutdown", boolean.class, String.class, boolean.class);
            Method mAsInterface = cIPowerManagerStub.getMethod("asInterface", IBinder.class);
            Object oIPowerManager = mAsInterface.invoke(null, oPowerManagerService);
            mShutdown.invoke(oIPowerManager, true, null, true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void shutdownOrReboot(final boolean shutdown, final boolean confirm) {
        try {
            Class<?> ServiceManager = Class.forName("android.os.ServiceManager");
            // 获得ServiceManager的getService方法
            Method getService = ServiceManager.getMethod("getService", java.lang.String.class);
            // 调用getService获取RemoteService
            Object oRemoteService = getService.invoke(null, Context.POWER_SERVICE);
            // 获得IPowerManager.Stub类
            Class<?> cStub = Class.forName("android.os.IPowerManager$Stub");
            // 获得asInterface方法
            Method asInterface = cStub.getMethod("asInterface", android.os.IBinder.class);
            // 调用asInterface方法获取IPowerManager对象
            Object oIPowerManager = asInterface.invoke(null, oRemoteService);
            if (shutdown) {
                // 获得shutdown()方法
                Method shutdownMethod = oIPowerManager.getClass().getMethod(
                        "shutdown", boolean.class, String.class, boolean.class);
                // 调用shutdown()方法
                shutdownMethod.invoke(oIPowerManager, confirm, null, false);
            } else {
                // 获得reboot()方法
                Method rebootMethod = oIPowerManager.getClass().getMethod("reboot",
                        boolean.class, String.class, boolean.class);
                // 调用reboot()方法
                rebootMethod.invoke(oIPowerManager, confirm, null, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

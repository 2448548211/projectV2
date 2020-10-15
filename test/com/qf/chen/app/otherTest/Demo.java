package com.qf.chen.app.otherTest;

import java.lang.reflect.Field;

/**
 * @author ChenWang
 * @className Demo
 * @date 2020/10/14 19:54
 * @since JDK 1.8
 */
public class Demo {

    public static void main(String[] args) throws IllegalAccessException {
        DataTest dataTest = new DataTest();
        System.out.println(dataTest);
        Class<DataTest> aClass = DataTest.class;
        Field[] fields = aClass.getDeclaredFields();
        fields[0].setAccessible(true);
        fields[1].setAccessible(true);
        Object i = 1;
        Object j = "1";
        System.out.println(i.getClass());
        System.out.println(j.getClass());
        fields[0].set(dataTest,i);
        fields[1].set(dataTest,j);
        System.out.println(dataTest);

    }
}
class DataTest{
    private Integer num;
    private Character str;
    @Override
    public String toString() {
        return "DataTest{" +
                "num=" + num +
                ", str='" + str + '\'' +
                '}';
    }
}

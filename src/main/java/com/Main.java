package com;

import java.util.Random;


/**
 * @author wcl
 * @date 5:09 下午 2021/5/12
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        if(args.length != 1) {
            return;
        }
        Class<?> c = Main.class.getClassLoader().loadClass(args[0]);
        Test test = (Test) c.newInstance();
        for (int i = 0; i < 100; i++) {
            test.test(new Random().nextInt(100));
        }
    }
}

package com;

import java.util.concurrent.ThreadLocalRandom;


/**
 * @author zms
 * @date 5:09 下午 2021/5/12
 */
public class Main {

    private static final int NUM = 100_000;

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (args.length != 1) {
            return;
        }
        Class<?> c = Main.class.getClassLoader().loadClass(args[0]);
        Test test = (Test) c.newInstance();
        System.out.printf("开始测试:%s\n", c.getName());
        for (int i = 0; i < NUM; i++) {
//            test.test(ThreadLocalRandom.current().nextInt(2, NUM));
            test.test(ThreadLocalRandom.current().nextInt(2, 1000));
        }
        System.out.println("结束测试");
    }
}

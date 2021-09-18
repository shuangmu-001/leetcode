package com;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author zms
 * @date 11:08 上午 2021/4/26
 */
public class PrintStream extends java.io.PrintStream {

    public PrintStream(String fileName) throws FileNotFoundException {
        super(new FileOutputStream(fileName, true));
    }

    @Override
    public void print(String s) {
        super.print(s);
    }
}

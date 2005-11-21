// Copyright 2001, FreeHEP.
package org.freehep.util.io.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.freehep.util.io.ConditionalInputStream;

/**
 * Test for Conditional Input Stream.
 * 
 * @author Mark Donszelmann
 * @version $Id: src/test/java/org/freehep/util/io/test/ConditionalInputStreamTest.java 96b41b903496 2005/11/21 19:50:18 duns $
 */
public class ConditionalInputStreamTest {

    private static void test(String file, Properties defines)
            throws IOException {
        System.out.println("========================================");
        ConditionalInputStream in = new ConditionalInputStream(
                new FileInputStream(file), defines);
        int b = in.read();
        while (b != -1) {
            System.out.write(b);
            b = in.read();
        }
        in.close();
        System.out.println("========================================");
        System.out.flush();
    }

    /**
     * FIXME use junit
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: ConditionalInputStreamTest filename");
            System.exit(1);
        }

        Properties defines = new Properties();
        System.out.println("Nothing");
        test(args[0], defines);

        System.out.println("FREEHEP");
        defines.setProperty("FREEHEP", "1");
        test(args[0], defines);

        System.out.println("FREEHEP & WIRED");
        defines.setProperty("WIRED", "1");
        test(args[0], defines);
    }
}
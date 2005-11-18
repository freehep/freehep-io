// Copyright 2001, FreeHEP.
package org.freehep.util.io.test;

import java.io.*;
import java.util.*;

import org.freehep.util.io.*;

/**
 *
 * @author Mark Donszelmann
 * @version $Id: src/test/java/org/freehep/util/io/test/ConditionalInputStreamTest.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class ConditionalInputStreamTest {

    private static void test(String file, Properties defines) throws IOException {
        System.out.println("========================================");
        ConditionalInputStream in = new ConditionalInputStream(new FileInputStream(file), defines);
        int b = in.read();
        while (b != -1) {
            System.out.write(b);
            b = in.read();
        }
        in.close();
        System.out.println("========================================");
        System.out.flush();
    }

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
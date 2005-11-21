// Copyright 2003, FreeHEP.
package org.freehep.util.io.test;

import java.io.FileInputStream;
import java.io.IOException;

import org.freehep.util.io.Base64InputStream;

/**
 * Test for Base64 Input Stream.
 * 
 * @author Mark Donszelmann
 * @version $Id: src/test/java/org/freehep/util/io/test/Base64InputStreamTest.java 96b41b903496 2005/11/21 19:50:18 duns $
 */
public class Base64InputStreamTest {

    /**
     * FIXME use junit
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: Base64InputStreamTest filename");
            System.exit(1);
        }

        Base64InputStream in = new Base64InputStream(new FileInputStream(
                args[0]));
        int b = in.read();
        while (b != -1) {
            System.out.write(b);
            b = in.read();
        }
        in.close();
        System.out.flush();
    }
}

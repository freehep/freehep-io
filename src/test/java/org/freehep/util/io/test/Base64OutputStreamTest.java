// Copyright 2001, FreeHEP.
package org.freehep.util.io.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.freehep.util.io.Base64OutputStream;

/**
 * Test for Base64 Output Stream.
 * 
 * @author Mark Donszelmann
 * @version $Id: src/test/java/org/freehep/util/io/test/Base64OutputStreamTest.java 96b41b903496 2005/11/21 19:50:18 duns $
 */
public class Base64OutputStreamTest {

    /**
     * FIXME use junit
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: Base64OutputStreamTest filename");
            System.exit(1);
        }

        Base64OutputStream out = new Base64OutputStream(System.out);
        InputStream in = new FileInputStream(args[0]);
        int ch = in.read();
        while (ch >= 0) {
            out.write((byte) ch);
            ch = in.read();
        }
        in.close();
        out.close();
    }
}

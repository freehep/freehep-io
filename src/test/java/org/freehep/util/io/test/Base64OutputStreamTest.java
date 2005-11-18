// Copyright 2001, FreeHEP.
package org.freehep.util.io.test;

import java.io.*;

import org.freehep.util.io.*;

/**
 *
 * @author Mark Donszelmann
 * @version $Id: src/test/java/org/freehep/util/io/test/Base64OutputStreamTest.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class Base64OutputStreamTest {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: Base64OutputStreamTest filename");
            System.exit(1);
        }

        Base64OutputStream out = new Base64OutputStream(System.out);
        InputStream in = new FileInputStream(args[0]);
        int ch = in.read();
        while (ch >= 0) {
            out.write((byte)ch);
            ch = in.read();
        }
        in.close();
        out.close();
    }
}

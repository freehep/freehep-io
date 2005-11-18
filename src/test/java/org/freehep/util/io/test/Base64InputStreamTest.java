// Copyright 2003, FreeHEP.
package org.freehep.util.io.test;

import java.io.*;

import org.freehep.util.io.*;

/**
 *
 * @author Mark Donszelmann
 * @version $Id: src/test/java/org/freehep/util/io/test/Base64InputStreamTest.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class Base64InputStreamTest {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: Base64InputStreamTest filename");
            System.exit(1);
        }

        Base64InputStream in = new Base64InputStream(new FileInputStream(args[0]));
        int b = in.read();
        while (b != -1) {
            System.out.write(b);
            b = in.read();
        }
        in.close();
        System.out.flush();
    }
}

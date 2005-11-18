// Copyright 2001, FreeHEP.
package org.freehep.util.io.test;

import java.io.*;

import org.freehep.util.io.*;

/**
 *
 * @author Mark Donszelmann
 * @version $Id: src/test/java/org/freehep/util/io/test/ASCIIHexInputStreamTest.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class ASCIIHexInputStreamTest {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: ASCIIHexInputStreamTest filename");
            System.exit(1);
        }
        
        ASCIIHexInputStream in = new ASCIIHexInputStream(new FileInputStream(args[0]));
        
        int b = in.read();
        while (b != -1) {
            System.out.write(b);
            b = in.read();
        }
        in.close();
        System.out.flush();
    }
}

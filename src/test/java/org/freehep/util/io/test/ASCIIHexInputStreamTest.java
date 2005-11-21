// Copyright 2001, FreeHEP.
package org.freehep.util.io.test;

import java.io.FileInputStream;
import java.io.IOException;

import org.freehep.util.io.ASCIIHexInputStream;

/**
 * Test for ASCII Hex Input Stream
 * 
 * @author Mark Donszelmann
 * @version $Id: src/test/java/org/freehep/util/io/test/ASCIIHexInputStreamTest.java 96b41b903496 2005/11/21 19:50:18 duns $
 */
public class ASCIIHexInputStreamTest {

    /**
     * FIXME use junit
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: ASCIIHexInputStreamTest filename");
            System.exit(1);
        }

        ASCIIHexInputStream in = new ASCIIHexInputStream(new FileInputStream(
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

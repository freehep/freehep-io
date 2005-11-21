// Copyright 2001, FreeHEP.
package org.freehep.util.io.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.freehep.util.io.ASCIIHexOutputStream;

/**
 * Test for ASCII Hex Output Stream.
 * 
 * @author Mark Donszelmann
 * @version $Id: src/test/java/org/freehep/util/io/test/ASCIIHexOutputStreamTest.java 96b41b903496 2005/11/21 19:50:18 duns $
 */
public class ASCIIHexOutputStreamTest {

    /**
     * FIXME use junit
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: ASCIIHexOutputStreamTest filename");
            System.exit(1);
        }

        ASCIIHexOutputStream out = new ASCIIHexOutputStream(System.out);
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        String line = reader.readLine();
        while (line != null) {
            out.write(line.getBytes());
            out.write('\r');
            out.write('\n');
            line = reader.readLine();
        }
        reader.close();
        out.close();
    }
}

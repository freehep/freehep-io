// Copyright 2002, FreeHEP.
package org.freehep.util.io.test;

import java.io.FileInputStream;

import org.freehep.util.io.BitInputStream;
import org.freehep.util.io.BitOutputStream;

/**
 * Test for Bit Input Stream
 * 
 * @author Mark Donszelmann
 * @version $Id: src/test/java/org/freehep/util/io/test/BitInputStreamTest.java 96b41b903496 2005/11/21 19:50:18 duns $
 */
public class BitInputStreamTest {

    /**
     * FIXME use junit
     * 
     * @param args
     */
    public static void main(String args[]) {
        try {
            if (args.length != 1) {
                System.err.println("Usage: BitInputStreamTest inputfile");
                System.exit(1);
            }
            BitInputStream bis = new BitInputStream(
                    new FileInputStream(args[0]));

            int n = 0;
            int sum = 0;
            for (int i = 0; i <= 10; i++) {
                n = BitOutputStream.minBits(i);
                long x = bis.readUBits(n);
                System.out.println("read: " + x);
                sum += n;
            }
            System.out.println("bits: " + sum + " bytes: "
                    + (int) java.lang.Math.ceil((float) sum / 8f));
            bis.close();
        } catch (Exception e) {
            System.err.println("HealMe: " + e);
        }
    }
}

// Copyright 2002, FreeHEP.
package org.freehep.util.io.test;

import java.io.FileOutputStream;

import org.freehep.util.io.BitOutputStream;

/**
 * Test for Bit Output Stream.
 * 
 * @author Mark Donszelmann
 * @version $Id: src/test/java/org/freehep/util/io/test/BitOutputStreamTest.java 96b41b903496 2005/11/21 19:50:18 duns $
 */
public class BitOutputStreamTest {

    /**
     * FIXME use junit
     * 
     * @param args
     */
    public static void main(String args[]) {
        try {
            if (args.length != 1) {
                System.err.println("Usage: BitOutputStreamTest outputfile");
                System.exit(1);
            }
            BitOutputStream bos = new BitOutputStream(new FileOutputStream(
                    args[0]));

            int n = 0;
            int sum = 0;
            for (int i = 0; i <= 10; i++) {
                n = BitOutputStream.minBits(i);
                System.out.println("bits needed: " + n);
                bos.writeUBits(i, n);
                sum += n;
            }
            System.out.println("bits: " + sum + " bytes: "
                    + (int) java.lang.Math.ceil((float) sum / 8f));
            bos.close();
        } catch (Exception e) {
            System.err.println("Bitme: " + e);
        }
    }
}

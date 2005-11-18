// Copyright 2002, FreeHEP.
package org.freehep.util.io.test;

import java.io.*;
import org.freehep.util.io.*;

/**
 * HealMe
 *
 * @author Mark Donszelmann
 * @version $Id: src/test/java/org/freehep/util/io/test/BitInputStreamTest.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class BitInputStreamTest {

    public static void main(String args[]) {
        try {
            if (args.length != 1) {
                System.err.println("Usage: BitInputStreamTest inputfile");
                System.exit(1);
            }
            BitInputStream bis = new BitInputStream(new FileInputStream(args[0]));

            int n = 0;
            int sum = 0;
            for (int i=0;i<=10;i++) {
                n = BitOutputStream.minBits(i);
                long x = bis.readUBits(n);
                System.out.println("read: "+x);
                sum += n;
            }
            System.out.println("bits: "+sum+" bytes: "+(int)java.lang.Math.ceil((float)sum/8f));
            bis.close();
        } catch (Exception e) {
            System.err.println("HealMe: "+e);
        }
    }
}

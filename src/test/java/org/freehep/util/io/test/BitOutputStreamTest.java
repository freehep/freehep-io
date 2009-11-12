// Copyright 2002-2009, FreeHEP.
package org.freehep.util.io.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.freehep.util.io.BitOutputStream;

/**
 * Test for Bit Output Stream.
 * 
 * @author Mark Donszelmann
 */
public class BitOutputStreamTest extends AbstractStreamTest {

    /**
     * Test method for 'org.freehep.util.io.BitOutputStream.writeUBits()'
     * @throws Exception if ref file cannot be found
     */
    public void testWrite() throws Exception {
        File testFile = new File(testDir, "Quote.txt");
        File outFile = new File(outDir, "Quote.bit");
        File refFile = new File(refDir, "Quote.bit");
        
        BitOutputStream out = new BitOutputStream(new FileOutputStream(outFile));
        FileInputStream in = new FileInputStream(testFile);
        int b;
        while ((b = in.read()) >= 0) {
            int n = BitOutputStream.minBits(b);
            out.writeUBits(n-1, 3); // min = 1, max is 8
            out.writeUBits(b, n);
        }
        in.close();
        out.close();
        
        Assert.assertEquals(refFile, outFile, true);
    }    
}

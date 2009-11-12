// Copyright 2001-2009, FreeHEP.
package org.freehep.util.io.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.freehep.util.io.RunLengthOutputStream;

/**
 * Test for Run Length Output Stream.
 * 
 * @author Mark Donszelmann
 */
public class RunLengthOutputStreamTest extends AbstractStreamTest {

    /**
     * Test method for 'org.freehep.util.io.RunLengthOutputStream.write()'
     * @throws Exception if ref file cannot be found
     */
    public void testWrite() throws Exception {
        File testFile = new File(testDir, "Quote.txt");
        File outFile = new File(outDir, "Quote.rnl");
        File refFile = new File(refDir, "Quote.rnl");
        
        RunLengthOutputStream out = new RunLengthOutputStream(new FileOutputStream(outFile));
        FileInputStream in = new FileInputStream(testFile);
        int b;
        while ((b = in.read()) >= 0) {
            out.write(b);
        }
        in.close();
        out.close();
        
        Assert.assertEquals(refFile, outFile, true);
    }
}

// Copyright 2001-2005, FreeHEP.
package org.freehep.util.io.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

import org.freehep.util.Assert;
import org.freehep.util.io.ASCII85OutputStream;

/**
 * Test for ASCII85 Output Stream
 * 
 * @author Mark Donszelmann
 * @version $Id: src/test/java/org/freehep/util/io/test/ASCII85OutputStreamTest.java c5cb38309f84 2005/12/02 20:35:09 duns $
 */
public class ASCII85OutputStreamTest extends AbstractStreamTest {
    
    /**
     * Test method for 'org.freehep.util.io.ASCII85OutputStream.write()'
     * @throws Exception if ref file cannot be found
     */
    public void testWrite() throws Exception {
        File testFile = new File(testDir, "TestFile.xml");
        File outFile = new File(outDir, "TestFile.a85");
        File refFile = new File(refDir, "TestFile.a85");
        
        ASCII85OutputStream out = new ASCII85OutputStream(new FileOutputStream(outFile));
        PrintWriter writer = new PrintWriter(out);
        BufferedReader reader = new BufferedReader(new FileReader(testFile));
        String line;
        while ((line = reader.readLine()) != null) {
            writer.println(line);
        }
        reader.close();
        writer.close();
        
        Assert.assertEquals(refFile, outFile, false);
    }    
}

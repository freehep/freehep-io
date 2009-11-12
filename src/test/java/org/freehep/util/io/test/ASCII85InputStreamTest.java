// Copyright 2001-2009, FreeHEP.
package org.freehep.util.io.test;

import java.io.File;
import java.io.FileInputStream;

import org.freehep.util.io.ASCII85InputStream;

/**
 * Test for ASCII85 Input Stream.
 * 
 * @author Mark Donszelmann
 */
public class ASCII85InputStreamTest extends AbstractStreamTest {
        
    /**
     * Test method for 'org.freehep.util.io.ASCII85InputStream.read()'
     * @throws Exception if ref file cannot be found
     */
    public void testRead() throws Exception {
        File testFile = new File(testDir, "Quote.a85");
        File refFile = new File(refDir, "Quote.txt");
            
        ASCII85InputStream in = new ASCII85InputStream(new FileInputStream(testFile));
        Assert.assertEquals(new FileInputStream(refFile), in, true, refFile.getPath());
    }
}

// Copyright 2001-2009, FreeHEP.
package org.freehep.util.io.test;

import java.io.File;
import java.io.FileInputStream;

import org.freehep.util.io.RunLengthInputStream;

/**
 * Test for Run Length Input Stream.
 * 
 * @author Mark Donszelmann
 */
public class RunLengthInputStreamTest extends AbstractStreamTest {

    /**
     * Test method for 'org.freehep.util.io.RunLengthInputStream.read()'
     * @throws Exception if ref file cannot be found
     */
    public void testRead() throws Exception {
        File testFile = new File(testDir, "Quote.rnl");
        File refFile = new File(refDir, "Quote.txt");
            
        RunLengthInputStream in = new RunLengthInputStream(new FileInputStream(testFile));
        Assert.assertEquals(new FileInputStream(refFile), in, true, refFile.getPath());
    }
}

// Copyright 2001, FreeHEP.
package org.freehep.util.io.test;

import java.io.File;
import java.io.FileInputStream;

import org.freehep.util.Assert;
import org.freehep.util.io.RunLengthInputStream;

/**
 * Test for Run Length Input Stream.
 * 
 * @author Mark Donszelmann
 * @version $Id: src/test/java/org/freehep/util/io/test/RunLengthInputStreamTest.java c5cb38309f84 2005/12/02 20:35:09 duns $
 */
public class RunLengthInputStreamTest extends AbstractStreamTest {

    /**
     * Test method for 'org.freehep.util.io.RunLengthInputStream.read()'
     * @throws Exception if ref file cannot be found
     */
    public void testRead() throws Exception {
        File testFile = new File(testDir, "TestFile.rnl");
        File refFile = new File(refDir, "TestFile.xml");
            
        RunLengthInputStream in = new RunLengthInputStream(new FileInputStream(testFile));
        Assert.assertEquals(new FileInputStream(refFile), in, true, refFile.getPath());
    }
}

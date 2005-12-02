// Copyright 2001, FreeHEP.
package org.freehep.util.io.test;

import java.io.File;
import java.io.FileInputStream;

import junit.framework.TestCase;

import org.freehep.util.Assert;
import org.freehep.util.io.ASCII85InputStream;

/**
 * Test for ASCII85 Input Stream.
 * 
 * @author Mark Donszelmann
 * @version $Id: src/test/java/org/freehep/util/io/test/ASCII85InputStreamTest.java 933ba6fbd8c7 2005/12/02 04:51:25 duns $
 */
public class ASCII85InputStreamTest extends TestCase {
    
    private File testFile = new File(System.getProperty("basedir"), "src/test/resources/ref/TestFile.xml");
    private File refFile = new File(System.getProperty("basedir"), "src/test/resources/ref/TestFile.xml.ascii85");
    
    /**
     * Test method for 'org.freehep.util.io.ASCII85InputStream.read()'
     * @throws Exception if ref file cannot be found
     */
    public void testRead() throws Exception {
            ASCII85InputStream in = new ASCII85InputStream(new FileInputStream(testFile));
            Assert.assertEquals(new FileInputStream(refFile), in, false, refFile.getPath());
    }
}

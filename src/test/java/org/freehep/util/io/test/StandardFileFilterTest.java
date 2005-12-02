// Copyright 2003, SLAC, Stanford, U.S.A
package org.freehep.util.io.test;

import java.io.File;
import java.io.FileFilter;

import org.freehep.util.Assert;
import org.freehep.util.io.StandardFileFilter;

/**
 * Test for the Standard File Filter.
 * 
 * @author duns
 * @version $Id: src/test/java/org/freehep/util/io/test/StandardFileFilterTest.java 5c38dc058ace 2005/12/02 23:30:37 duns $
 */
public class StandardFileFilterTest extends AbstractStreamTest {

    public void testFileFilterTxt() throws Exception {
        FileFilter filter = new StandardFileFilter("*.txt");
        File[] files = refDir.listFiles(filter);
        Assert.assertEquals(4, files.length);
    }

    public void testFileFilterRef() throws Exception {
        FileFilter filter = new StandardFileFilter("*.ref*");
        File[] files = refDir.listFiles(filter);
        Assert.assertEquals(3, files.length);
    }
}

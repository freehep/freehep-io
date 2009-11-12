// Copyright 2005-2009, FreeHEP.
package org.freehep.util.io.test;

import java.io.File;

import junit.framework.TestCase;

/**
 * Abstract Test for ASCII85 Output Stream
 * 
 * @author Mark Donszelmann
 */
public abstract class AbstractStreamTest extends TestCase {
    
    protected File refDir;
    protected File testDir;
    protected File outDir;
    
    protected void setUp() throws Exception {
        String baseDir = System.getProperty("basedir");
        if (baseDir == null) baseDir = ".";
        refDir = new File(baseDir, "src/test/resources/ref");
        testDir = refDir;
        outDir = new File(baseDir, "target/test-output/ref");
        if (!outDir.exists()) outDir.mkdirs();        
    }
    
}

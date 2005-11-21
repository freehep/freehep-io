// Copyright 2003, SLAC, Stanford, U.S.A
package org.freehep.util.io.test;

import java.io.File;
import java.io.FileFilter;

import org.freehep.util.io.StandardFileFilter;

/**
 * Test for the Standard File Filter.
 * 
 * @author duns
 * @version $Id: src/test/java/org/freehep/util/io/test/StandardFileFilterTest.java 96b41b903496 2005/11/21 19:50:18 duns $
 */
public class StandardFileFilterTest {

    /**
     * FIXME use junit
     * @param args
     */
    public static void main(String[] args) {
        FileFilter filter = new StandardFileFilter("*.html");
        File[] files = new File(".").listFiles(filter);

        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].getPath().replaceAll(
                    "\\" + File.separator, "/"));
        }
    }
}

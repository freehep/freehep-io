// Copyright 2003-2009, FreeHEP
package org.freehep.util.io.test;

import java.io.File;
import java.io.FileFilter;

import org.freehep.util.io.StandardFileFilter;

/**
 * Test for the Standard File Filter.
 * 
 * @author Mark Donszelmann
 */
public class StandardFileFilterTest extends AbstractStreamTest {

	/**
	 * Counts *.txt files in the ref directory
	 */
	public void testFileFilterTxt() {
		FileFilter filter = new StandardFileFilter("*.txt");
		File[] files = refDir.listFiles(filter);
		org.junit.Assert.assertEquals(5, files.length);
	}

	/**
	 * Counts *.ref* files in the ref directory
	 */
	public void testFileFilterRef() {
		FileFilter filter = new StandardFileFilter("*.ref*");
		File[] files = refDir.listFiles(filter);
		org.junit.Assert.assertEquals(3, files.length);
	}
}

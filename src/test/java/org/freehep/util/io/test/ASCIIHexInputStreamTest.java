// Copyright 2001-2009, FreeHEP.
package org.freehep.util.io.test;

import java.io.File;
import java.io.FileInputStream;

import org.freehep.util.io.ASCIIHexInputStream;

/**
 * Test for ASCII Hex Input Stream
 * 
 * @author Mark Donszelmann
 */
public class ASCIIHexInputStreamTest extends AbstractStreamTest {

	/**
	 * Test method for 'org.freehep.util.io.ASCIIHexInputStream.read()'
	 * 
	 * @throws Exception
	 *             if ref file cannot be found
	 */
	public void testRead() throws Exception {
		File testFile = new File(testDir, "Quote.hex");
		File refFile = new File(refDir, "Quote.txt");

		ASCIIHexInputStream in = new ASCIIHexInputStream(new FileInputStream(
				testFile));
		Assert.assertEquals(new FileInputStream(refFile), in, true, refFile
				.getPath());
	}
}

// Copyright FreeHEP, 2007-2009
package org.freehep.util.io.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.freehep.util.io.ASCIIHexOutputStream;

/**
 * Test for ASCII Hex Output Stream.
 * 
 * @author Mark Donszelmann
 */
public class ASCIIHexOutputStreamTest extends AbstractStreamTest {

	/**
	 * Test method for 'org.freehep.util.io.ASCIIHexOutputStream.write()'
	 * 
	 * @throws Exception
	 *             if ref file cannot be found
	 */
	public void testWrite() throws Exception {
		File testFile = new File(testDir, "Quote.txt");
		File outFile = new File(outDir, "Quote.hex");
		File refFile = new File(refDir, "Quote.hex");

		PrintStream out = new PrintStream(new ASCIIHexOutputStream(
				new FileOutputStream(outFile)));
		FileInputStream in = new FileInputStream(testFile);
		int b;
		while ((b = in.read()) >= 0) {
			out.write(b);
		}
		in.close();
		out.close();

		Assert.assertEquals(refFile, outFile, true);
	}
}

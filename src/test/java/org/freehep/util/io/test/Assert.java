// Copyright 2002-2009, FreeHEP.
package org.freehep.util.io.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import junit.framework.AssertionFailedError;

/**
 * Additional assert methods to the junit.framework.Assert class.
 * 
 * @author Mark Donszelmann
 */
public class Assert extends org.junit.Assert {

	/**
	 * static class only
	 */
	protected Assert() {
	}

	/**
	 * Compares two files. The files may be gzipped, and will then be
	 * uncompressed on the fly.
	 * 
	 * @param expected
	 *            reference file
	 * @param actual
	 *            file to be tested
	 * @param isBinary
	 *            when true will do byte-by-byte comparison rather than line by
	 *            line.
	 * @throws FileNotFoundException
	 *             if one of the files cannot be found.
	 * @throws IOException
	 *             if one of the files cannot read.
	 * @throws AssertionFailedError
	 *             if the files are not equal.
	 */
	public static void assertEquals(File expected, File actual, boolean isBinary)
			throws FileNotFoundException, IOException {
		InputStream ref = new BufferedInputStream(new FileInputStream(expected));
		if (expected.getPath().toLowerCase().endsWith(".gz")) {
			ref = new GZIPInputStream(ref);
		}

		InputStream tst = new BufferedInputStream(new FileInputStream(actual));
		if (actual.getPath().toLowerCase().endsWith(".gz")) {
			tst = new GZIPInputStream(tst);
		}

		assertEquals(ref, tst, isBinary, "File " + actual.getPath());
	}

	/**
	 * Compares two streams.
	 * 
	 * @param expected
	 *            reference stream
	 * @param actual
	 *            stream to be tested
	 * @param filename
	 *            an prefix for the error message (normally filename associated
	 *            with the actual stream)
	 * @param isBinary
	 *            when true will do byte-by-byte comparison rather than line by
	 *            line (Reader).
	 * @throws IOException
	 *             if one of the streams cannot read.
	 * @throws AssertionFailedError
	 *             if the streams are not equal.
	 */
	public static void assertEquals(InputStream expected, InputStream actual,
			boolean isBinary, String filename) throws IOException {
		int diff;
		if (isBinary) {
			diff = diff(expected, actual);
			if (diff >= 0) {
				throw new AssertionFailedError(filename
						+ ": comparison failed at offset " + diff);
			}
		} else {
			diff = diff(new BufferedReader(new InputStreamReader(expected)),
					new BufferedReader(new InputStreamReader(actual)));
			if (diff >= 0) {
				throw new AssertionFailedError(filename
						+ ": comparison failed at line " + diff);
			}
		}
	}

	private static int diff(InputStream ref, InputStream tst)
			throws IOException {
		int bRef, bTst;
		int i = 0;
		do {
			bRef = ref.read();
			bTst = tst.read();
			// System.err.println(i+" "+bTst+" "+(char)bTst+" "+bRef+" "+(char)bRef);
			i++;
		} while ((bRef >= 0) && (bTst >= 0) && (bRef == bTst));
		ref.close();
		tst.close();
		return (bRef == bTst) ? -1 : i - 1;
	}

	private static int diff(BufferedReader ref, BufferedReader tst)
			throws IOException {
		String bRef, bTst;
		int i = 1;
		do {
			bRef = ref.readLine();
			bTst = tst.readLine();
			i++;
		} while ((bRef != null) && (bTst != null) && (bRef.equals(bTst)));
		ref.close();
		tst.close();

		return ((bRef == null) && (bTst == null)) ? -1 : i - 1;
	}

	public static void assertArrayEquals(byte[] expecteds, byte[] actuals,
			int len) {
		if (expecteds.length != len) {
			throw new AssertionError("expected length " + expecteds.length
					+ " not equals to checked length " + len);
		}

		// make arrays the same length (as actuals) and pad with 0s
		byte[] b = new byte[actuals.length];
		System.arraycopy(expecteds, 0, b, 0, len);

		for (int i = len; i < b.length; i++) {
			b[i] = 0;
			actuals[i] = 0;
		}

		org.junit.Assert.assertArrayEquals(b, actuals);
	}
}

// Copyright FreeHEP 2003-2009
package org.freehep.util.io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Equivalent to writing to /dev/nul
 * 
 * @author tonyj
 */
public class DummyOutputStream extends OutputStream {
	/**
	 * Creates a Dummy output steram.
	 */
	public DummyOutputStream() {
	}

	@Override
	public void write(int b) throws IOException {
	}

	@Override
	public void write(byte[] b) throws IOException {
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
	}
}

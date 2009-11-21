// Copyright 2003-2009, FreeHEP.
package org.freehep.util.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;

/**
 * Special stream that can be used to write a header in uncompressed format, and
 * the rest of the stream in compressed format. This stream is used by SWF, to
 * compress the tail of the stream.
 * 
 * @author Mark Donszelmann
 */
public class CompressableOutputStream extends FilterOutputStream implements
		FinishableOutputStream {
	private boolean compress;

	private DeflaterOutputStream dos;

	/**
	 * Creates a Compressable Output Stream from given stream. Initially the
	 * stream does not compress.
	 * 
	 * @param out
	 *            stream to write to
	 */
	public CompressableOutputStream(OutputStream out) {
		super(out);
		compress = false;
	}

	// Made final to detect write(int) changes.
	@Override
	public final void write(int b) throws IOException {
		writeSingleByte(b);
	}

	protected void writeSingleByte(int b) throws IOException {
		if (compress) {
			dos.write(b);
		} else {
			out.write(b);
		}
	}

	@Override
	public final void write(byte[] b, int off, int len) throws IOException {
		writeByteArray(b, off, len);
	}

	protected void writeByteArray(byte[] bytes, int offset, int length)
			throws IOException {
		if (compress) {
			dos.write(bytes, offset, length);
		} else {
			out.write(bytes, offset, length);
		}
	}

	public void finish() throws IOException {
		if (compress) {
			dos.finish();
		}
		if (out instanceof FinishableOutputStream) {
			((FinishableOutputStream) out).finish();
		}
	}

	@Override
	public void close() throws IOException {
		if (compress) {
			finish();
			dos.close();
		} else {
			out.close();
		}
	}

	/**
	 * Start compressing from the next byte onwards. Flushes what is currently
	 * in the buffer to the underlying stream and start compression from here.
	 * 
	 * @throws IOException
	 *             if write fails
	 */
	public void startCompressing() throws IOException {
		out.flush();
		compress = true;
		dos = new DeflaterOutputStream(out);
	}
}
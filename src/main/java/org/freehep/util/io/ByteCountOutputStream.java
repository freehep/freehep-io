// Copyright 2001, FreeHEP.
package org.freehep.util.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Allows to write to some internal buffer and keep count of any set of stacked
 * buffers written. When a pushBuffer is called, a new buffer is created. When a
 * popBuffer is called, the number of bytes written to the popped buffer is
 * returned. From this moment on one is writing again to the underlying
 * buffer/outputstream. A header can be written and the header can be written.
 * By calling append(), the previous buffer will be appended to the underlying
 * one. This way one can write into a buffer, retrieve its length, create some
 * header bytes and insert those in front of the just written buffer.
 * 
 * @author Mark Donszelmann
 * @author Ian Graham - added popBufferBytes() for use by CGMOutputStream
 * @version $Id: src/main/java/org/freehep/util/io/ByteCountOutputStream.java
 *          96b41b903496 2005/11/21 19:50:18 duns $
 */
public class ByteCountOutputStream extends ByteOrderOutputStream {

	private int currentBuffer;

	private List<Buffer> bufferList;

	/**
	 * Create a Byte Count output stream from given stream
	 * 
	 * @param out
	 *            stream to write to
	 * @param littleEndian
	 *            true if stream should be little endian
	 */
	public ByteCountOutputStream(OutputStream out, boolean littleEndian) {
		super(out, littleEndian);
		currentBuffer = -1;
		// Improve efficiency.
		bufferList = new ArrayList<Buffer>();
	}

	// Renamed and final to detect write(int) changes.
	@Override
	protected final void writeSingleByte(int b) throws IOException {
		// System.out.println(Integer.toHexString(b)+" "+index);
		// original stream
		if (currentBuffer == -1) {
			super.writeSingleByte(b);
			return;
		}

		Buffer buffer = bufferList.get(currentBuffer);
		buffer.add((byte) b);
	}

	// Improve byte array performance.
	@Override
	protected void writeByteArray(byte[] bytes, int offset, int length)
			throws IOException {
		// original stream
		if (currentBuffer == -1) {
			super.writeByteArray(bytes, offset, length);
			return;
		}

		Buffer buffer = bufferList.get(currentBuffer);
		buffer.add(bytes, offset, length);
	}

	/**
	 * Pushes the buffer and strat writing to a new one.
	 * 
	 * @throws IOException
	 *             if the write fails
	 */
	public void pushBuffer() throws IOException {
		// call append just to make sure we did not leave anything in the
		// buffer...
		append();
		bufferList.add(new Buffer());
		currentBuffer++;
	}

	/**
	 * returns the number of bytes written since the last pushBuffer call. It
	 * also puts the write pointer at the start of the buffer, to be able to
	 * "insert" a header. If no buffer was ever pushed, or the last one has been
	 * popped -1 is returned.
	 * 
	 * @return number of bytes written or -1
	 * @throws IOException
	 *             if the write fails
	 */
	public int popBuffer() throws IOException {
		if (currentBuffer >= 0) {
			append();
			int len = getBufferLength();
			currentBuffer--;
			return len;
		}
		return -1;
	}

	/**
	 * Similar to pop buffer, but returns the actual byte[] buffer and then
	 * removes it from the bufferList so that subsequent appends will have no
	 * action. When using this method, the caller is responsible for writing all
	 * buffered data as desired. The byte[] array will usually be larger than
	 * the actual content, so to determine the length of the actual data, you
	 * must call getBufferLength() <i>before</i> invoking this method.
	 * 
	 * @return byte array of bytes that need to be written
	 * @throws IOException
	 *             if write fails
	 */
	public byte[] popBufferBytes() throws IOException {
		int len = popBuffer();
		if (len >= 0) {
			Buffer buffer = bufferList.remove(currentBuffer + 1);
			return buffer.getBytes();
		} else {
			return new byte[0];
		}
	}

	/**
	 * @return valid number of bytes in the buffer
	 */
	public int getBufferLength() {
		return (currentBuffer >= 0) ? bufferList.get(currentBuffer)
				.getLength() : -1;
	}

	/**
	 * @return total number of bytes written
	 */
	public int getLength() {
		int length = 0;
		for (int i = 0; i < bufferList.size(); i++) {
			length += bufferList.get(i).getLength();
		}
		return (currentBuffer >= 0) ? length : -1;
	}

	/**
	 * Inserts the bytes written as header and puts the write pointer at the end
	 * of the stream.
	 * 
	 * @throws IOException
	 *             if write fails
	 */
	public void append() throws IOException {
		// append the top-level buffer
		super.byteAlign();

		if (currentBuffer + 1 >= bufferList.size()) {
			// there is no buffer to append
			return;
		}

		Buffer append = bufferList.get(currentBuffer + 1);
		if (append.getLength() > 0) {
			if (currentBuffer >= 0) {
				bufferList.get(currentBuffer).add(append);
			} else {
				super.write(append.getBytes(), 0, append.getLength());
			}
		}
		bufferList.remove(currentBuffer + 1);
	}

	/**
	 * closes the stream, inserting any non-written header.
	 */
	@Override
	public void close() throws IOException {
		append();
		super.close();
	}

	static class Buffer {

		byte[] buffer;

		int len;

		Buffer() {
			len = 0;
			buffer = new byte[256]; // 8192
		}

		void add(byte b) {
			if (len + 1 > buffer.length) {
				byte newBuffer[] = new byte[buffer.length << 1];
				System.arraycopy(buffer, 0, newBuffer, 0, len);
				buffer = newBuffer;
			}
			buffer[len] = b;
			len++;
		}

		// Improve byte array performance.
		void add(byte[] bytes, int offset, int appendLength) {
			int needed = len + appendLength;
			if (needed > buffer.length) {
				byte[] newBuffer = new byte[needed];
				System.arraycopy(buffer, 0, newBuffer, 0, len);
				buffer = newBuffer;
			}
			System.arraycopy(bytes, offset, buffer, len, appendLength);
			len += appendLength;
		}

		void add(Buffer append) {
			int appendLength = append.getLength();
			int needed = len + appendLength;
			if (needed > buffer.length) {
				byte[] newBuffer = new byte[needed];
				System.arraycopy(buffer, 0, newBuffer, 0, len);
				buffer = newBuffer;
			}
			System.arraycopy(append.getBytes(), 0, buffer, len, appendLength);
			len += appendLength;
		}

		int getLength() {
			return len;
		}

		byte[] getBytes() {
			return buffer;
		}
	}
}

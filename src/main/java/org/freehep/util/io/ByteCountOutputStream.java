// Copyright 2001, FreeHEP.
package org.freehep.util.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Allows to write to some internal buffer and keep count of any
 * set of stacked buffers written. When a pushBuffer is called,
 * a new buffer is created. When a popBuffer is called, the number
 * of bytes written to the popped buffer is returned. From this moment
 * on one is writing again to the underlying buffer/outputstream.
 * A header can be written and the header can be
 * written. By calling append(), the previous buffer will be
 * appended to the underlying one. This way one can write
 * into a buffer, retrieve its length, create some header bytes
 * and insert those in front of the just written buffer.
 *
 * @author Mark Donszelmann
 * @author Ian Graham - added popBufferBytes() for use by CGMOutputStream
 * @version $Id: src/main/java/org/freehep/util/io/ByteCountOutputStream.java effd8b4f3966 2005/11/19 07:52:18 duns $
 */
public class ByteCountOutputStream extends ByteOrderOutputStream {

    private int currentBuffer;
    private List bufferList;

    public ByteCountOutputStream(OutputStream out, boolean littleEndian) {
        super(out, littleEndian);
        currentBuffer = -1;
        bufferList = new LinkedList();
    }


    public void write(int b) throws IOException {
//        System.out.println(Integer.toHexString(b)+" "+index);
        // original stream
        if (currentBuffer == -1) {
            super.write(b);
            return;
        }

        Buffer buffer = (Buffer)bufferList.get(currentBuffer);
        buffer.add((byte)b);
    }

    public void pushBuffer() throws IOException {
//      call append just to make sure we did not leave anything in the buffer...
        append();
        bufferList.add(new Buffer());
        currentBuffer++;
    }

    /**
     * returns the number of bytes written since the last pushBuffer call.
     * It also puts the write pointer at the start of the buffer, to be able
     * to "insert" a header.
     * If no buffer was ever pushed, or the last one has been popped -1 is returned.
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
     * Similar to pop buffer, but returns the actual byte[] buffer and
     * then removes it from the bufferList so that subsequent appends
     * will have no action.  When using this method, the caller is
     * responsible for writing all buffered data as desired.
     * The byte[] array will usually be larger than the actual content, so
     * to determine the length of the actual data, you must call
     * getBufferLength() <i>before</i> invoking this method.
     */
    public byte[] popBufferBytes() throws IOException {
        int len = popBuffer();
        if (len >= 0) {
            Buffer buffer = (Buffer) bufferList.remove(currentBuffer+1);
            return buffer.getBytes();
        } else {
            return new byte[0];
        }
    }
    
    public int getBufferLength() {
        return (currentBuffer >= 0) ? ((Buffer)bufferList.get(currentBuffer)).getLength() : -1;
    }

    public int getLength() {
        int length = 0;
        for (int i=0; i<bufferList.size(); i++) {
            length += ((Buffer)bufferList.get(i)).getLength();
        }
        return (currentBuffer >= 0) ? length : -1;
    }

    /**
     * Inserts the bytes written as header and
     * puts the write pointer at the end of the stream.
     */
    public void append() throws IOException {
        // append the top-level buffer
        super.byteAlign();

        if (currentBuffer+1 >= bufferList.size()) {
            // there is no buffer to append
            return;
        }

        Buffer append = (Buffer)bufferList.get(currentBuffer+1);
        if (append.getLength() > 0) {
            if (currentBuffer >= 0) {
                ((Buffer)bufferList.get(currentBuffer)).add(append);
            } else {
                super.write(append.getBytes(), 0, append.getLength());
            }
        }
        bufferList.remove(currentBuffer+1);
    }

    /**
     * closes the stream, inserting any non-written header.
     */
    public void close() throws IOException {
        append();
        super.close();
    }

    static class Buffer {

        byte[] buffer;
        int len;

        Buffer() {
            len = 0;
            buffer = new byte[256];    // 8192
        }

        void add(byte b) {
            if (len+1 > buffer.length) {
                byte newBuffer[] = new byte[buffer.length << 1];
                System.arraycopy(buffer, 0, newBuffer, 0, len);
                buffer = newBuffer;
            }
            buffer[len] = b;
            len++;
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

// Copyright 2001, FreeHEP.
package org.freehep.util.io;

import java.io.InputStream;
import java.io.IOException;

/**
 * The input buffer can be limited to less than the number of bytes
 * of the underlying buffer. Only one real
 * input stream exists, which is where the reads take place. A buffer
 * is limited by some length. If more is read, -1 is returned.
 * Multiple limits can be set by calling pushBuffer.
 * If bytes are left in the buffer when popBuffer is called, they are returned
 * in an array. Otherwise null is returned.
 *
 * @author Mark Donszelmann
 * @author Charles Loomis
 * @version $Id: src/main/java/org/freehep/util/io/ByteCountInputStream.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class ByteCountInputStream extends ByteOrderInputStream {

    private int index;
    private int[] size;
    private long len;

    public ByteCountInputStream(InputStream in, boolean littleEndian, int stackDepth) {
        super(in, littleEndian);
        size = new int[stackDepth];
        index = -1;
        len = 0;
    }

    public int read() throws IOException {
        // original stream
        if (index == -1) {
            len++;
            return super.read();
        }

        // end of buffer
        if (size[index] <= 0) return -1;

        // decrease counter
        size[index]--;

        len++;
        return super.read();
    }

    public void pushBuffer(int len) {
        if (index >= size.length-1) {
            System.err.println("ByteCountInputStream: trying to push more buffers than stackDepth: "+size.length);
            return;
        }

        if (index >= 0) {
            if (size[index] < len) {
                System.err.println("ByteCountInputStream: trying to set a length: "+len+", longer than the underlying buffer: "+size[index]);
                return;
            }
            size[index] -= len;
        }
        index++;
        size[index] = len;
    }

    /**
     * @return null if buffer was completely read. Otherwise rest of buffer is
     * read and returned.
     */
    public byte[] popBuffer() throws IOException {
        if (index >= 0) {
            int len = size[index];
            if (len > 0) {
                return readByte(len);
            } else if (len < 0) {
                System.err.println("ByteCountInputStream: Internal Error");
            }
            index--;
        }
        return null;
    }

    public long getLength() {
        return (index >=0) ? size[index] : len;
    }
}

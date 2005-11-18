// Copyright 2001, FreeHEP.
package org.freehep.util.io;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.io.IOException;

/**
 * Class to read bytes and pairs of bytes in both little and big endian order.
 *
 * @author Mark Donszelmann
 * @author Charles Loomis
 * @version $Id: src/main/java/org/freehep/util/io/ByteOrderInputStream.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class ByteOrderInputStream extends BitInputStream implements DataInput {

    protected boolean little;

    public ByteOrderInputStream(InputStream in) {
        this(in, false);
    }

    public ByteOrderInputStream(InputStream in, boolean littleEndian) {
        super(in);
        little = littleEndian;
    }

    public void readFully(byte b[])
        throws IOException {

    	readFully(b, 0, b.length);
    }

    public void readFully(byte b[], int off, int len)
        throws IOException {

    	if (len < 0) throw new IndexOutOfBoundsException();
    	int n = 0;
    	while (n < len) {
    	    int count = read(b, off + n, len - n);
    	    if (count < 0) throw new EOFException();
    	    n += count;
    	}
    }

    public int skipBytes(int n) throws IOException {
    	int total = 0;
    	int cur = 0;

    	while ((total<n) && ((cur = (int) skip(n-total)) > 0)) {
    	    total += cur;
    	}

    	return total;
    }

    public boolean readBoolean() throws IOException {
    	int b = readUnsignedByte();
    	return (b != 0);
    }

    public char readChar() throws IOException {
    	int b1 = readUnsignedByte();
    	int b2 = readUnsignedByte();
    	return (little) ? (char)((b1 << 8) + b2) : (char)((b2 << 8) +b1);
    }

    /**
     * Read a signed byte. */
    public byte readByte()
        throws IOException {

        byteAlign();
    	int b = read();
    	if (b < 0) throw new EOFException();
	    return (byte)b;
    }

    public byte[] readByte(int n)
        throws IOException {

        byteAlign();
        byte[] bytes = new byte[n];
        for (int i=0; i<n; i++) {
            int b = read();
    	    if (b < 0) throw new EOFException();
            bytes[i] = (byte)b;
        }
        return bytes;
    }

    /**
     * Read an unsigned byte. */
    public int readUnsignedByte()
        throws IOException {

        byteAlign();
    	int ub = read();
	    if (ub < 0) throw new EOFException();
	    return ub;
    }

    public int[] readUnsignedByte(int n)
        throws IOException {

        byteAlign();
        int[] bytes = new int[n];
        for (int i=0; i<n; i++) {
            int ub = read();
            if (ub < 0) throw new EOFException();
            bytes[i] = ub;
        }
        return bytes;
    }

    /**
     * Read a signed short.  */
    public short readShort()
        throws IOException {

        int i1 = readUnsignedByte();
        int i2 = readUnsignedByte();
        return (little) ? (short)((i2 << 8) + i1) : (short)((i1 << 8) + i2);
    }

    public short[] readShort(int n)
        throws IOException {

        short[] shorts = new short[n];
        for (int i=0; i<n; i++) {
            shorts[i] = readShort();
        }
        return shorts;
    }

    /**
     * Read an unsigned short. */
    public int readUnsignedShort()
        throws IOException {

        byteAlign();
        int i1 = readUnsignedByte();
        int i2 = readUnsignedByte();
        return (little) ? (i2 << 8) + i1 : (i1 << 8) + i2;
    }

    public int[] readUnsignedShort(int n)
        throws IOException {

        int[] shorts = new int[n];
        for (int i=0; i<n; i++) {
            shorts[i] = readUnsignedShort();
        }
        return shorts;
    }

    /**
     * Read a signed integer. */
    public int readInt()
        throws IOException {

        int i1 = readUnsignedByte();
        int i2 = readUnsignedByte();
        int i3 = readUnsignedByte();
        int i4 = readUnsignedByte();
        return (little) ? (i4 << 24) + (i3 << 16) + (i2 << 8) + i1
                        : (i1 << 24) + (i2 << 16) + (i3 << 8) + i4;
    }

    public int[] readInt(int n)
        throws IOException {

        int[] ints = new int[n];
        for (int i=0; i<n; i++) {
            ints[i] = readInt();
        }
        return ints;
    }

    /**
     * Read an unsigned integer. */
    public long readUnsignedInt()
        throws IOException {

        long i1 = readUnsignedByte();
        long i2 = readUnsignedByte();
        long i3 = readUnsignedByte();
        long i4 = readUnsignedByte();
        return (little) ? (i4 << 24) + (i3 << 16) + (i2 << 8) + i1
                        : (i1 << 24) + (i2 << 16) + (i3 << 8) + i4;
    }

    public long[] readUnsignedInt(int n)
        throws IOException {

        long[] ints = new long[n];
        for (int i=0; i<n; i++) {
            ints[i] = readUnsignedInt();
        }
        return ints;
    }

    public long readLong() throws IOException {
        long i1 = readInt();
        long i2 = readInt();
    	return (little) ? (i2 << 32) + (i1 & 0xFFFFFFFFL)
    	                : (i1 << 32) + (i2 & 0xFFFFFFFFL);
    }

    public float readFloat() throws IOException {
	    return Float.intBitsToFloat(readInt());
    }

    public double readDouble() throws IOException {
	    return Double.longBitsToDouble(readLong());
    }

    /**
     * @deprecated
     */
    public String readLine() throws IOException {
        throw new IOException("ByteOrderInputStream.readLine() is deprecated and not implemented.");
    }

    public String readString() throws IOException {
        return readUTF();
    }

    public String readUTF() throws IOException {
        return DataInputStream.readUTF(this);
    }

    public String readAsciiZString() throws IOException {
        StringBuffer buffer = new StringBuffer();
        char c = (char) readUnsignedByte();
        while (c != 0) {
            buffer.append(c);
            c = (char) readUnsignedByte();
        }
        return buffer.toString();
    }
}

// Copyright 2001, FreeHEP.
package org.freehep.util.io;

import java.io.*;
import java.util.zip.*;

/**
 *
 * IMPORTANT: inherits from InputStream rather than FilterInputStream
 * so that the correct read(byte[], int, int) method is used.
 *
 * @author Mark Donszelmann
 * @version $Id: src/main/java/org/freehep/util/io/DecompressableInputStream.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class DecompressableInputStream extends InputStream {

    private boolean decompress;
    private InflaterInputStream iis;
    private InputStream in;

    public DecompressableInputStream(InputStream input) {
        super();
        in = input;
        decompress = false;
    }

    public int read() throws IOException {
        return (decompress) ? iis.read() : in.read();
    }

    public long skip(long n) throws IOException {
        return (decompress) ? iis.skip(n) : in.skip(n);
    }

    public void startDecompressing() throws IOException {
        decompress = true;
        iis = new InflaterInputStream(in);
    }
}

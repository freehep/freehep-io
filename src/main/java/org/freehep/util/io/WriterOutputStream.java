// Copyright 2003, FreeHEP.
package org.freehep.util.io;

import java.io.*;

/**
 * The WriterOutputStream makes a Writer look like an OutputStream.
 *
 * @author Mark Donszelmann
 * @version $Id: src/main/java/org/freehep/util/io/WriterOutputStream.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class WriterOutputStream extends OutputStream {

    private Writer writer;

    public WriterOutputStream(Writer writer) {
        this.writer = writer;
    }

    public void write(int b) throws IOException {
        writer.write(b & 0xFF);
    }

    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    public void write(byte[] b, int off, int len) throws IOException {
        for (int i=0; i<len; i++) {
            writer.write(b[off+i]);
        }
    }

    public void close() throws IOException {
        writer.close();
    }

    public void flush() throws IOException {
        writer.flush();
    }
}

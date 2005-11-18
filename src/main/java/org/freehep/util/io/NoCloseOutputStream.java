// Copyright 2001, FreeHEP.
package org.freehep.util.io;

import java.io.*;

/**
 * The NoCloseOutputStream ignores the close so that one can keep writing to the underlying stream.
 *
 * @author Mark Donszelmann
 * @version $Id: src/main/java/org/freehep/util/io/NoCloseOutputStream.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class NoCloseOutputStream extends BufferedOutputStream {

    public NoCloseOutputStream(OutputStream stream) {
        super(stream);
    }

    public NoCloseOutputStream(OutputStream stream, int size) {
        super(stream, size);
    }

    public void close() throws IOException {
        flush();
    }

    public void realClose() throws IOException {
        super.close();
    }
}

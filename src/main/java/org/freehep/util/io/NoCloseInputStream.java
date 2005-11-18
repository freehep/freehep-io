// Copyright 2001, FreeHEP.
package org.freehep.util.io;

import java.io.*;

/**
 * The NoCloseInputStream ignores the close so that one can keep reading from the underlying stream.
 *
 * @author Mark Donszelmann
 * @version $Id: src/main/java/org/freehep/util/io/NoCloseInputStream.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class NoCloseInputStream extends BufferedInputStream {

    public NoCloseInputStream(InputStream stream) {
        super(stream);
    }

    public NoCloseInputStream(InputStream stream, int size) {
        super(stream, size);
    }

    public void close() throws IOException {
    }

    public void realClose() throws IOException {
        super.close();
    }


}

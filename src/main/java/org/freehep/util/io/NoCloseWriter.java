// Copyright 2003, FreeHEP.
package org.freehep.util.io;

import java.io.*;

/**
 * The NoCloseWriter ignores the close so that one can keep writing 
 * to the underlying stream.
 *
 * @author Mark Donszelmann
 * @version $Id: src/main/java/org/freehep/util/io/NoCloseWriter.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class NoCloseWriter extends BufferedWriter {

    public NoCloseWriter(Writer writer) {
        super(writer);
    }

    public NoCloseWriter(Writer writer, int size) {
        super(writer, size);
    }

    public void close() throws IOException {
        flush();
    }

    public void realClose() throws IOException {
        super.close();
    }
}

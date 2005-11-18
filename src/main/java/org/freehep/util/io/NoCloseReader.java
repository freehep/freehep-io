// Copyright 2003, FreeHEP.
package org.freehep.util.io;

import java.io.*;

/**
 * The NoCloseReader ignores the close so that one can keep reading 
 * from the underlying stream.
 *
 * @author Mark Donszelmann
 * @version $Id: src/main/java/org/freehep/util/io/NoCloseReader.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class NoCloseReader extends BufferedReader {

    public NoCloseReader(Reader reader) {
        super(reader);
    }

    public NoCloseReader(Reader reader, int size) {
        super(reader, size);
    }

    public void close() throws IOException {
    }

    public void realClose() throws IOException {
        super.close();
    }
}

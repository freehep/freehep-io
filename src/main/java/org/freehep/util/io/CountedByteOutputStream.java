// Copyright 2001, FreeHEP.
package org.freehep.util.io;

import java.io.*;

/**
 * The CountedByteOutputStream counts the number of bytes written.
 * 
 * @author Mark Donszelmann
 * @version $Id: src/main/java/org/freehep/util/io/CountedByteOutputStream.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class CountedByteOutputStream extends FilterOutputStream {

    private int count;
    
    public CountedByteOutputStream(OutputStream out) {
        super(out);
        count = 0;
    }
    
    public void write(int b) throws IOException {
        out.write(b);
        count++;
    }
    
    public void write(byte[] b) throws IOException {
        out.write(b);
        count += b.length;
    }
    
    public void write(byte[] b, int offset, int len) throws IOException {
        out.write(b, offset, len);
        count += len;
    }
    
    public int getCount() {
        return count;
    }
}

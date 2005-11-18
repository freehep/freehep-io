// Copyright 2001, FreeHEP.
package org.freehep.util.io;

import java.io.*;

/**
 * The ASCIIHexOutputStream encodes binary data as ASCII Hexadecimal.
 * The exact definition of ASCII Hex encoding can be found 
 * in the PostScript Language Reference (3rd ed.) chapter 3.13.3.
 * 
 * @author Mark Donszelmann
 * @version $Id: src/main/java/org/freehep/util/io/ASCIIHexOutputStream.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class ASCIIHexOutputStream extends FilterOutputStream implements FinishableOutputStream {

    private final static int MAX_CHARS_PER_LINE = 80;
    private int characters;
    private boolean end;
    
    public ASCIIHexOutputStream(OutputStream out) {
        super(out);
        characters = MAX_CHARS_PER_LINE;
        end = false;
    }
    
    public void write(int b) throws IOException {
        String s = Integer.toHexString(b & 0x00FF);
        switch (s.length()) {
            case 1:
                writeChar('0');
                writeChar(s.charAt(0));
                break;
            case 2:
                writeChar(s.charAt(0));
                writeChar(s.charAt(1));
                break;
            default:
                throw new IOException("ASCIIHexOutputStream: byte '"+b+"' was encoded in less than 1 or more than 2 chars"); 
        }
    }
    
    public void finish() throws IOException {
        if (!end) {
            end = true;
            writeChar('>');
            flush();
            if (out instanceof FinishableOutputStream) {
                ((FinishableOutputStream)out).finish();
            }
        }        
    }
    
    public void close() throws IOException {
        finish();
        super.close();
    }

    private void writeChar(int b) throws IOException {
        if (characters == 0) {
            characters = MAX_CHARS_PER_LINE;
            super.write('\n');
        } 
        characters--;
        super.write(b);
    }
}

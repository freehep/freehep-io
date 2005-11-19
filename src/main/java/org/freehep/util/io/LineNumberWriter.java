// Copyright 2003, FreeHEP.
package org.freehep.util.io;

import java.io.*;
import java.util.*;

/**
 * Counts line numbers, based on the first cr-lf, cr or lf it finds. Informs a
 * listener when the linenumber exceeds a threshold.
 *
 * Listeners can only be informed from the second line only.
 *
 * @author Mark Donszelmann
 * @version $Id: src/main/java/org/freehep/util/io/LineNumberWriter.java effd8b4f3966 2005/11/19 07:52:18 duns $
 */
public class LineNumberWriter extends Writer {

    private final static int UNKNOWN = 0;
    private final static int CR      = 1;
    private final static int CRLF    = 2;
    private final static int LF      = 3;
    private final static int LFCR    = 4;

    private int lineSeparator = UNKNOWN;
    private Writer out;
    private int lineNo = 0;
    private LineNumberListener listener;
    private int lineNoLimit;
    private int previous = -1;

    public LineNumberWriter(Writer out) {
        this.out = out;
    }

    public void write(char cbuf[]) throws IOException {
	    write(cbuf, 0, cbuf.length);
    }

    public void write(char cbuf[], int off, int len) throws IOException {
        for (int i=0; i<len; i++) {
            write(cbuf[off+i]);
        }
    }

    public void write(String str) throws IOException {
    	write(str, 0, str.length());
    }

    public void write(String str, int off, int len) throws IOException {
        for (int i=0; i<len; i++) {
            write(str.charAt(off+i));
        }
    }

	public void write(int c) throws IOException {
	    boolean newLine = false;
        synchronized(lock) {
            out.write(c);

            switch(lineSeparator) {
                default:
                case UNKNOWN:
                    switch (previous) {
                        case '\r':
                            lineNo++;
                            lineSeparator = (c == '\n') ? CRLF : CR;
                            if (c == '\r') lineNo++;
                            break;
                        case '\n':
                            lineNo++;
                            lineSeparator = (c == '\r') ? LFCR : LF;
                            if (c == '\n') lineNo++;
                            break;
                        default:
                            break;
                    }
                    break;
                case CR:
                    if (c == '\r') {
                        lineNo++;
                        newLine = true;
                    }
                    break;
                case CRLF:
                    if ((previous == '\r') && (c == '\n')) {
                        lineNo++;
                        newLine = true;
                    }
                    break;
                case LF:
                    if (c == '\n') {
                        lineNo++;
                        newLine = true;
                    }
                    break;
                case LFCR:
                    if ((previous == '\n') && (c == '\r')) {
                        lineNo++;
                        newLine = true;
                    }
                    break;
            }
            previous = c;
        }

		if ((listener != null) && newLine && (lineNo >= lineNoLimit)) {
		    listener.lineNumberReached(new LineNumberEvent(this, lineNo));
		}
	}

    public void close() throws IOException {
        out.close();
    }

    public void flush() throws IOException {
        out.flush();
    }

    /**
     * Returns the line number that is currently being written.
     */
    public int getLineNumber() {
        return lineNo;
    }

    public void setLineNumber(int lineNo) {
        this.lineNo = lineNo;
    }

    public void addLineNumberListener(LineNumberListener listener, int lineNoLimit)
            throws TooManyListenersException {
        if (this.listener != null) throw new TooManyListenersException();
        if (lineNoLimit < 2) throw new IllegalArgumentException("LineNoLimit cannot be less than 2");

        this.listener = listener;
        this.lineNoLimit = lineNoLimit;
    }

    public static interface LineNumberListener extends EventListener {
        public void lineNumberReached(LineNumberEvent event);
    }

    public static class LineNumberEvent extends EventObject {
        /**
		 * 
		 */
		private static final long serialVersionUID = 2821724279014031198L;
		private int lineNo;

        public LineNumberEvent(Object source, int lineNo) {
            super(source);
            this.lineNo = lineNo;
        }

        public int getLineNumber() {
            return lineNo;
        }

        public String toString() {
            return "LineNumberEvent: line="+lineNo+"; "+getSource().toString();
        }
    }
}

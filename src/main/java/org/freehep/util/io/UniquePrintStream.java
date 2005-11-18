// Copyright 2001, FreeHEP.
package org.freehep.util.io;

import java.io.*;
import java.util.*;

/**
 * The UniquePrintStream keeps Strings buffered in sorted order,
 * but any duplicates are removed. This stream can be used to print
 * error messages exactly once. When finish is called all messages are
 * printed.
 *
 * It only acts on the println(String) method, any other method will
 * print directly.
 *
 * @author Mark Donszelmann
 * @version $Id: src/main/java/org/freehep/util/io/UniquePrintStream.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class UniquePrintStream extends PrintStream implements FinishableOutputStream {

    SortedSet msg = new TreeSet();

    public UniquePrintStream(OutputStream out) {
        super(out);
    }

    public void println(String s) {
        synchronized(this) {
            msg.add(s);
        }
    }

    public void finish() {
        for (Iterator i=msg.iterator(); i.hasNext(); ) {
            String s = (String)i.next();
            super.println(s);
        }
        msg = new TreeSet();
    }
}

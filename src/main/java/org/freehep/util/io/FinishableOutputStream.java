// Copyright 2001, FreeHEP.
package org.freehep.util.io;

import java.io.*;

/**
 * The FinishableOutputStream allows a generic way of calling finish on an
 * output stream without closing it.
 * 
 * @author Mark Donszelmann
 * @version $Id: src/main/java/org/freehep/util/io/FinishableOutputStream.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public interface FinishableOutputStream {
    public void finish() throws IOException;
}

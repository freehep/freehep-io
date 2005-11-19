// Copyright 2001-2005, FreeHEP.
package org.freehep.util.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;

/**
 * The FlateOutputStream uses the Deflate mechanism to compress data.
 * The exact definition of Deflate encoding can be found
 * in the PostScript Language Reference (3rd ed.) chapter 3.13.3.
 *
 * @author Mark Donszelmann
 * @version $Id: src/main/java/org/freehep/util/io/FlateOutputStream.java effd8b4f3966 2005/11/19 07:52:18 duns $
 */
public class FlateOutputStream extends DeflaterOutputStream implements FinishableOutputStream {

    public FlateOutputStream(OutputStream out) {
        super(out);
    }

    public void finish() throws IOException {
        super.finish();
        if (out instanceof FinishableOutputStream) {
            ((FinishableOutputStream)out).finish();
        }
    }
}

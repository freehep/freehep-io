// Copyright 2001-2003, FreeHEP.
package org.freehep.util.io;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.InflaterInputStream;

/**
 * The FlateInputStream uses the Deflate mechanism to compress data.
 * The exact definition of Deflate encoding can be found
 * in the PostScript Language Reference (3rd ed.) chapter 3.13.3.
 *
 * @author Mark Donszelmann
 * @version $Id: src/main/java/org/freehep/util/io/FlateInputStream.java effd8b4f3966 2005/11/19 07:52:18 duns $
 */
public class FlateInputStream extends InflaterInputStream {

    public FlateInputStream(InputStream in) {
        super(in);
    }

    public Image readImage() throws IOException {
// FIXME
        return null;
    }
}

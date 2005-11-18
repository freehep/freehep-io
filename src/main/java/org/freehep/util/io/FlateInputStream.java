// Copyright 2001-2003, FreeHEP.
package org.freehep.util.io;

import java.awt.*;
import java.io.*;
import java.util.zip.*;
import javax.imageio.*;

/**
 * The FlateInputStream uses the Deflate mechanism to compress data.
 * The exact definition of Deflate encoding can be found
 * in the PostScript Language Reference (3rd ed.) chapter 3.13.3.
 *
 * @author Mark Donszelmann
 * @version $Id: src/main/java/org/freehep/util/io/FlateInputStream.java b2aff02d4920 2005/11/18 22:58:46 duns $
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

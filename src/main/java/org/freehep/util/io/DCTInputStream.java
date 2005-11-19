// Copyright 2003, FreeHEP.
package org.freehep.util.io;

import java.awt.*;
import java.io.*;
import javax.imageio.*;

/**
 * Reads images from a JPEG Stream, but only images.
 *
 * @author Mark Donszelmann
 * @version $Id: src/main/java/org/freehep/util/io/DCTInputStream.java effd8b4f3966 2005/11/19 07:52:18 duns $
 */
public class DCTInputStream extends FilterInputStream {

    public DCTInputStream(InputStream input) {
        super(input);
    }

    public int read() throws IOException {
        throw new IOException(getClass()+": read() not implemented, use readImage().");
    }

    public Image readImage() throws IOException {
        return ImageIO.read(new NoCloseInputStream(this));
    }
}

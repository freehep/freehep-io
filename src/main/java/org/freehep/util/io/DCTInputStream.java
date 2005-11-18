// Copyright 2003, FreeHEP.
package org.freehep.util.io;

import java.awt.*;
import java.io.*;
import javax.imageio.*;

/**
 * Reads images from a JPEG Stream, but only images.
 *
 * IMPORTANT: inherits from InputStream rather than FilterInputStream
 * so that the correct read(byte[], int, int) method is used.
 *
 * @author Mark Donszelmann
 * @version $Id: src/main/java/org/freehep/util/io/DCTInputStream.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class DCTInputStream extends InputStream {

    private InputStream input;

    public DCTInputStream(InputStream input) {
        super();
    	this.input = input;
    }

    public int read() throws IOException {
        throw new IOException(getClass()+": read() not implemented, use readImage().");
    }

    public Image readImage() throws IOException {
        return ImageIO.read(new NoCloseInputStream(this));
    }
}

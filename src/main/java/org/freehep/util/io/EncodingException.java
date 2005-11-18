// Copyright 2001, FreeHEP.
package org.freehep.util.io;

import java.io.IOException;

/**
 * Encoding Exception for any of the encoding streams.
 *
 * @author Mark Donszelmann
 * @version $Id: src/main/java/org/freehep/util/io/EncodingException.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class EncodingException extends IOException {

    public EncodingException(String msg) {
        super(msg);
    }
}

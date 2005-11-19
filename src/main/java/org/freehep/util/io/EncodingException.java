// Copyright 2001, FreeHEP.
package org.freehep.util.io;

import java.io.IOException;

/**
 * Encoding Exception for any of the encoding streams.
 *
 * @author Mark Donszelmann
 * @version $Id: src/main/java/org/freehep/util/io/EncodingException.java effd8b4f3966 2005/11/19 07:52:18 duns $
 */
public class EncodingException extends IOException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8496816190751796701L;

	public EncodingException(String msg) {
        super(msg);
    }
}

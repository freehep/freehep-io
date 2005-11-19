// Copyright 2001, FreeHEP.
package org.freehep.util.io;

import java.io.IOException;

/**
 * Exception for the TaggedOutputStream. Signals that the user tries to write
 * a tag which is not defined at this version or below.
 *
 * @author Mark Donszelmann
 * @author Charles Loomis
 * @version $Id: src/main/java/org/freehep/util/io/UndefinedTagException.java effd8b4f3966 2005/11/19 07:52:18 duns $
 */
public class UndefinedTagException extends IOException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7504997713135869344L;

	public UndefinedTagException() {
        super();
    }
    
    public UndefinedTagException(String msg) {
        super(msg);
    }

    public UndefinedTagException(int code) {
        super("Code: ("+code+")");
    }
}

// Copyright 2001, FreeHEP.
package org.freehep.util.io;

import java.io.IOException;

/**
 * Exception for the TaggedInputStream. Signals that the inputstream contains
 * more bytes than the stream has read for this tag.
 *
 * @author Mark Donszelmann
 * @author Charles Loomis
 * @version $Id: src/main/java/org/freehep/util/io/IncompleteTagException.java effd8b4f3966 2005/11/19 07:52:18 duns $
 */
public class IncompleteTagException extends IOException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7808675150856818588L;
	private Tag tag;
    private byte[] rest;

    public IncompleteTagException(Tag tag, byte[] rest) {
        super("Tag "+tag+" contains "+rest.length+" unread bytes");
        this.tag = tag;
        this.rest = rest;
    }

    public Tag getTag() {
        return tag;
    }

    public byte[] getBytes() {
        return rest;
    }
}

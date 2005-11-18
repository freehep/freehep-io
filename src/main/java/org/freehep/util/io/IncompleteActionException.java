// Copyright 2001, FreeHEP.
package org.freehep.util.io;

import java.io.IOException;

/**
 * Exception for the TaggedInputStream. Signals that the inputstream contains
 * more bytes than the stream has read for this action.
 *
 * @author Mark Donszelmann
 * @author Charles Loomis
 * @version $Id: src/main/java/org/freehep/util/io/IncompleteActionException.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class IncompleteActionException extends IOException {

    private Action action;
    private byte[] rest;

    public IncompleteActionException(Action action, byte[] rest) {
        super("Action "+action+" contains "+rest.length+" unread bytes");
        this.action = action;
        this.rest = rest;
    }

    public Action getAction() {
        return action;
    }

    public byte[] getBytes() {
        return rest;
    }
}

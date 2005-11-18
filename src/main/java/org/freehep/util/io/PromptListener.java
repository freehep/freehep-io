// Copyright 2002, FreeHEP.
package org.freehep.util.io;

import java.io.*;

/**
 * Listener to inform that Prompt of the PromptInputStream has been found.
 *
 * @author Mark Donszelmann
 * @version $Id: src/main/java/org/freehep/util/io/PromptListener.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public interface PromptListener {

    /**
     * Prompt was found, and can now be read.
     */
    public void promptFound(RoutedInputStream.Route route) throws IOException;
}

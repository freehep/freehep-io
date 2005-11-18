// Copyright 2001, FreeHEP.
package org.freehep.util.io;

import java.io.IOException;

/**
 * @author Mark Donszelmann
 * @version $Id: src/main/java/org/freehep/util/io/TaggedOutput.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public interface TaggedOutput {

    /**
     * Write a tag. */
    public void writeTag(Tag tag) throws IOException;
    
    public void close() throws IOException;
}

// Copyright 2001, FreeHEP.
package org.freehep.util.io;

/**
 * Keeps the tagID and Length of a specific tag. To be used in the InputStream
 * to return the tagID and Length, and in the OutputStream to write them.
 *
 * @author Mark Donszelmann
 * @author Charles Loomis
 * @version $Id: src/main/java/org/freehep/util/io/TagHeader.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class TagHeader {

    int tagID;
    long length;

    public TagHeader(int tagID, long length) {
        this.tagID = tagID;
        this.length = length;
    }
    
    public void setTag(int tagID) {
        this.tagID = tagID;
    }
    
    public int getTag() {
        return tagID;
    }
    
    public void setLength(long length) {
        this.length = length;
    }
    
    public long getLength() {
        return length;
    }
}

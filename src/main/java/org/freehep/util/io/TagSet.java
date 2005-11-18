// Copyright 2001, FreeHEP.
package org.freehep.util.io;

import java.io.InputStream;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

/**
 * Class to keep registered Tags, 
 * which should be used by the TaggedIn/OutputStream.
 *
 * A set of recognized Tags can be added to this class.
 * A concrete implementation of this stream should install all allowed tags.
 *
 * @author Mark Donszelmann
 * @author Charles Loomis
 * @version $Id: src/main/java/org/freehep/util/io/TagSet.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class TagSet {

    /**
     * This holds the individual tags. */
    protected Map tags;
    
    /**
     * The default tag handler. */
    protected Tag defaultTag;

    public TagSet() {
        // Initialize the tag classes.
        defaultTag = new UndefinedTag();
        tags = new HashMap();
    }

    /**
     * Add a new tag to this set.  If the
     * tagID returned is the DEFAULT_TAG, 
     * then the default handler is
     * set to the given handler. 
     */
    public void addTag(Tag tag) {
        int id = tag.getTag();
        if (id != Tag.DEFAULT_TAG) {
            tags.put(new Integer(id),tag);
        } else {
            defaultTag = tag;
        }
    }
    
    public Tag get(int tagID) {
        Tag tag = (Tag)tags.get(new Integer(tagID));
        if (tag == null) tag = defaultTag;
        return tag;
    }   

    public boolean exists(int tagID) {
        return (tags.get(new Integer(tagID)) != null);
    }       
}

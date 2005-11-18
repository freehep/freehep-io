// Copyright 2001, FreeHEP.
package org.freehep.util.io;

import java.io.InputStream;
import java.io.IOException;

/**
 * Class to read Tagged blocks from a Stream. The tagged blocks (Tags) contain
 * a tagID and a Length, so that known and unknown tags can be read and written
 * (using the TaggedOutputStream).
 * The stream also allows to read Actions, which again come with a actionCode and
 * a length.
 *
 * A set of recognized Tags and Actions can be added to this stream.
 * A concrete implementation of this stream should decode/read the TagHeader.
 * All Concrete tags should be inherited from the Tag class and implement
 * their read methods.
 *
 * @author Mark Donszelmann
 * @author Charles Loomis
 * @version $Id: src/main/java/org/freehep/util/io/TaggedInputStream.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public abstract class TaggedInputStream extends ByteCountInputStream {

    /**
     * Set of tags that can be used by this Stream
     */
    protected TagSet tagSet;

    /**
     * Set of actions that can be used by this Stream
     */
    protected ActionSet actionSet;

    public TaggedInputStream(InputStream in, TagSet tagSet, ActionSet actionSet) {
        this(in, tagSet, actionSet, false);
    }

    public TaggedInputStream(InputStream in, TagSet tagSet, ActionSet actionSet, boolean littleEndian) {
        super(in, littleEndian, 8);

        this.tagSet = tagSet;
        this.actionSet = actionSet;
    }

    public void addTag(Tag tag) {
        tagSet.addTag(tag);
    }

    /**
     * Decodes and returns the TagHeader, which includes a TagID and a length
     */
    protected abstract TagHeader readTagHeader() throws IOException;

    /**
     * Read a tag. */
    public Tag readTag()
        throws IOException {

        TagHeader header = readTagHeader();
        if (header == null) return null;

        int size = (int) header.getLength();

        // Look up the proper tag.
        Tag tag = tagSet.get(header.getTag());

        // set max tag length and read tag
        pushBuffer(size);
        tag = tag.read(header.getTag(), this, size);
        byte[] rest = popBuffer();

        // read non-read part of tag
        if (rest != null) {
            throw new IncompleteTagException(tag, rest);
        }
        return tag;
    }

    public void addAction(Action action) {
        actionSet.addAction(action);
    }

    /**
     * Decodes and returns the ActionHeader, which includes an actionCode and a length
     */
    protected abstract ActionHeader readActionHeader() throws IOException;

    public Action readAction()
        throws IOException {

        ActionHeader header = readActionHeader();
        if (header == null) return null;

        int size = (int) header.getLength();

        // Look up the proper action.
        Action action = actionSet.get(header.getAction());

        pushBuffer(size);
        action = action.read(header.getAction(), this, size);
        byte[] rest = popBuffer();

        if (rest != null) {
            throw new IncompleteActionException(action, rest);
        }
        return action;
    }

}

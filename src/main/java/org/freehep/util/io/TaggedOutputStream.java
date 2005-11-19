// Copyright 2001, FreeHEP.
package org.freehep.util.io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Class to write Tagged blocks to a Stream. The tagged blocks (Tags) contain
 * a tagID and a Length, so that known and unknown tags (read with the
 * TaggedInputStream) can again be written.
 * The stream also allows to write Actions, which again come with a actionCode and
 * a length.
 *
 * A concrete implementation of this stream should encode/write the TagHeader.
 * All Concrete tags should be inherited from the Tag class and implement
 * their write methods.
 *
 * @author Mark Donszelmann
 * @author Charles Loomis
 * @version $Id: src/main/java/org/freehep/util/io/TaggedOutputStream.java effd8b4f3966 2005/11/19 07:52:18 duns $
 */
public abstract class TaggedOutputStream
        extends ByteCountOutputStream
        implements TaggedOutput {
    /**
     * Set of tags that can be used by this Stream
     */
    protected TagSet tagSet;

    /**
     * Set of actions that can be used by this Stream
     */
    protected ActionSet actionSet;

    public TaggedOutputStream(OutputStream out, TagSet tagSet, ActionSet actionSet) {
        this(out, tagSet, actionSet, false);
    }

    public TaggedOutputStream(OutputStream out,  TagSet tagSet, ActionSet actionSet, boolean littleEndian) {
        super(out, littleEndian);

        this.tagSet = tagSet;
        this.actionSet = actionSet;
    }

    /**
     * Writes the TagHeader, which includes a TagID and a length
     */
    protected abstract void writeTagHeader(TagHeader header) throws IOException;

    /**
     * Specifies tag alignment
     * 1 byte
     * 2 short
     * 4 int
     * 8 long
     */
    protected int getTagAlignment() {
        return 1;
    }

    /**
     * Write a tag.
     */
    public void writeTag(Tag tag)
        throws IOException {

        int tagID = tag.getTag();

        if (!tagSet.exists(tagID)) throw new UndefinedTagException(tagID);

        pushBuffer();
        tag.write(tagID, this);
        int align = getTagAlignment();
        int pad = (align - (getBufferLength() % align)) % align;
        for (int i=0; i<pad; i++) {
            write(0);
        }
        int len = popBuffer();
        TagHeader header = new TagHeader(tagID, len);
        writeTagHeader(header);
        append();
    }

    /**
     * Writes the ActionHeader, which includes an actionCode and a length
     */
    protected abstract void writeActionHeader(ActionHeader header) throws IOException;

    public void writeAction(Action action)
        throws IOException {
        // handle end of action stream
        if (action == null) {
            writeByte(0);
            return;
        }

        int actionCode = action.getCode();

        if (!actionSet.exists(actionCode)) throw new UndefinedTagException(actionCode);

        pushBuffer();
        action.write(actionCode, this);
        int len = popBuffer();
        ActionHeader header = new ActionHeader(actionCode, len);
        writeActionHeader(header);
        append();
    }

}

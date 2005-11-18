// Copyright 2001, FreeHEP.
package org.freehep.util.io;

import java.io.*;

/**
 * Constants for the ASCII85 encoding.
 *
 * @author Mark Donszelmann
 * @version $Id: src/main/java/org/freehep/util/io/ASCII85.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public interface ASCII85 {

    public final static int MAX_CHARS_PER_LINE = 80;
    
    public static long a85p1 = 85;
    public static long a85p2 = a85p1*a85p1;
    public static long a85p3 = a85p2*a85p1;
    public static long a85p4 = a85p3*a85p1;
    
}

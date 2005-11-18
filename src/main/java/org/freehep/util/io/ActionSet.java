// Copyright 2001, FreeHEP.
package org.freehep.util.io;

import java.io.InputStream;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

/**
 * Class to keep registered Actions, 
 * which should be used by the TaggedIn/OutputStream.
 *
 * A set of recognized Actions can be added to this class.
 * A concrete implementation of this stream should install all allowed actions.
 *
 * @author Mark Donszelmann
 * @author Charles Loomis
 * @version $Id: src/main/java/org/freehep/util/io/ActionSet.java b2aff02d4920 2005/11/18 22:58:46 duns $
 */
public class ActionSet {
    
    /**
     * This holds the individual actions. */
    protected Map actions;
    protected Action defaultAction;

    public ActionSet() {
        actions = new HashMap();
        defaultAction = new Action.Unknown();        
    }

    public void addAction(Action action) {
        actions.put(new Integer(action.getCode()), action);
    }
    
    public Action get(int actionCode) {
        Action action = (Action)actions.get(new Integer(actionCode));
        if (action == null) action = defaultAction;
        return action;
    }
    
    public boolean exists(int actionCode) {
        return (actions.get(new Integer(actionCode)) != null);
    }       
}

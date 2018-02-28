package com.acn.submenu;
import org.eclipse.ui.IStartup;

import com.acn.submenu.popup.actions.OpenInBrowserHandler;


public class MyStartUp implements IStartup {

    @Override
    public void earlyStartup() {
        // Initial the action
        new OpenInBrowserHandler();
        
    }
}
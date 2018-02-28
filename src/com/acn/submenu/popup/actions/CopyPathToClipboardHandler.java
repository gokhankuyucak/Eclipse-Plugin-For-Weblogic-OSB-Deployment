package com.acn.submenu.popup.actions;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.handlers.HandlerUtil;


import com.acn.osb.customtools.browseroperations.BrowserOperations;
import com.acn.osb.customtools.utils.CustomToolsHelper;
import com.acn.osb.customtools.variables.Resource;
import com.acn.osb.customtools.variables.Settings;
import com.acn.submenu.Activator;
import com.acn.submenu.Utils;

public class CopyPathToClipboardHandler extends AbstractHandler {
	

	@Override
	  public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			Resource res = Utils.getResourceFromAction();
			if (res == null || res.path == null || res.path.length() == 0)
				return null;
			Settings settings=Activator.getSettings();			
			BrowserOperations browserOperations=new BrowserOperations(settings);
			String result = browserOperations.getServerPath(res);
			System.out.println("copy:" +res.toString());		
			CustomToolsHelper.copyStringToClipboard(result);

		} catch (Exception e) {
			MessageDialog.openInformation(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Error", e.toString());
		}
		return null;
	}


}

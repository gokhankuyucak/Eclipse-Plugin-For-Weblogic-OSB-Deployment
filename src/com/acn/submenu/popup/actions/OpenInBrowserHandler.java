package com.acn.submenu.popup.actions;

import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;

import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.menus.UIElement;


import com.acn.osb.customtools.browseroperations.BrowserOperations;
import com.acn.osb.customtools.variables.Resource;
import com.acn.osb.customtools.variables.Resource.ResourceFileType;
import com.acn.osb.customtools.variables.Settings;
import com.acn.submenu.Activator;

import com.acn.submenu.Utils;


public class OpenInBrowserHandler extends AbstractHandler implements IElementUpdater{
	
	String serverName="";
	
	@Override
	  public Object execute(ExecutionEvent event) throws ExecutionException {
		
		setServerName( event.getParameter("com.acn.submenu.popup.showInServer.serverID"));
		try {
			Resource res = Utils.getResourceFromAction();
			if (res == null || res.path == null || res.path.length() == 0 ||res.fileType==ResourceFileType.OTHER) 
				return null;
			Settings settings=Activator.getSettings();		
			settings.setServerName(serverName);
			BrowserOperations browserOperations=new BrowserOperations(settings);
			String result = browserOperations.getServerPath(serverName, res);

		} catch (Exception e) {
			MessageDialog.openInformation(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Error", e.toString());
		}
	    return null;
	  }
	
	public void updateElement(UIElement element, Map parameters) {
		String serverIndex = (String) parameters.get("com.acn.submenu.popup.showInServer.serverID");
		setServerName(serverIndex);
		element.setText("Test on http://" + serverName);

	}
	private void setServerName(String serverIndex)
	{
		serverName=Activator.getServerNameFromId(serverIndex);			
	}
	
	
	
}

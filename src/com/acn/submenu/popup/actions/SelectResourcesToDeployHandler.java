package com.acn.submenu.popup.actions;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.handlers.HandlerUtil;

import com.acn.osb.customtools.variables.Resource;
import com.acn.osb.customtools.variables.Settings;
import com.acn.submenu.Activator;
import com.acn.submenu.Utils;
import com.acn.osb.customtools.views.*;

public class SelectResourcesToDeployHandler extends AbstractHandler {
	String serverName="";
	@Override
	  public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			Resource res = Utils.getResourceFromAction();
			if (res == null || res.path == null || res.path.length() == 0)
				return null;			
			
			Settings settings=Activator.getSettings();		
			FileSelectForm myForm=new FileSelectForm(res.osbApplicationsPath(),settings);
			myForm.setVisible(true);

		} catch (Exception e) {
			MessageDialog.openInformation(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Error", e.toString());
		}
		return null;
	}	
}

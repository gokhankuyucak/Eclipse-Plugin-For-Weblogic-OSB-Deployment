package com.acn.submenu.popup.actions;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.menus.UIElement;

import com.acn.osb.customtools.fastmenuoperations.FastMenuOperations;
import com.acn.osb.customtools.jaroperations.GenerateJar;
import com.acn.osb.customtools.utils.CustomToolsHelper;
import com.acn.osb.customtools.variables.Resource;
import com.acn.osb.customtools.variables.Settings;
import com.acn.submenu.Activator;
import com.acn.submenu.Utils;

public class CopyToJSONHandler extends AbstractHandler {

@Override
public Object execute(final ExecutionEvent event) throws ExecutionException {
try {

	BusyIndicator.showWhile(HandlerUtil.getActiveWorkbenchWindow(event)
			.getShell().getDisplay(), new Runnable() {
		public void run() {
			Resource res = Utils.getResourceFromAction();
			if (res != null && res.path != null
					&& res.path.length() > 0) {
				
				ArrayList<Resource> resources = new ArrayList<Resource>();
				resources.add(res);
				Settings settings = Activator.getSettings();
				
				FastMenuOperations operations=new FastMenuOperations(settings, res);

			
				MessageDialog.openInformation(HandlerUtil
						.getActiveWorkbenchWindow(event).getShell(),
						"DeploymentStatus", "Data copied.");
			}
		}
	});
} catch (Exception e) {
	MessageDialog.openInformation(
			HandlerUtil.getActiveWorkbenchWindow(event).getShell(),
			"Error", e.toString());
} finally {

}
return null;
}

}
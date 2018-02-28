package com.acn.submenu.popup.actions;

import java.awt.Cursor;
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

import com.acn.osb.customtools.jaroperations.GenerateJar;
import com.acn.osb.customtools.utils.CustomToolsHelper;
import com.acn.osb.customtools.variables.Resource;
import com.acn.osb.customtools.variables.Settings;
import com.acn.submenu.Activator;
import com.acn.submenu.Utils;

public class CreateJarHandler extends AbstractHandler implements
		IElementUpdater {
	String serverName = "";

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		try {

			BusyIndicator.showWhile(HandlerUtil.getActiveWorkbenchWindow(event)
					.getShell().getDisplay(), new Runnable() {
				public void run() {
					Resource res = Utils.getResourceFromAction();
					if (res != null && res.path != null
							&& res.path.length() > 0) {
						setServerName( event.getParameter("com.acn.submenu.popup.buildJar.serverID"));
						ArrayList<Resource> resources = new ArrayList<Resource>();
						resources.add(res);
						Settings settings = Activator.getSettings();
						settings.setServerName(serverName);
						GenerateJar generateJar = new GenerateJar(settings);
						Boolean deploymentStatus = generateJar
								.createSbConfigJar(resources);

						String logMessage;
						if (deploymentStatus) {
							logMessage = CustomToolsHelper
									.getDeploymentStatusSuccess();
						} else {
							logMessage = CustomToolsHelper
									.getDeploymentStatusError(settings);
						}
						MessageDialog.openInformation(HandlerUtil
								.getActiveWorkbenchWindow(event).getShell(),
								"DeploymentStatus", logMessage);
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

	public void updateElement(UIElement element, Map parameters) {
		String serverIndex = (String) parameters.get("com.acn.submenu.popup.buildJar.serverID");
		setServerName(serverIndex);
		element.setText("Deploy Selected Resources to " + serverName);
	}

	private void setServerName(String serverIndex) {
		serverName = Activator.getServerNameFromId(serverIndex);
	}

}

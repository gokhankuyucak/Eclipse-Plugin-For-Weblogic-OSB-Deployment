package com.acn.submenu;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;

import org.eclipse.swt.widgets.*;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class SubMenuPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public SubMenuPreferencePage() {
		super(FieldEditorPreferencePage.GRID);

		// Set the preference store for the preference page.
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		setPreferenceStore(store);
	}

	@Override
	protected void createFieldEditors() {
		// TODO Auto-generated method stub
		StringFieldEditor serverList = new StringFieldEditor(
				Activator.SERVERLIST, "&Server List:http://",
				getFieldEditorParent());
		addField(serverList);
		FileFieldEditor browserPath = new FileFieldEditor(
				Activator.BROWSERADDRESS, "&Default Browser Path:", true,
				getFieldEditorParent());
		addField(browserPath);
		FileFieldEditor configJarPath = new FileFieldEditor(
				Activator.CONFIGJARFOLDERPATH, "&Config.jar Path:", true,
				getFieldEditorParent());
		addField(configJarPath);
		FileFieldEditor userName = new FileFieldEditor(
				Activator.USERNAME, "&UserName:", true,
				getFieldEditorParent());
		addField(userName);
		FileFieldEditor password = new FileFieldEditor(
				Activator.PASSWORD, "&password:", true,
				getFieldEditorParent());
		addField(password);
	}

	@Override
	public void init(IWorkbench arg0) {
		// TODO Auto-generated method stub

	}

}
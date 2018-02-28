package com.acn.submenu;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.acn.osb.customtools.variables.Settings;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.acn.submenu"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	// The identifiers for the preferences
	public static final String BROWSERADDRESS = "Browser Address";
	public static final String CONFIGJARFOLDERPATH="Config Jar Folder Path";
	public static final String USERNAME="User Name";
	public static final String PASSWORD="Password";
	public static final String SERVERLIST="Server List";

	// The default values for the preferences
	public static final String DEFAULT_SERVERLIST = "server1:port;server2:port";
	public static final String DEFAULT_BROWSER = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
	public static final String DEFAULT_CONFIGJARPATH="D:\\SoaSuite\\Tools\\configjar";
	public static final String DEFAULT_USERNAME = "weblogic";
	public static final String DEFAULT_PASSWORD = "weblogic";

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}

	protected void initializeDefaultPreferences(IPreferenceStore store) {
		store.setDefault(SERVERLIST, DEFAULT_SERVERLIST);
		store.setDefault(BROWSERADDRESS, DEFAULT_BROWSER);
		store.setDefault(CONFIGJARFOLDERPATH, DEFAULT_CONFIGJARPATH);
		store.setDefault(USERNAME,DEFAULT_USERNAME);
		store.setDefault(PASSWORD, DEFAULT_PASSWORD);

	}

	public String getDefaultServerList() {
		return getPreferenceStore().getDefaultString(SERVERLIST);
	}

	public String getServerList() {
		return getPreferenceStore().getString(SERVERLIST);
	}

	public void setServerList(String element) {

		getPreferenceStore().setValue(SERVERLIST, element);
	}



	public String getDefaultBrowser() {
		return getPreferenceStore().getDefaultString(BROWSERADDRESS);
	}

	public String getBrowser() {
		return getPreferenceStore().getString(BROWSERADDRESS);
	}

	public void setBrowser(String element) {

		getPreferenceStore().setValue(BROWSERADDRESS, element);
	}
	public String getDefaultConfigJar() {
		return getPreferenceStore().getDefaultString(CONFIGJARFOLDERPATH);
	}

	public String getConfigJar() {
		return getPreferenceStore().getString(CONFIGJARFOLDERPATH);
	}

	public void setConfigJar(String element) {

		getPreferenceStore().setValue(CONFIGJARFOLDERPATH, element);
	}

	public String getUserName() {
		return getPreferenceStore().getString(USERNAME);
	}

	public void setUserName(String element) {

		getPreferenceStore().setValue(USERNAME, element);
	}
	
	public String getPassword() {
		return getPreferenceStore().getString(PASSWORD);
	}

	public void setPassword(String element) {

		getPreferenceStore().setValue(PASSWORD, element);
	}
	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
	public static Settings getSettings()
	{
		Settings settings=new Settings();
		settings.setServerList(plugin.getServerList());
		settings.setBrowserPath(plugin.getBrowser());
		settings.setConfigJarPath(plugin.getConfigJar());		
		settings.setUserName(plugin.getUserName());
		settings.setPassword(plugin.getPassword());
		return settings;
	}
	public static String getServerNameFromId(String index)
	{
		String serverName;
		try
		{
		int serverId=Integer.parseInt(index);
		serverName=plugin.getServerList().split(";")[serverId-1];
		}
		catch (Exception ex)
		{
			serverName="Error occured getting server list from paramaters";
		}
		
	   return serverName;
	}
}

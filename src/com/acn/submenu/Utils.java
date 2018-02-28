package com.acn.submenu;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.ui.ISelectionService;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import com.acn.osb.customtools.utils.CustomToolsHelper;
import com.acn.osb.customtools.variables.Resource;

public class Utils {

	public static Resource getResourceFromAction() {
		Resource res=null;

		ISelectionService service = Activator.getDefault().getWorkbench()
				.getActiveWorkbenchWindow().getSelectionService();
		IStructuredSelection structured = (IStructuredSelection) service
				.getSelection();
		Object obj = structured.getFirstElement();		
		String fullPath="";
	
		try {
			if (obj instanceof IFile) {
				IFile file = (IFile) obj;
				IPath iPath = file.getLocation();
				File newFile = iPath.toFile();

				fullPath=file.getRawLocation().toOSString();
				res=CustomToolsHelper.getResourceFromFile(newFile,fullPath);
			
			} else if (obj instanceof IFolder) {
				IFolder folder = (IFolder) obj;
				IPath iFolder = folder.getFullPath();				
				File newFile = iFolder.toFile();				
				fullPath=folder.getRawLocation().toOSString();
				res=CustomToolsHelper.getResourceFromFolder(newFile,fullPath);
			}
			
			

		} catch (Exception e) {
			res = new Resource("GetResourceFromAction Error", obj.getClass()
					.getName(), "","other","FullPath Error");
		}
		
		return res;
	}

}

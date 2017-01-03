package com.iris.module;

/**
 * 
 */


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author sbali
 *
 */
@XmlRootElement(name="MasterData")
public class MasterData {
	
private List<ChildElement> childElement = new ArrayList<ChildElement>();

/**
 * @return the element
 */
public List<ChildElement> getChildElement() {
	return childElement;
}

/**
 * @param element the element to set
 */

public void setChildElement(List<ChildElement> childElement) {
	this.childElement = childElement;
}




}

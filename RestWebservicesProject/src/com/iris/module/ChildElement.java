package com.iris.module;

/**
 * 
 */


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author sbali
 *
 */

public class ChildElement {

	private int id;
	private String label;
	private String documentLabel;
	private Integer order;
	private String prentPath;
	private List<ChildElement> childElement = new ArrayList<ChildElement>();
	
	
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	
	@XmlAttribute
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the documentLabel
	 */
	public String getDocumentLabel() {
		return documentLabel;
	}
	/**
	 * @param documentLabel the documentLabel to set
	 */
	
	@XmlAttribute( name="documentLabel")
	public void setDocumentLabel(String documentLabel) {
		this.documentLabel = documentLabel;
	}
	/**
	 * @return the order
	 */
	public Integer getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	@XmlAttribute(name="order")
	public void setOrder(Integer order) {
		this.order = order;
	}
	/**
	 * @return the prentPath
	 */
	public String getPrentPath() {
		return prentPath;
	}
	/**
	 * @param prentPath the prentPath to set
	 */
	@XmlTransient
	public void setPrentPath(String prentPath) {
		this.prentPath = prentPath;
	}
	/**
	 * @return the childElement
	 */
	public List<ChildElement> getChildElement() {
		return childElement;
	}
	/**
	 * @param childElement the childElement to set
	 */
	public void setChildElement(List<ChildElement> childElement) {
		this.childElement = childElement;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	@XmlTransient
	public void setId(int id) {
		this.id = id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nChildElement [id=" + id + ", label=" + label
				+ ", documentLabel=" + documentLabel + ", order=" + order
				+ ", prentPath=" + prentPath + ", childElement=" + childElement
				+ "]\n";
	}
	

	

	
	
}

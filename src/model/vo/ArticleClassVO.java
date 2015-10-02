package model.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ArticleClassVO {
	@XmlElement(required = true)
	private String subclassNo;
	@XmlElement(required = true)
	private String subclassName;
	@XmlElement(required = true)
	private String className;
	
	@Override
	public String toString() {
		return subclassNo + ": " + subclassName + " (" + className + ")";
	}
	public String getSubclassNo() {
		return subclassNo;
	}
	public void setSubclassNo(String subclassNo) {
		this.subclassNo = subclassNo;
	}
	public String getSubclassName() {
		return subclassName;
	}
	public void setSubclassName(String subclassName) {
		this.subclassName = subclassName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
}

/**
 * 
 */
package com.mathtabolism.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.mathtabolism.constants.Weekday;
import com.mathtabolism.jsf.renderer.Attribute;
import com.mathtabolism.util.string.StringUtils;

/**
 * 
 * @author laursenm
 */
@FacesConverter(value="enumConverter")
public class EnumConverter implements Converter {
	
	private static final String CONSTANTS_PACKAGE = "com.mathtabolism.constants.";
	/**
	 * @param context
	 * @param component
	 * @param value
	 * @return
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(StringUtils.isNotBlank(value)) {
			Class<? extends Enum> enumType = getEnum(context, component);
			if(enumType == null) {
				System.out.println("Returning null1");
				return null;
			}
			try {
				return Enum.valueOf(enumType, value);
			} catch(IllegalArgumentException e) {
				
			}
		}
		System.out.println("Returning null");
		return null;
	}
	
	/**
	 * @param context
	 * @param component
	 * @param object
	 * @return
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if(context == null || component == null) {
			
		}
		if(object instanceof Enum) {
			return ((Enum<?>) object).toString();
		}
		return null;
	}
	
	protected Class<? extends Enum> getEnum(FacesContext context, UIComponent component) {
		Class<? extends Enum> eClass = null;
		String enumClass = (String) component.getAttributes().get(StringUtils.toDataAttribute(Attribute.ENUM_CLASS));
		if(StringUtils.isBlank(enumClass)) {
			return null;
		}
		try {
			eClass = (Class<? extends Enum>) Class.forName(CONSTANTS_PACKAGE + enumClass);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return eClass;
	}
}

/**
 * 
 */
package com.mathtabolism.jsf.renderer;

import java.io.IOException;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.mathtabolism.util.string.StringUtils;
import com.sun.faces.renderkit.html_basic.MenuRenderer;
/**
 * 
 * @author laursenm
 */
public class SelectOneMenuRenderer extends MenuRenderer {
	
	public SelectOneMenuRenderer() {
	}
	
	@Override
	protected void getEndTextToRender(FacesContext context, UIComponent component, String value) throws IOException {
		Map<String, Object> attributes = component.getAttributes();
		ResponseWriter wrter = context.getResponseWriter();
		for(Attribute attribute : Attribute.values()) {
			String attr = StringUtils.toDataAttribute(attribute);
			String a = (String) attributes.get(attr);
			if(attr != null) {
				wrter.writeAttribute(attr, a, attr);
			}
		}
		
		super.getEndTextToRender(context, component, value);
	}
}

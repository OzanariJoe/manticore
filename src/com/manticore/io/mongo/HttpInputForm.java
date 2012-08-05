package com.manticore.io.mongo;

import java.lang.reflect.Field;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

public class HttpInputForm {

	public <T> T getForm(Class<T> clazz, ServletRequest req)
			throws FormException {
		try {
			T form = clazz.newInstance();
			setParametersToForm(form, req);
			return form;
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException e) {
			e.printStackTrace();
			throw new FormException(e);
		}
	}

	final String getKey(Class<?> clazz) {
		return clazz.toString();
	}

	public <T> T readFromSession(Class<T> clazz, HttpSession session) {
		Object o = session.getAttribute(getKey(clazz));
		return clazz.cast(o);
	}

	public <T> void saveInSession(HttpSession session, T form) {
		session.setAttribute(getKey(form.getClass()), form);
	}

	final <T> void setParametersToForm(T form, ServletRequest req)
			throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = form.getClass().getDeclaredFields();
		for (Field field : fields) {
			String parameter = req.getParameter(field.getName());
			setParameterToField(form, field, parameter);
		}
	}

	final <T> void setParameterToField(T form, Field field, String parameter)
			throws IllegalArgumentException, IllegalAccessException {
		Class<?> type = field.getType();

		if (String.class == type) {
			field.set(form, parameter);

		} else if ((short.class == type) || (Short.class == type)) {
			field.set(this, Short.valueOf(parameter));

		} else if ((int.class == type) || (Integer.class == type)) {
			field.set(this, Integer.valueOf(parameter));

		} else if ((long.class == type) || (Long.class == type)) {
			field.set(this, Long.valueOf(parameter));

		} else if ((float.class == type) || (Float.class == type)) {
			field.set(this, Float.valueOf(parameter));

		} else if ((double.class == type) || (Double.class == type)) {
			field.set(this, Double.valueOf(parameter));

		} else if ((boolean.class == type) || (Boolean.class == type)) {
			field.set(this, Boolean.valueOf(parameter));
		}
	}

}

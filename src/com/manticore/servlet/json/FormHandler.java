package com.manticore.servlet.json;

import javax.servlet.http.HttpServletRequest;

public interface FormHandler {

	public Object handle(HttpServletRequest req);

}

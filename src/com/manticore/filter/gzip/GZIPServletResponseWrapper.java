package com.manticore.filter.gzip;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

class GZIPServletResponseWrapper extends
		javax.servlet.http.HttpServletResponseWrapper {

	GZIPServletResponseWrapper(HttpServletResponse response) {
		super(response);
		setHeader("Context-Encoding", "gzip");
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		GZIPServletOutputStream gzos = new GZIPServletOutputStream(
				getOutputStream());
		return gzos;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return new PrintWriter(getOutputStream());
	}

	@Override
	public void setContentLength(int len) {
	}
}
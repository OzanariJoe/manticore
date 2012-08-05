package com.manticore.filter.gzip;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;

class GZIPServletOutputStream extends ServletOutputStream implements
		AutoCloseable {

	private GZIPOutputStream gos = null;

	GZIPServletOutputStream(ServletOutputStream sos) throws IOException {
		this.gos = new GZIPOutputStream(sos);
	}

	@Override
	public void close() throws IOException {
		try {
			this.gos.close();
		} finally {
			super.close();
		}
	}

	@Override
	public void write(int b) throws IOException {
		this.gos.write(b);
	}
}
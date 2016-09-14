package com.async.clientImpl;

import java.util.Random;

public class BoundaryBuilder {

	private String Enter = "\r\n";

	private String start_tag;

	private String end_tag;

	private String boundary;

	public BoundaryBuilder() {
		createBoundary();
	}

	void createBoundary() {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 30; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		boundary = sb.toString();

		start_tag = "--" + boundary;
		end_tag = "--" + boundary + "--" + Enter;
	}

	public String getBoundary() {

		return boundary;
	}

	public String getEnd_tag() {
		return end_tag;
	}

	public String getStart_tag() {
		return start_tag;
	}

}

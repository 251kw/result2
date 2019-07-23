package com.shantery.result2.util;

public class PagingViewElement {
	private String name;
	private String href;

	public PagingViewElement() {
	}

	public PagingViewElement(final String name, final String href) {
		this.setName(name);
		this.setHref(href);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

}

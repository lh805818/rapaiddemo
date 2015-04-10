package com.company.project.model.resp;

public class TextMessage extends BaseMessage {

	public TextMessage() {
	}

	private String Content;

	/**
	 * @return the content
	 */
	public String getContent() {
		return Content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String Content) {
		this.Content = Content;
	}
	
}

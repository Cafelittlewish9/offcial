package controller;

public class Message {
	private String text;
	private long id = System.currentTimeMillis();
	public Message(String newtext) {
	        text = newtext;
	        if (text.length() > 200) {
	            text = text.substring(0, 200); //回傳0-200之間的字
	        }
	    }
	public long getId() {
		return id;
	}
	public String getText() {
		return text;
	}
}

package domain;

public class Message {

	private Long id;
	private String text;

	public Message(String msg) {
		// TODO Auto-generated constructor stub
		text=msg;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return String.format("Message [id=%s, text=%s]", id, text);
	}
}

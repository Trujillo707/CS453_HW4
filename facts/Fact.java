package facts;

public class Fact {

	private String author;
	private String type;
	private String text;

	public Fact() {}


	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Check if an Object is a Fact object equivalent to the Fact the method was called on.
	 * Overrides Object.equals() for convenience.
	 *
	 * @param theOther The Object to compare the Fact object to
	 * @return True if the Object is a Fact and has equal fields. False otherwise.
	 */
	@Override
	public boolean equals(Object theOther){
		if (theOther == this)
			return true;
		if (!(theOther instanceof Fact))
			return false;

        return this.author.equals(((Fact) theOther).getAuthor()) && this.text.equals(((Fact) theOther).getText()) && this.type.equals(((Fact) theOther).getType());
    }




}

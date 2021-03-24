package m226;

public class books {
	
	String title;
	String author;
	int pages;
	boolean lent;
	
	//Setter und Getter für alle Variablen
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAuthor() {
		return author;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getPages() {
		return pages;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setLent(boolean lent) {
		this.lent = lent;
	}
	public Boolean getLent() {
		return lent;
	}
}

package model.bean;

public class Movie {
	private int id;
	private String title;
	private int lenght;
	private boolean image3d;
	private boolean dubbed;
	private String synopsis;
	private String category;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getLenght() {
		return lenght;
	}
	public void setLenght(int lenght) {
		this.lenght = lenght;
	}
	public boolean isImage3d() {
		return image3d;
	}
	public void setImage3d(boolean image3d) {
		this.image3d = image3d;
	}
	public boolean isDubbed() {
		return dubbed;
	}
	public void setDubbed(boolean dubbed) {
		this.dubbed = dubbed;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
}

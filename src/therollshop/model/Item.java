package therollshop.model;

public class Item {
	private int id;
	private String name;
	private double price;
	private String description;
	private int stock;
	private String image;

	public Item() {
	}

	public Item(int id, String name, double price, String description, int stock, String image) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.stock = stock;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double d) {
		this.price = d;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description + ", stock="
				+ stock + ", image=" + image + "]";
	}

}

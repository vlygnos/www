package Entity;

import javax.persistence.*;

@Entity
@Table(name="product")

public class Product {
	
	@Column(name="name")
	private String name;
	
	@Id
	@Column(name="barcode")
	private int barcode;
	
	@Column(name="colour")
	private String colour;
	
	@Column(name="description")
	private String description;

	public Product() {
		super();
		this.name="default";
		this.barcode=0;
		this.colour="default";
		this.description="default";
	}
	public Product(String name, int barcode, String colour, String description) {
		super();
		this.name=name;
		this.barcode=barcode;
		this.colour=colour;
		this.description=description;
	}

	public String getName() {return name;}
	public int getBarcode() {return barcode;}
	public String getColour() {return colour;}
	public String getDescription() {return description;}
}
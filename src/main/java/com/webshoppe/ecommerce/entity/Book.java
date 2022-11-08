package com.webshoppe.ecommerce.entity;

import java.math.BigDecimal;

public class Book implements Item{
    private String bId;
    private String name;
    private String description;
    private String aId;
    private BigDecimal price;

    public Book() {
    }

    public Book(String id, String name, String description, String aid, BigDecimal price) {
        this.bId = id;
        this.aId = aid;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getbId() {
        return bId;
    }

    public void setId(String id) {
        this.bId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAid() {
		return aId;
	}

	public void setAid(String aid) {
		this.aId = aid;
	}

	@Override
    public int hashCode() {
        return this.bId.hashCode() * 31;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Book)) {
            return false;
        }
        Book otherToy = (Book)obj;
        return this.bId.equals(otherToy.getbId()) && this.name.equals(otherToy.getName())
               && this.description.equals(otherToy.getDescription())
               && this.price.equals(otherToy.getPrice());
    }

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return bId;
	}
}

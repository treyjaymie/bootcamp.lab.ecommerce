package com.webshoppe.ecommerce.entity;

import java.math.BigDecimal;

public interface Item {

    public String getId();

    public void setId(String id);

    public String getName();

    public void setName(String name);

    public String getDescription();

    public void setDescription(String description);

    public BigDecimal getPrice();

    public void setPrice(BigDecimal price);

	@Override
    public int hashCode();

    @Override
    public boolean equals(Object obj);
}
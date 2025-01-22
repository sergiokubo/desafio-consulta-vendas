package com.devsuperior.dsmeta.dto;

public class SellerSalesDTO {
    private String name;
    private Double totalSales;

    public SellerSalesDTO(String name, Double totalSales){
        this.name = name;
        this.totalSales = totalSales;
    }

    public String getName() {
        return name;
    }

    public Double getAmount() {
        return totalSales;
    }
}

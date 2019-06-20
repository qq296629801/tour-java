package cn.ymsys.api.model.product;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Product {
    private int id;
    private int companyId;
    private String name;
    private String specsSize;
    private String infrastructure;
    private String accommodate;
    private String manufacturer;
    private List<ProductCategory> productCategorys = new ArrayList<ProductCategory>();
}

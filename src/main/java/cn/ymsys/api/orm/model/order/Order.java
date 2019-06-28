package cn.ymsys.api.orm.model.order;

import lombok.Data;

@Data
public class Order {
    private int id;
    private int orderType;
    private double fee;
}

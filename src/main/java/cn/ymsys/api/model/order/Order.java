package cn.ymsys.api.model.order;

import lombok.Data;

@Data
public class Order {
    private int id;
    private int settledId;
    private double fee;
}

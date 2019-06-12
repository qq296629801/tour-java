package cn.ymsys.api.common.enums;

public enum RoleEnum {
    SUPPLIER_ROLE(0, "供应商"),
    BUY_ROLE(1, "采购商");
    private int state;
    private String name;

    RoleEnum(int state, String name) {
        this.state = state;
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static String valueOf(int state) {
        return RoleEnum.values()[state].name;
    }
}

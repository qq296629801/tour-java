package cn.ymsys.enums;

public enum BaseEnum {
	WEIXIN_PAYMENT(100, "微信", "ba_repayment_type_id", "bu_u_authentication"),
	ZHIFUBAO_PAYMENT(100, "支付宝", "ba_repayment_type_id", "bu_u_authentication"),
	PRODUCT_IMG(500, "产品图片", "ba_photo_type_id", "bu_photo"), COMPANY_IMG(501, "公司图片", "ba_photo_type_id", "bu_photo"),
	TENDER_IMG(502, "标的图片", "ba_photo_type_id", "bu_photo"),
	AUTHENTICATION_IMG(503, "认证图片", "ba_photo_type_id", "bu_photo");

	private String name;
	private long value;
	private String column;
	private String table;

	BaseEnum(long value, String name, String column, String table) {
		this.value = value;
		this.name = name;
		this.column = column;
		this.table = table;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

}

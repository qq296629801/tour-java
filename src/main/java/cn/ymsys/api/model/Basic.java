package cn.ymsys.api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document
public class Basic implements Serializable {
    @Id
    private int id;
    private String key;
    private String value;
    private String clounmName;
    private String clunmTable;
}

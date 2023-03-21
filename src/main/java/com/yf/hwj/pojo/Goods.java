package com.yf.hwj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Goods {
    private long id;
    private String name;
    private String specification;
    private String category;
    private String description;
}

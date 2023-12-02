package com.heritage.mkheritage.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HeritageSite {
    private String id;
    private String name;
    private String siteType;
    private double lat;
    private double lon;
}

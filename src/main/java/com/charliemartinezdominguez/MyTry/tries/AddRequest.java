package com.charliemartinezdominguez.MyTry.tries;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddRequest {
    private String name;
    private String address;
    private String longitude;
    private String latitude;
}

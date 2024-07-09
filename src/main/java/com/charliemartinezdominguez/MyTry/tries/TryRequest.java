package com.charliemartinezdominguez.MyTry.tries;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TryRequest {
    private String longitude;
    private String latitude;
    private String distance;
}

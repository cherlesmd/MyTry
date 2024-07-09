package com.charliemartinezdominguez.MyTry.tries;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteRequest {
    private String name;
    private String longitude;
    private String latitude;
}

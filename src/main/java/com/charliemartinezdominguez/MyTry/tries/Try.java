package com.charliemartinezdominguez.MyTry.tries;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "tries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Try {

  @Id
  private ObjectId tryId;

  private String name;

  private String address;
  private GeoJsonPoint location;
  private String username;
  private List<String> itineraries;
}

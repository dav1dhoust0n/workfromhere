package dev.dav1dhoust0n.workfromhere.spaces.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpaceResource {
    private long id;
    private String name;
    private String description;
    private SpaceTypeResource type;
    private boolean wifiAvailable;
    private String wifiPassword;
    private int wifiSpeed;
}

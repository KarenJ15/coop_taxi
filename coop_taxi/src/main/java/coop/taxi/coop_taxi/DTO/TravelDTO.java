package coop.taxi.coop_taxi.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class TravelDTO {
    private int id;
    private String origin; 
    private String destination;
    private Float cost;
}

package coop.taxi.coop_taxi.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class TaxiDTO {
    private Long id;
    private String licensePlate; 
    private String model; 
    private String tradeMark; 
    private String registration;
}

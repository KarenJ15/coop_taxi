package coop.taxi.coop_taxi.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DriverListDTO {
    private Long id;
    private String name; 
    private String licenceType;
    private String document;
}

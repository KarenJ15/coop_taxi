package coop.taxi.coop_taxi.DTO;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewTaxiDTO {
    @NotNull(message = "Licese plate width can't be null.")
    private String licensePlate; 
    private String model; 
    private String tradeMark; 
    private String registration;
}

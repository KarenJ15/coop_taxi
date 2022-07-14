package coop.taxi.coop_taxi.DTO;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewTravelDTO {
    @NotNull(message = "Origin width can't be null.")
    private String origin; 
    private String destination;
    private Float cost;
}
package coop.taxi.coop_taxi.DTO;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewDriverDTO {
    @NotNull(message = "Name width can't be null.")
    private String name; 
    private String licenceType;
    private String document;
}

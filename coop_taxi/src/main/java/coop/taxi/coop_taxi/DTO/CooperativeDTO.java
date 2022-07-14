package coop.taxi.coop_taxi.DTO;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class CooperativeDTO extends NewCooperativeDTO{
    private Long id;
    private List<TaxiDTO> taxis;
}

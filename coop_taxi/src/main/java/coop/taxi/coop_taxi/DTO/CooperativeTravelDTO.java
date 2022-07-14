package coop.taxi.coop_taxi.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CooperativeTravelDTO extends CooperativeDTO{
    private TravelDTO travel;
}

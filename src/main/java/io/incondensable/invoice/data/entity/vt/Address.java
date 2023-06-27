package io.incondensable.invoice.data.entity.vt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * @author abbas
 */
@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("JpaAttributeMemberSignatureInspection")
public class Address {
    private String country;
    private String state;
    private String city;
    private String mainStreet;

    /**
     * @return the full address format by concatenating all Address properties.
     */
    public String getCompleteAddress() {
        return country + ", " + state + ", " + city + ", " + mainStreet;
    }

}

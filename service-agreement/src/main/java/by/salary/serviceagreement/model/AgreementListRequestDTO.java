package by.salary.serviceagreement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class AgreementListRequestDTO {

    private BigInteger id;
    private String stateListName;

}

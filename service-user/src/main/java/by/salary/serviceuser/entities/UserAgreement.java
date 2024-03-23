package by.salary.serviceuser.entities;


import by.salary.serviceuser.model.UserAgreementRequestDTO;
import by.salary.serviceuser.model.UserAgreementResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.util.List;

/**
User agreement
One table with all user agreements
UserAgreement=
    userId - WHO take the add
    agreementId - WHAT agreement (class AgreementState - service-agreement module)
    moderatorName (just Name of user, who assigned the add)
    moderatorComment (moderator comment, text )
    count (the count of %)
    time (when the add was assigned -set in create time)
    currentBaseReward (current base reward from ORGANISATION property -set in create time)
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserAgreement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    @NotNull
    private BigInteger userId;
    @NotNull
    private BigInteger agreementId;

    @Size(min = 1, max = 50)
    private String moderatorName;
    @Size(min = 1, max = 2000)
    private String moderatorComment;
    @NotNull
    private BigDecimal count;
    @NotNull
    private Time time;
    @NotNull
    private BigDecimal currentBaseReward;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_agreement_user",
            joinColumns = @JoinColumn(name = "user_agreement_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    public UserAgreement(UserAgreementRequestDTO userAgreementRequestDTO) {
        this.userId = userAgreementRequestDTO.getUserId();
        this.agreementId = userAgreementRequestDTO.getAgreementId();
        this.moderatorName = userAgreementRequestDTO.getModeratorName();
        this.moderatorComment = userAgreementRequestDTO.getModeratorComment();
        this.count = userAgreementRequestDTO.getCount();
        this.time = userAgreementRequestDTO.getTime();
        this.currentBaseReward = userAgreementRequestDTO.getCurrentBaseReward();
    }

    public void update(UserAgreementRequestDTO userAgreementRequestDTO){
        this.userId = userAgreementRequestDTO.getUserId() == null? this.userId : userAgreementRequestDTO.getUserId();
        this.agreementId = userAgreementRequestDTO.getAgreementId() == null? this.agreementId : userAgreementRequestDTO.getAgreementId();
        this.moderatorName = userAgreementRequestDTO.getModeratorName() == null? this.moderatorName : userAgreementRequestDTO.getModeratorName();
        this.moderatorComment = userAgreementRequestDTO.getModeratorComment() == null? this.moderatorComment : userAgreementRequestDTO.getModeratorComment();
        this.count = userAgreementRequestDTO.getCount() == null? this.count : userAgreementRequestDTO.getCount();
        this.time = userAgreementRequestDTO.getTime() == null? this.time : userAgreementRequestDTO.getTime();
        this.currentBaseReward = userAgreementRequestDTO.getCurrentBaseReward() == null? this.currentBaseReward : userAgreementRequestDTO.getCurrentBaseReward();

    }
}
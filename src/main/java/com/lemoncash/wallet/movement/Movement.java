package com.lemoncash.wallet.movement;

import com.lemoncash.wallet.wallet.Wallet;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double amount;
    private Type movementType;

    @JoinColumn(name = "wallet_id")
    @ManyToOne
    private Wallet wallet;

}

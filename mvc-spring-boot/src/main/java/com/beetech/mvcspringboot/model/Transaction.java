package com.beetech.mvcspringboot.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    private Long balance = 0L;

}

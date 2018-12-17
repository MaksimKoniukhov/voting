package com.maks.voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maks.voting.HasId;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = "theme")
@EqualsAndHashCode(of = "id")
public class Item implements HasId {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String answer;

    private int totalVotes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id")
    @JsonIgnore
    private Theme theme;
}


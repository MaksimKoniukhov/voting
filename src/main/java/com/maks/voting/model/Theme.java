package com.maks.voting.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String question;

    private boolean enabled;

    @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL)
    private List<Item> items;
}

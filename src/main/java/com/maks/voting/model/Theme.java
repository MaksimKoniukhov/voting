package com.maks.voting.model;

import com.maks.voting.HasId;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
public class Theme implements HasId {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String question;

    private String path;

    @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL)
    private List<Item> items;
}

package org.corka.housholdkeepingbook.category;

import lombok.*;

import javax.persistence.*;

/**
 * Categorize expenses.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NonNull
    @Column(nullable = false)
    private String name;

}

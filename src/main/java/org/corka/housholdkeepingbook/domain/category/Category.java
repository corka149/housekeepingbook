package org.corka.housholdkeepingbook.domain.category;

import lombok.*;
import org.corka.housholdkeepingbook.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @NonNull
    @Column(nullable = false)
    private boolean deleted = false;

    @NonNull
    @ManyToOne
    private User creator;

    @NonNull
    @Column(nullable = false)
    private LocalDateTime creationDate;
}

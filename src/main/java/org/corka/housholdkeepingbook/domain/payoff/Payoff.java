package org.corka.housholdkeepingbook.domain.payoff;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.corka.housholdkeepingbook.domain.category.Category;
import org.corka.housholdkeepingbook.domain.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payoff {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NonNull
    private LocalDate payoffDate;

    @NonNull
    private float amount;

    @NonNull
    private String description;

    @NonNull
    @ManyToOne
    private Category category;

    @NonNull
    @ManyToOne
    private User creator;

    @NonNull
    @JsonIgnore
    private LocalDateTime creationDate;

    @Column
    private boolean deleted = false;

    @Column
    private boolean regularOccurrence = false;

    public boolean isNotDeleted() {
        return !this.deleted;
    }
}

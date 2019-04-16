package org.corka.housholdkeepingbook.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@Entity
@Table(name = "housekeepingbook_user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "housekeepingbook_user_id_seq")
    private long id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String name;

    @NonNull
    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column
    private String favoriteColor;

    @Column
    private boolean deleted = false;

    public boolean isNotDeleted() {
        return !this.deleted;
    }
}

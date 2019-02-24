package org.corka.housholdkeepingbook.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private long id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @JsonIgnore
    @Column(nullable = false)
    private String password;
}

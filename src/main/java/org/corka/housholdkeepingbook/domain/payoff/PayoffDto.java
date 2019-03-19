package org.corka.housholdkeepingbook.domain.payoff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayoffDto {

    private long id;

    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate payoffDate;

    private float amount;

    @NonNull
    private String description;

    private long categoryId;

    private long creatorUserId;

}

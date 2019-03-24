package org.corka.housholdkeepingbook.domain.payoff;

import lombok.val;
import org.corka.housholdkeepingbook.domain.category.Category;
import org.corka.housholdkeepingbook.domain.user.User;

public class PayoffDtoMapper {

    static Payoff fromDto(PayoffDto payoffDto, Category category, User creator) {
        val payoff = new Payoff();

        payoff.setPayoffDate(payoffDto.getPayoffDate());
        payoff.setAmount(payoffDto.getAmount());
        payoff.setDescription(payoffDto.getDescription());
        payoff.setCategory(category);
        payoff.setCreator(creator);
        payoff.setRegularOccurrence(payoffDto.isRegularOccurrence());

        return payoff;
    }

    static PayoffDto toDto(Payoff payoff) {
        val payoffDto = new PayoffDto();

        payoffDto.setId(payoff.getId());
        payoffDto.setPayoffDate(payoff.getPayoffDate());
        payoffDto.setAmount(payoff.getAmount());
        payoffDto.setDescription(payoff.getDescription());
        payoffDto.setCategoryId(payoff.getCategory().getId());
        payoffDto.setCreatorUserId(payoff.getCreator().getId());

        return payoffDto;
    }
}

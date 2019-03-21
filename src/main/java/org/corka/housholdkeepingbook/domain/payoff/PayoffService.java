package org.corka.housholdkeepingbook.domain.payoff;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.corka.housholdkeepingbook.domain.category.CategoryService;
import org.corka.housholdkeepingbook.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PayoffService {

    private PayoffRepository payoffRepository;

    private UserService userService;

    private CategoryService categoryService;

    @Autowired
    public PayoffService(PayoffRepository payoffRepository, UserService userService, CategoryService categoryService) {
        this.payoffRepository = payoffRepository;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    void addPayoff(PayoffDto payoffDto, String userName) {
        val category = this.categoryService.getCategoryById(payoffDto.getCategoryId());
        val creator = this.userService.findUserByName(userName);
        val payoff = PayoffDtoMapper.fromDto(payoffDto, category, creator);
        payoff.setCreationDate(LocalDateTime.now());

        log.info("User {} tries to add payoff: {}", userName, payoff.toString());
        this.payoffRepository.save(payoff);
    }

    List<PayoffDto> getAllPayoffs() {
        val payoffs = this.payoffRepository.findAll();
        log.info("All payoffs requested: Total amount of payoffs {}", payoffs.size());
        return payoffs.stream().map(PayoffDtoMapper::toDto).collect(Collectors.toList());
    }

    List<Payoff> getLatestPayoff(int size) {
        val payoffPage = this.payoffRepository.findAll(PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "payoffDate")));
        return payoffPage.getContent();
    }
}

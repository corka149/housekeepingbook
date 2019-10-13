package org.corka.housholdkeepingbook.domain.payoff;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.corka.housholdkeepingbook.domain.category.CategoryDoesNotExists;
import org.corka.housholdkeepingbook.domain.category.CategoryService;
import org.corka.housholdkeepingbook.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @SneakyThrows
    PayoffDto addPayoff(PayoffDto payoffDto, String userName) {
        val categoryOpt = this.categoryService.getCategoryById(payoffDto.getCategoryId());
        val creator = this.userService.findUserByNameIgnoreCase(userName);
        val payoffId = this.payoffRepository.findHighestPayOffId().map(id -> id + 1L).orElse(1L);
        val payoff = categoryOpt
                .map(category -> PayoffDtoMapper.fromDto(payoffDto, category, creator))
                .orElseThrow(CategoryDoesNotExists::new);

        payoff.setCreationDate(LocalDateTime.now());
        payoff.setId(payoffId);

        log.info("User {} tries to add payoff: {}", userName, payoff.toString());
        val newPayoff = this.payoffRepository.save(payoff);
        return PayoffDtoMapper.toDto(newPayoff);
    }

    List<PayoffDto> getAllActivePayoffs() {
        val payoffs = this.payoffRepository.findAll().stream()
                .filter(Payoff::isNotDeleted)
                .map(PayoffDtoMapper::toDto)
                .collect(Collectors.toList());
        log.info("All payoffs requested: Total amount of payoffs {}", payoffs.size());
        return payoffs;
    }

    List<Payoff> getLatestPayoffs(int size) {
        return this.payoffRepository.findLatestAddedActivePayoffs().stream()
                .filter(Payoff::isNotDeleted)
                .limit(size)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @Transactional
    void deletePayoff(long payoffId) {
        val payoffOpt = this.payoffRepository.findById(payoffId);
        val payoff = payoffOpt.orElseThrow(PayoffDoesNotExists::new);
        payoff.setDeleted(true);
        log.info("Deleted payoff with id {}", payoff);
        this.payoffRepository.save(payoff);
    }

    Payoff getPayoffById(long id) {
        return this.payoffRepository.getOne(id);
    }
}

package org.wora.wainting___list.visitor.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.wora.wainting___list.visitor.domain.entity.Visitor;
import org.wora.wainting___list.visitor.domain.repository.VisitorRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VisitorSeeder {

    private final VisitorRepository visitorRepository;

    @Bean
    public CommandLineRunner createVisitors() {
        return args -> {
            System.out.println("-- visitor seeder --");
            if (visitorRepository.count() != 0)
                return;

            System.out.println("--  visitors --");
            visitorRepository.saveAll(List.of(
                    new Visitor("test1", "test1"),
                    new Visitor("test2", "test2"),
                    new Visitor("test3", "test3"),
                    new Visitor("test4", "test4"),
                    new Visitor("test5", "test5")));
        };

    }

}

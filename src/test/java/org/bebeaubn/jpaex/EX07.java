package org.bebeaubn.jpaex;

import org.bebeaubn.entities.BoardData;
import org.bebeaubn.repositories.BoardDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

public class EX07 {
    @SpringBootTest
    @TestPropertySource(properties = "spring.profiles.active=test")
    public class Ex07 {

        @Autowired
        private BoardDataRepository boardDataRepository;

        @Autowired
        private HashTagRepository hashTagRepository;

        @BeforeEach
        void init() {
            List<HashTag> tags = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
                HashTag tag = new HashTag();
                tag.setTag("태그" + i);
                tags.add(tag);
            }

            hashTagRepository.saveAllAndFlush(tags);

            List<BoardData> items = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
                BoardData item = BoardData.builder()
                        .subject("제목" + i)
                        .content("내용" + i)
                        //.tags(tags)
                        .build();
                items.add(item);
            }

            boardDataRepository.saveAllAndFlush(items);
        }

        @Test
        void test1() {
            BoardData data = boardDataRepository.findById(1L).orElse(null);
            //List<HashTag> tags = data.getTags();
            //tags.stream().forEach(System.out::println);
        }

        @Test
        void test2() {
            HashTag tag = hashTagRepository.findById("태그2").orElse(null);
            //List<BoardData> items = tag.getItems();
            //items.stream().forEach(System.out::println);
        }
    }
}

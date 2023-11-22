package org.bebeaubn.jpaex;

import com.querydsl.core.BooleanBuilder;
import org.bebeaubn.entities.BoardData;
import org.bebeaubn.entities.QBoardData;
import org.bebeaubn.repositories.BoardDataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@SpringBootTest
@TestPropertySource(properties = "spring.profiles.active=test")
public class Ex05 {
    @Autowired
    private BoardDataRepository repository;

    @Test
    void test1(){
        QBoardData boardData =QBoardData.boardData;
       // BooleanExpression cond1 =boardData.subject.contains("목");

        List<BoardData> items = (List<BoardData>) repository.findAll(boardData.subject.contains("목"));
        items.stream().forEach(System.out::println);


    }
    @Test
    void test2() {
        QBoardData boardData = QBoardData.boardData;

        BooleanBuilder andBuilder = new BooleanBuilder();
        BooleanBuilder orBuilder = new BooleanBuilder();

        orBuilder.or(boardData.subject.contains("목"))
                .or(boardData.content.contains("용"));

        andBuilder.and(orBuilder)
                .and(boardData.seq.in(2,4,6,8));


        /*
        BooleanBuilder andBuilder = new BooleanBuilder();
        andBuilder.and(boardData.subject.contains("목"))
                .and(boardData.content.contains("용"))
                .and(boardData.seq.in(2, 4, 6, 8));
        */
        List<BoardData> items = (List<BoardData>)repository.findAll(andBuilder);
        items.stream().forEach(System.out::println);
    }
}



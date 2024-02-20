package com.picpal.framework;

import com.picpal.framework.sample.repository.SampleRepository;
import com.picpal.framework.sample.repository.model.SampleEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@DataJpaTest
class BoilerplateApplicationTest {

    SampleRepository sampleRepository;

    public BoilerplateApplicationTest(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    @BeforeEach
    void insertTestData(){
        SampleEntity sampleEntity = new SampleEntity();

        sampleEntity.setUsername("KIM 1");
        sampleRepository.save(sampleEntity);

        sampleEntity.setUsername("KIM 2");
        sampleRepository.save(sampleEntity);

        sampleEntity.setUsername("LEE");
        sampleRepository.save(sampleEntity);

        sampleEntity.setUsername("PARK");
        sampleRepository.save(sampleEntity);

    }

    @Test
    void findAllTest(){
        List<SampleEntity> userList = sampleRepository.findAll();
        for (SampleEntity u : userList) {
            log.info("FIND ALL => username : {}", u.getUsername());
        }
    }

    @Test
    void find2ByNameTest() { // Like 검색으로 2개만 값을 가져오는 내가 작성한 명령을 실행해본다
        List<SampleEntity> userList = sampleRepository.findFirst2ByUsernameLikeOrderByIdDesc("kim%");
        for (SampleEntity u : userList) {
            log.info("FIND ALL => username : {}", u.getUsername());
        }
    }

}
package com.picpal.framework.common.excel;

import com.picpal.framework.common.excel.ExcelDataService;
import com.picpal.framework.sample.mapper.SampleMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.mockito.Mockito.mock;

class ExcelDataServiceTest {
    private SqlSessionFactory sqlSessionFactory;
    private ExcelDataService excelDataService;

    @BeforeEach
    void setUp() {
        // SqlSessionFactory를 mock으로 생성
        sqlSessionFactory = mock(SqlSessionFactory.class);
        excelDataService = new ExcelDataService(sqlSessionFactory);
    }

    @Test
    void testProcessExcelData() {
        // 테스트용 데이터 생성
        List<Map<String, Object>> testData = Arrays.asList(
                Map.of("id", "1"),
                Map.of("age", "11"),
                Map.of("birthday", "2023-01-01"),
                Map.of("name", "user02"),
                Map.of("username", "testUser02")
        );

        // 테스트 실행
        excelDataService.processExcelData("com.picpal.framework.sample.mapper.SampleMapper", "insertUserRecord", testData);

        // 예외 없이 정상적으로 실행되면 테스트 통과
    }
}

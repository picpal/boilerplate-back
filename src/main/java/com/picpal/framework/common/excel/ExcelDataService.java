package com.picpal.framework.common.excel;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class ExcelDataService {
    private final SqlSessionFactory sqlSessionFactory;

    public ExcelDataService(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void processExcelData(String mapperClass, String methodName, List<Map<String, Object>> dataList) {
        try (SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            Class<?> mapperInterface = Class.forName(mapperClass);
            Object mapper = session.getMapper(mapperInterface);
            Method method = mapperInterface.getMethod(methodName, Map.class);

            int count = 0;
            for (Map<String, Object> data : dataList) {
                method.invoke(mapper, data);
                if (++count % 3000 == 0) {
                    session.commit(); // 3,000건마다 커밋
                    session.clearCache(); // 옵션: 세션 캐시를 정리하여 메모리 사용량 관리
                }
            }
            session.commit(); // 남은 데이터에 대해 커밋
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.picpal.framework.common.utils.HtmlGenerator;

import com.picpal.framework.common.utils.BackOfficeLogReportGenerator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.nodes.Element;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class HtmlGeneratorTest {

    @Test
    void htmlGenerateTest() throws ParseException {
        BackOfficeLogReportGenerator bog = new BackOfficeLogReportGenerator();
        String result = bog.getBackOfficeLogReport();

        log.info(result);

//        HtmlGenerator hg = new HtmlGenerator();
//        String jsonData = "[[{\"text\":\"row01_cell01\",\"rowspan\":\"2\",\"colspan\":\"1\"},{\"text\":\"row01_cell02\",\"rowspan\":\"1\",\"colspan\":\"1\"}],[{\"text\":\"\",\"rowspan\":\"1\",\"colspan\":\"1\"},{\"text\":\"row02_cell02\",\"rowspan\":\"1\",\"colspan\":\"1\"}]]";
//
//        JSONParser parser = new JSONParser();
//        JSONArray rows = (JSONArray) parser.parse(jsonData);
//
//        List<List<Map<String, String>>> tableData = new ArrayList<>();
//        for (Object rowObj : rows) {
//            JSONArray rowArray = (JSONArray) rowObj;
//            List<Map<String, String>> row = new ArrayList<>();
//
//            for (Object cellObj : rowArray) {
//                JSONObject cellJson = (JSONObject) cellObj;
//                Map<String, String> cell = new HashMap<>();
//                for (Object keyObj : cellJson.keySet()) {
//                    String key = (String) keyObj;
//                    String value = (String) cellJson.get(key);
//                    cell.put(key, value);
//                }
//                row.add(cell);
//            }
//            tableData.add(row);
//        }
//
//
////        Element table = hg.createTable(tableData, "table-class");
//
//        Element ul = hg.createUl(new String[]{"Item1", "Item2", "Item3"}, "ul-class");
//
//        Element dl = hg.createDl(new String[][]{
//                {"Term1", "Description1"},
//                {"Term2", "Description2"}
//        }, "dl-class");
//
//
//        Element span = hg.createSpan("This is a span.", "span-class");
//
//        Element link = hg.createLink("https://example.com", "Click Here", "link-class");
//
//        // 모든 요소를 문서에 추가
////        hg.doc.body().appendChild(table);
//        hg.doc.body().appendChild(ul);
//        hg.doc.body().appendChild(dl);
////        hg.doc.body().appendChild(div);
//        hg.doc.body().appendChild(span);
//        hg.doc.body().appendChild(link);
//
//        // 최종 HTML 출력
//        log.info(hg.html());

        
    }
}
package com.picpal.framework.common.utils.HtmlGenerator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlGenerator {

    public Document doc;

    public HtmlGenerator() {
        this.doc = Jsoup.parseBodyFragment("");
    }

    /**
     * list 데이터를 테이블 형태의 html 태그로 변환
     *
     * @param jsonStr 테이블 row 데이터
     * @param classAttribute 테이블의 className
     *
     * */
    public Element createTable(String jsonStr, String classAttribute){
        JSONParser parser = new JSONParser();
        JSONArray rows;
        try {
            rows = (JSONArray) parser.parse(jsonStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Element table = doc.createElement("table").addClass(classAttribute);
        for (Object rowObj : rows) {
            JSONArray rowArray = (JSONArray) rowObj;
            Element tr = table.appendElement("tr");

            for (Object cellObj : rowArray) {
                JSONObject cellJson = (JSONObject) cellObj;
                Map<String, String> cell = new HashMap<>();
                for (Object keyObj : cellJson.keySet()) {
                    String key = (String) keyObj;
                    String value = (String) cellJson.get(key);
                    cell.put(key, value);
                }

                if(cell.get("text") == null || "".equals(cell.get("text"))){
                    continue;
                }

                tr.appendElement("td")
                        .html(cell.get("text"))
                        .attr("rowspan",cell.get("rowspan") == null ? "1" : cell.get("rowspan"))
                        .attr("colspan",cell.get("colspan") == null ? "1" : cell.get("colspan"))
                        .attr("style","border:1px solid;");
            }
        }

        return table;
    }

    public Element createUl(String[] items, String classAttribute) {
        Element ul = doc.createElement("ul").addClass(classAttribute);
        for (String item : items) {
            ul.appendElement("li").text(item);
        }
        return ul;
    }

    public Element createDl(String[][] definitions, String classAttribute) {
        Element dl = doc.createElement("dl").addClass(classAttribute);
        for (String[] definition : definitions) {
            dl.appendElement("dt").text(definition[0]);
            dl.appendElement("dd").text(definition[1]);
        }
        return dl;
    }

    public Element createDiv(String content, String classAttribute , String style) {
        return doc.createElement("div").text(content).attr("style",style).addClass(classAttribute);
    }

    public Element createBr() {
        return doc.body().appendElement("br");
    }

    public Element createSpan(String content, String classAttribute) {
        return doc.createElement("span").text(content).addClass(classAttribute);
    }

    public Element createLink(String href, String text, String classAttribute) {
        return doc.createElement("a").attr("href", href).text(text).addClass(classAttribute);
    }

    public Element createLine(String content, String classAttribute) {
        return doc.createElement("br").text(content).addClass(classAttribute);
    }

    // HTML을 문자열로 반환합니다. 이는 최종적인 HTML 출력을 위해 사용됩니다.
    public String html() {
        return doc.body().html();
    }
}

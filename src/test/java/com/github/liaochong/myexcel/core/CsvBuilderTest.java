package com.github.liaochong.myexcel.core;

import com.github.liaochong.myexcel.core.pojo.CsvPeople;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author liaochong
 * @version 1.0
 */
class CsvBuilderTest extends BasicTest {

    @BeforeAll
    static void before() throws Exception {
        Files.deleteIfExists(Paths.get(TEST_OUTPUT_DIR + "common.csv"));
    }

    @Test
    void mapBuild() {
        List<Map> maps = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", "name" + i);
            map.put("age", i);
            maps.add(map);
        }
        List<String> titles = new ArrayList<>();
        titles.add("姓名");
        titles.add("年龄");
        Csv csv = CsvBuilder.of(Map.class).titles(titles).build(maps);
        csv.write(Paths.get(TEST_OUTPUT_DIR + "map.csv"));
    }

    @Test
    void build() {
        Csv csv = CsvBuilder.of(CsvPeople.class).build(data(1));
        csv.write(Paths.get(TEST_OUTPUT_DIR + "common.csv"));
    }

    @Test
    void buildContinued() {
        Csv csv = CsvBuilder.of(CsvPeople.class).build(data(1));
        csv.write(Paths.get(TEST_OUTPUT_DIR + "common.csv"), true);
    }

    @Test
    void appendBuild() {
        CsvBuilder<CsvPeople> csvBuilder = CsvBuilder.of(CsvPeople.class);
        for (int i = 0; i < 10; i++) {
            csvBuilder.append(data(1000));
        }
        Csv csv = csvBuilder.build();
        csv.write(Paths.get(TEST_OUTPUT_DIR + "append.csv"));

        csvBuilder = CsvBuilder.of(CsvPeople.class).noTitles();
        for (int i = 0; i < 10; i++) {
            csvBuilder.append(data(1000));
        }
        csv = csvBuilder.build();
        csv.write(Paths.get(TEST_OUTPUT_DIR + "append.csv"), true);

        csvBuilder = CsvBuilder.of(CsvPeople.class);
        for (int i = 0; i < 10; i++) {
            csvBuilder.append(data(1000));
        }
        csv = csvBuilder.build();
        csv.write(Paths.get(TEST_OUTPUT_DIR + "append.csv"));
    }

    @Test
    void noTitlesBuild() {
        CsvBuilder<CsvPeople> csvBuilder = CsvBuilder.of(CsvPeople.class).noTitles();
        for (int i = 0; i < 10; i++) {
            csvBuilder.append(data(1000));
        }
        Csv csv = csvBuilder.build();
        csv.write(Paths.get(TEST_OUTPUT_DIR + "no_titles_append.csv"));
    }

    private List<CsvPeople> data(int size) {
        BigDecimal oddMoney = new BigDecimal(109898);
        BigDecimal evenMoney = new BigDecimal(66666);

        List<CsvPeople> peoples = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            CsvPeople csvPeople = new CsvPeople();
            boolean odd = i % 2 == 0;
            csvPeople.setName(odd ? "张三\"" : "李四");
            csvPeople.setAge(null);
            csvPeople.setDance(odd ? true : false);
            csvPeople.setMoney(odd ? oddMoney : evenMoney);
            csvPeople.setBirthday(new Date());
            peoples.add(csvPeople);
        }
        return peoples;
    }

}
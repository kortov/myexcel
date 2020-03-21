/*
 * Copyright 2019 liaochong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.liaochong.myexcel.core;

import com.github.liaochong.myexcel.utils.FileExportUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liaochong
 * @version 1.0
 */
public class EnjoyExcelBuilderTest extends BasicTest {

    @Test
    public void build() throws Exception {
        ExcelBuilder excelBuilder = new EnjoyExcelBuilder();
        Map<String, String> params = new HashMap<>();
        params.put("sheetName", "xxxx");
        Workbook workbook = excelBuilder.classpathTemplate("/templates/enjoyToExcelExample.ey").build(params);
        FileExportUtil.export(workbook, new File(TEST_OUTPUT_DIR + "enjoy_build.xlsx"));
    }

    @Test
    public void fileBuild() throws Exception {
        ExcelBuilder excelBuilder = new EnjoyExcelBuilder();
        Map<String, String> params = new HashMap<>();
        params.put("sheetName", "xxxx");
        Workbook workbook = excelBuilder.fileTemplate(TEST_RESOURCES_DIR + "templates", "enjoyToExcelExample.ey").build(params);
        FileExportUtil.export(workbook, new File(TEST_OUTPUT_DIR + "enjoy_file_build.xlsx"));
    }

}

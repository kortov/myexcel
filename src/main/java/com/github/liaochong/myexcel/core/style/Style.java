package com.github.liaochong.myexcel.core.style;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;

import java.util.EnumSet;
import java.util.function.BiConsumer;

public enum Style {
    BORDER_LEFT_STYLE ("border-left-style", CellStyle::setBorderLeft),
    BORDER_RIGHT_STYLE ("border-right-style", CellStyle::setBorderRight),
    BORDER_TOP_STYLE ("border-top-style", CellStyle::setBorderTop),
    BORDER_BOTTOM_STYLE ("border-bottom-style", CellStyle::setBorderBottom),
    BORDER ("border", (cellStyle, borderStyle) -> {
        cellStyle.setBorderLeft(borderStyle);
        cellStyle.setBorderTop(borderStyle);
        cellStyle.setBorderRight(borderStyle);
        cellStyle.setBorderBottom(borderStyle);
    });

    private String cssStyle;
    private BiConsumer<CellStyle, BorderStyle> function;
    private BorderStyle poiStyle;

    Style(String cssStyle, BiConsumer<CellStyle, BorderStyle> function) {
        this.cssStyle = cssStyle;
        this.function = function;
    }

    public String getCssStyle() {
        return cssStyle;
    }

    public BiConsumer<CellStyle, BorderStyle> getFunction() {
        return function;
    }

    public void apply(CellStyle cellStyle, BorderStyle borderStyle) {
        function.accept(cellStyle, borderStyle);
    }

}

package me.vasiliy.khotenov.courseprojectapp.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SocksColor {

    BLACK("чёрный"),
    GREY("серый"),
    WHITE("белый"),
    RED("красный"),
    BLUE("синий"),
    BROWN("коричневый");

    private final String color;

    SocksColor(String color) {
        this.color = color;
    }

    @JsonValue
    public String getColor() {
        return color;
    }
}

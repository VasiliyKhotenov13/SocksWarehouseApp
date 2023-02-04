package me.vasiliy.khotenov.courseprojectapp.models;

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
    public String getColor() {
        return color;
    }
}

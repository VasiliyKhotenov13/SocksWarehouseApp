package me.vasiliy.khotenov.courseprojectapp.models;

public enum SocksSize {

    S(36,37),
    M(38,40),
    L(41,43),
    XL(44, 46),
    XXL(47,47);

    private final int minSize = 36;
    private final int maxSize = 47;

    SocksSize(int minSize, int maxSize) {
    }
}

package pl.koziol.calculator.model;

public enum Unit {
    FT("Feet"),
    NM("Nautical Miles"),
    M("Meters");
    public String fullName;

    Unit(String fullName) {
        this.fullName = fullName;
    }
}

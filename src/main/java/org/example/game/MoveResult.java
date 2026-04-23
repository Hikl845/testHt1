package org.example.game;

public class MoveResult {

    private MoveResultType type;
    private String city;
    private Character expectedLetter;

    private MoveResult(MoveResultType type) {
        this.type = type;
    }

    public static MoveResult invalid() {
        return new MoveResult(MoveResultType.INVALID);
    }

    public static MoveResult used() {
        return new MoveResult(MoveResultType.USED);
    }

    public static MoveResult win() {
        return new MoveResult(MoveResultType.WIN);
    }

    public static MoveResult success(String city) {
        MoveResult r = new MoveResult(MoveResultType.SUCCESS);
        r.city = city;
        return r;
    }

    public static MoveResult invalidLetter(char expected) {
        MoveResult r = new MoveResult(MoveResultType.INVALID_LETTER);
        r.expectedLetter = expected;
        return r;
    }

    public MoveResultType getType() { return type; }
    public String getCity() { return city; }
    public Character getExpectedLetter() { return expectedLetter; }
}

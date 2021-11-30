package aocUniverse;

public class Password {

    private final int from;
    private final int to;
    private final char letter;
    private final String password;

    public Password(int from, int to, char letter, String password) {
        this.from = from;
        this.to = to;
        this.letter = letter;
        this.password = password;
    }

    public boolean valid1() {
        long count = password.chars().filter(ch -> ch == letter).count();
        return count >= from && count <= to;
    }

    public boolean valid2() {
        return password.charAt(from - 1) == letter ^ password.charAt(to - 1) == letter;
    }
}

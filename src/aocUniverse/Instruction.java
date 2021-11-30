package aocUniverse;

public class Instruction {

    String type;
    int amount;
    boolean visited;

    public Instruction(String type, int amount, boolean visited) {
        this.type = type;
        this.amount = amount;
        this.visited = visited;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}

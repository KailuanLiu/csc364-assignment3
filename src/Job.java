public class Job {
    private static int idCounter = 0;
    int id;
    int first;
    int second;
    int operation;

    public Job (int first, int second, int operation) {
        this.id = idCounter++;
        this.first = first;
        this.second = second;
        this.operation = operation;
    }
    private String getOperator() {
        if(operation < 0 || operation > 4) {
            throw new IllegalArgumentException();
        }
        return switch (operation) {
            case 1 -> "+";
            case 2 -> "-";
            case 3 -> "*";
            case 4 -> "/";
            default -> throw new IllegalArgumentException();
        };
    }

    public String process() {
        int answer = 0;
        String operator = getOperator();
        answer = switch (operator) {
            case "+" -> first + second;
            case "-" -> first - second;
            case "*" -> first * second;
            case "/" -> (first / second);
            default -> answer;
        };
        return String.format("id %d: %d %s %d = %s",id,first, operator, second, answer);
    }

    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return String.format("id %d: %d %s %d",id,first,getOperator(),second);
    }
}

public class Job {
    int first;
    int second;
    int operation;

    public Job (int first, int second, int operation) {
        this.first = first;
        this.second = second;
        this.operation = operation;
    }

    public String process() {
        int answer = 0;
        String operator = "";
        switch (this.operation) {
            case 1:
                answer =  first + second;
                operator = "+";
                break;
            case 2:
                answer = first - second;
                operator = "-";
                break;
            case 3:
                answer = first * second;
                operator = "*";
                break;
            case 4:
                answer = (int)(first / second);
                operator = "/";
                break;
        }
        return String.format("%d %s %d = %s",first, operator, second, answer);
    }
}

package net.lambda.grammar;

public class LambdaQs {
    public void eat (Eatable eatable) {
        eatable.taste();
    }
    public void driver (Flyable flyable) {
        flyable.fly(" ++sunning++");
    }

    public void add (Addable addable) {
        System.out.println("[add] = " + addable.add(1,2));
    }
}

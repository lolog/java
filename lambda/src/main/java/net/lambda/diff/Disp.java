package net.lambda.diff;

@FunctionalInterface
public interface Disp {
    void display();
    
    default int add(int a, int b) {
        return a+b;
    }
}

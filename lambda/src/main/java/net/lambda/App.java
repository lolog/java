package net.lambda;

import net.lambda.diff.Disp;
import net.lambda.grammar.LambdaQs;
import net.lambda.quotes.Constructor;
import net.lambda.quotes.Converts;
import net.lambda.simple.ProcessArray;
import org.junit.Test;

import static net.lambda.grammar.Flyable.age;

public class App 
{
    @Test
    public void simple() {
        ProcessArray process = new ProcessArray();
        int[] arrs = {1, 2,3,4,5};

        process.process(arrs, (int[] targets) -> {
            int sum = 0;
            for (int target: targets) {
                sum += target;
            }
            System.out.println("sum = " + sum);
        });
    }
    
    @Test
    public void grammer () {
        String name = "lolog";
        LambdaQs qs = new LambdaQs();
        
        qs.eat(() -> System.out.println("hello word"));
        System.out.println("=============================");
        
        qs.driver(weather -> {
            System.out.println("wether = " + weather);
            System.out.println("===nice===");
            System.out.println("name = " + name + ";  age="+age);
        });
        System.out.println("=============================");
        
        qs.add((a, b) -> a+b);
    }
    
    @Test // 引用类方法
    public void quotes1 () {
        // 方式一
        Converts converts = from -> Integer.valueOf(from);
        
        // 方式2
        Converts converts1 = Integer::valueOf;
        
        System.out.println(converts.convert("1"));
        System.out.println(converts1.convert("1"));
    }
    
    @Test // 引用特定对象的实例方法
    public void quotes2 () {
        // 方式一
        Converts converts = from -> "hello word".indexOf(from);
        
        // 方式2
        Converts converts1 = "hello word"::indexOf;
        
        System.out.println(converts.convert("o"));
        System.out.println(converts1.convert("o"));
    }
    
    @Test // 引用构造器
    public void quotes3 () {
        // 方式一
        Constructor constructor = val -> new Integer(val);
        
        // 方式2
        Constructor constructor1 = Integer::new;
        
        System.out.println(constructor.number(1));
        System.out.println(constructor1.number(2));
    }

    @Test 
    public void diff () {
        Disp disp = () -> {
          System.out.println("a");  
        };

        disp.display();
        System.out.println(disp.add(1,2));
    }
}

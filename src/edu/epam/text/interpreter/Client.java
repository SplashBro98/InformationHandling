package edu.epam.text.interpreter;

import java.util.ArrayList;

public class Client {
    ArrayList<MathExpression> expressions = new ArrayList<>();
    Context context = new Context();

    public void parse(String expression){
        for(String lexeme : expression.split("\\p{Blank}+")){
            if(Character.isDigit(lexeme.charAt(0))){
               expressions.add(c -> c.push(Integer.parseInt(lexeme)));
            }
            else{
                for(MathOperation operation : MathOperation.values()){
                    if(operation.getOperation().equals(lexeme)){
                        switch (operation){
                            case NOT:
                                expressions.add(c -> c.push(~c.pop()));
                                break;
                            case AND:
                                expressions.add(c -> c.push(c.pop() & c.pop()));
                                break;
                            case OR:
                                expressions.add(c -> c.push(c.pop() | c.pop()));
                                break;
                            case XOR:
                                expressions.add(c -> c.push(c.pop() ^ c.pop()));
                                break;
                            case LEFTSHIFT:
                                expressions.add(new MathExpression() {
                                    @Override
                                    public void interpret(Context context) {
                                        int first = context.pop();
                                        int second = context.pop();
                                        context.push(second << first);
                                    }
                                });
                                break;
                            case RIGHTSHIFT:
                                expressions.add(new MathExpression() {
                                    @Override
                                    public void interpret(Context context) {
                                        int first = context.pop();
                                        int second = context.pop();
                                        context.push(second >> first);
                                    }
                                });
                                break;
                        }
                    }
                }
            }
        }
    }
    public int calculate(){
        expressions.forEach(c -> c.interpret(context));
        return context.pop();
    }
}

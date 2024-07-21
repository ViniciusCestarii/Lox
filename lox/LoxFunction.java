package lox;

import java.util.List;

class LoxFunction implements LoxCallable {
  private final Stmt.Function declaration;
    // fun makeCounter() {
    //   var i = 0; 
    //   fun count() { <----- this is a closure because it “closes over” and holds on to the surrounding variables where the function is declared. (in this case: i) 
    //     i = i + 1;
    //     print i;
    //   }
    
    //   return count;
    // }
    
    // var counter = makeCounter();
    // counter(); // "1".
    // counter(); // "2".
  private final Environment closure;
  private final boolean isInitializer;

  LoxFunction(Stmt.Function declaration, Environment closure,
              boolean isInitializer) {
    this.isInitializer = isInitializer;
    this.closure = closure;
    this.declaration = declaration;
  }

  LoxFunction bind(LoxInstance instance) {
    Environment environment = new Environment(closure);
    environment.define("this", instance);
    return new LoxFunction(declaration, environment,
                           isInitializer);
  }

  @Override
  public String toString() {
    return "<fn " + declaration.name.lexeme + ">";
  }

  @Override
  public int arity() {
    return declaration.params.size();
  }

  @Override
  public Object call(Interpreter interpreter,
                     List<Object> arguments) {
    // create a new environment at each call
    Environment environment = new Environment(closure);
    for (int i = 0; i < declaration.params.size(); i++) {
      environment.define(declaration.params.get(i).lexeme,
          arguments.get(i));
    }

    try {
      interpreter.executeBlock(declaration.body, environment);
    } catch (Return returnValue) {
      if (isInitializer) return closure.getAt(0, "this");

      return returnValue.value;
    }

    if (isInitializer) return closure.getAt(0, "this");
    
    return null;
  }
}

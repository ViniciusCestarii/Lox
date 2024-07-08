
## Language

Backus-Naur Form (BNF) for Lox


> simplified version

```
expression     → literal
               | unary
               | binary
               | grouping ;

literal        → NUMBER | STRING | "true" | "false" | "nil" ;
grouping       → "(" expression ")" ;
unary          → ( "-" | "!" ) expression ;
binary         → expression operator expression ;
operator       → "==" | "!=" | "<" | "<=" | ">" | ">="
               | "+"  | "-"  | "*" | "/" ;
```

> full version

```
expression     → equality ;
equality       → comparison ( ( "!=" | "==" ) comparison )* ;
comparison     → term ( ( ">" | ">=" | "<" | "<=" ) term )* ;
term           → factor ( ( "-" | "+" ) factor )* ;
factor         → unary ( ( "/" | "*" ) unary )* ;
unary          → ( "!" | "-" ) unary
               | primary ;
primary        → NUMBER | STRING | "true" | "false" | "nil"
               | "(" expression ")" ;
```
## Commands

generate Abstract Syntax Tree (AST)
```bash
java tool.GenerateAst lox
```

execute file
```bash
java lox.Lox ./examples/test.lox
```

prompt and see what the compiler return (for now it only the scan tokens, so it's not a compiler)
```
java lox.Lox
```

write this and press enter
```
imIdentifier = "string literal";
```
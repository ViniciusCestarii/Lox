package lox;

class RpnAstPrinter implements Expr.Visitor<String> {
    String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return expr.left.accept(this) + " " +
               expr.right.accept(this) + " " +
               expr.operator.lexeme;
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return expr.expression.accept(this);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return expr.right.accept(this) + " " + expr.operator.lexeme;
    }

    // Test the RPN AST printer
    // public static void main(String[] args) {
    //     Expr expression = new Expr.Binary(
    //             new Expr.Unary(
    //                     new Token(TokenType.MINUS, "-", null, 1),
    //                     new Expr.Literal(123)),
    //             new Token(TokenType.STAR, "*", null, 1),
    //             new Expr.Grouping(
    //                     new Expr.Literal(45.67)));

    //     System.out.println(new RpnAstPrinter().print(expression)); // 123 - 45.67 *
    // }

    // Test 2 the RPN AST printer (from the book)
    // public static void main(String[] args) {
    //     Expr expression = new Expr.Binary( // (1 + 2) * (4 - 3)
    //       new Expr.Grouping(
    //         new Expr.Binary(
    //           new Expr.Literal(1),
    //           new Token(TokenType.PLUS, "+", null, 1), 
    //           new Expr.Literal(2)
    //         )
    //       ),
    //       new Token(TokenType.STAR, "*", null, 1),
    //       new Expr.Grouping(
    //         new Expr.Binary(
    //           new Expr.Literal(4),
    //           new Token(TokenType.MINUS, "-", null, 1), 
    //           new Expr.Literal(3)
    //         )
    //       )
    //     );

    //     System.out.println(new RpnAstPrinter().print(expression)); // 1 2 + 4 3 - *
    // }
}

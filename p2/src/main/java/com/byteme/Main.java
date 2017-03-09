package com.byteme;

import com.byteme.lexer.KeywordLexeme;
import com.byteme.lexer.Lexeme;
import com.byteme.lexer.Token;
import com.byteme.lexer.classes.*;
import com.byteme.scanner.Scanner;

import java.io.File;

public class Main {

    // TODO: Move this to GrammarBuilder or Grammar or something
    private static Lexeme[] lexemes = {
            new KeywordLexeme("array"),
            new KeywordLexeme("begin"),
            new KeywordLexeme("boolean"),
            new KeywordLexeme("break"),
            new KeywordLexeme("do"),
            new KeywordLexeme("else"),
            new KeywordLexeme("end"),
            new KeywordLexeme("enddo"),
            new KeywordLexeme("endif"),
            new KeywordLexeme("false"),
            new KeywordLexeme("float"),
            new KeywordLexeme("for"),
            new KeywordLexeme("func"),
            new KeywordLexeme("if"),
            new KeywordLexeme("in"),
            new KeywordLexeme("int"),
            new KeywordLexeme("let"),
            new KeywordLexeme("of"),
            new KeywordLexeme("return"),
            new KeywordLexeme("then"),
            new KeywordLexeme("to"),
            new KeywordLexeme("true"),
            new KeywordLexeme("type"),
            new KeywordLexeme("unit"),
            new KeywordLexeme("var"),
            new KeywordLexeme("while"),
            new KeywordLexeme(","),
            new KeywordLexeme(":"),
            new KeywordLexeme(";"),
            new KeywordLexeme("("),
            new KeywordLexeme(")"),
            new KeywordLexeme("["),
            new KeywordLexeme("]"),
            new KeywordLexeme("{"),
            new KeywordLexeme("}"),
            new KeywordLexeme("."),
            new KeywordLexeme("+"),
            new KeywordLexeme("-"),
            new KeywordLexeme("*"),
            new KeywordLexeme("/"),
            new KeywordLexeme("="),
            new KeywordLexeme("<>"),
            new KeywordLexeme("<"),
            new KeywordLexeme(">"),
            new KeywordLexeme("<="),
            new KeywordLexeme(">="),
            new KeywordLexeme("&"),
            new KeywordLexeme("|"),
            new KeywordLexeme(":="),
            new KeywordLexeme("_"),
            new CommentClassLexeme(),
            new FloatlitClassLexeme(),
            new IdClassLexeme(),
            new IntlitClassLexeme()
    };

    public static void main(String[] args) {
        if (args.length != 2) {
            printUsage();
            System.exit(1);
        }

        String fnameIn = args[0];
        String phase = args[1];

        if (phase.equals("--tokens")) {
            Scanner sc = new Scanner(new File(fnameIn), lexemes);

            // Generate tokens
            sc.tokenize();

            while (sc.hasNextToken()) {
                Token t = sc.getNextToken();
                Lexeme l = t.getLexeme();

                // Format the token output
                String tokenString = l.stringify(t.getValue());

                // Only write tokenString if it is not empty
                if (!tokenString.isEmpty()) {
                    System.out.print(l.stringify(t.getValue()));

                    if (sc.hasNextToken())
                        System.out.print(" ");
                }
            }
        } else {
            printUsage();
            System.exit(1);
        }
    }

    private static void printUsage() {
        System.out.println("Usage: java -jar compiler.jar SOURCE_FILE [—-tokens]\n");
    }
}
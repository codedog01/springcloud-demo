//package com.lengao.springcloud.utils;
//
///**
// * <p>description goes here</p>
// *
// * @author 冷澳
// * @date 2022/12/2
// */
//
//import jdk.nashorn.internal.runtime.regexp.RegExp;
//
//import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import static jdk.nashorn.internal.objects.NativeArray.concat;
//
//public class JavaScriptUtil {
//    int MIN = 32;
//    int MAX = 126;
//    String GLOBAL = " Object(\"return this\")()"; //todo
//
//    HashMap<String,String> SIMPLE = new HashMap<>();
//    HashMap<String,String> CONSTRUCTORS = new HashMap<>();
//    HashMap<String,String> MAPPING = new HashMap<>();
//
//    {
//        SIMPLE.put("false", "![]");
//        SIMPLE.put("true", "!![]");
//        SIMPLE.put("undefined", "[][[]]");
//        SIMPLE.put("NaN", "+[![]]");
//        SIMPLE.put("Infinity", "+(+!+[]+(!+[]+[])[!+[]+!+[]+!+[]]+[+!+[]]+[+[]]+[+[]]+[+[]])");
//
//
//        CONSTRUCTORS.put("Array", "[]");
//        CONSTRUCTORS.put("Number", "(+[])");
//        CONSTRUCTORS.put("String", "([]+[])");
//        CONSTRUCTORS.put("Boolean", "(![])");
//        CONSTRUCTORS.put(" Object", "[][\"flat\"]");
//        CONSTRUCTORS.put("RegExp", " Object(\"return/\"+false+\"/\")()");
//        CONSTRUCTORS.put("Object", "[][\"entries\"]()");
//
////        MAPPING.put(0, "[+[]]");
////        MAPPING.put(1, "[+!+[]]");
////        MAPPING.put(2, "[!+[]+!+[]]");
////        MAPPING.put(3, "[!+[]+!+[]+!+[]]");
////        MAPPING.put(4, "[!+[]+!+[]+!+[]+!+[]]");
////        MAPPING.put(5, "[!+[]+!+[]+!+[]+!+[]+!+[]]");
////        MAPPING.put(6, "[!+[]+!+[]+!+[]+!+[]+!+[]+!+[]]");
////        MAPPING.put(7, "[!+[]+!+[]+!+[]+!+[]+!+[]+!+[]+!+[]]");
////        MAPPING.put(8, "[!+[]+!+[]+!+[]+!+[]+!+[]+!+[]+!+[]+!+[]]");
////        MAPPING.put(9, "[!+[]+!+[]+!+[]+!+[]+!+[]+!+[]+!+[]+!+[]+!+[]]");
//
//        MAPPING.put("a", "(false+\"\")[1]");
//        MAPPING.put("b", "([][\"entries\"]()+\"\")[2]");
//        MAPPING.put("c", "([][\"flat\"]+\"\")[3]");
//        MAPPING.put("d", "(undefined+\"\")[2]");
//        MAPPING.put("e", "(true+\"\")[3]");
//        MAPPING.put("f", "(false+\"\")[0]");
//        MAPPING.put("g", "(false+[0]+String)[20]");
//        MAPPING.put("h", "(+(101))[\"to\"+String[\"name\"]](21)[1]");
//        MAPPING.put("i", "([false]+undefined)[10]");
//        MAPPING.put("j", "([][\"entries\"]()+\"\")[3]");
//        MAPPING.put("k", "(+(20))[\"to\"+String[\"name\"]](21)");
//        MAPPING.put("l", "(false+\"\")[2]");
//        MAPPING.put("m", "(Number+\"\")[11]");
//        MAPPING.put("n", "(undefined+\"\")[1]");
//        MAPPING.put("o", "(true+[][\"flat\"])[10]");
//        MAPPING.put("p", "(+(211))[\"to\"+String[\"name\"]](31)[1]");
//        MAPPING.put("q", "(\"\")[\"fontcolor\"]([0]+false+\")[20]");
//        MAPPING.put("r", "(true+\"\")[1]");
//        MAPPING.put("s", "(false+\"\")[3]");
//        MAPPING.put("t", "(true+\"\")[0]");
//        MAPPING.put("u", "(undefined+\"\")[0]");
//        MAPPING.put("v", "(+(31))[\"to\"+String[\"name\"]](32)");
//        MAPPING.put("w", "(+(32))[\"to\"+String[\"name\"]](33)");
//        MAPPING.put("x", "(+(101))[\"to\"+String[\"name\"]](34)[1]");
//        MAPPING.put("y", "(NaN+[Infinity])[10]");
//        MAPPING.put("z", "(+(35))[\"to\"+String[\"name\"]](36)");
//        MAPPING.put("A", "(NaN+[][\"entries\"]())[11]");
//        MAPPING.put("B", "(+[]+Boolean)[10]");
//        MAPPING.put("C", " Object(\"return escape\")()((\"\")[\"italics\"]())[2]");
//        MAPPING.put("D", " Object(\"return escape\")()([][\"flat\"])[\"slice\"](\"-1\")");
//        MAPPING.put("E", "(RegExp+\"\")[12]");
//        MAPPING.put("F", "(+[]+ Object)[10]");
//        MAPPING.put("G", "(false+ Object(\"return Date\")()())[30]");
//        MAPPING.put("H", null);
//        MAPPING.put("I", "(Infinity+\"\")[0]");
//        MAPPING.put("J", null);
//        MAPPING.put("K", null);
//        MAPPING.put("L", null);
//        MAPPING.put("M", "(true+ Object(\"return Date\")()())[30]");
//        MAPPING.put("N", "(NaN+\"\")[0]");
//        MAPPING.put("O", "(+[]+Object)[10]");
//        MAPPING.put("P", null);
//        MAPPING.put("Q", null);
//        MAPPING.put("R", "(+[]+RegExp)[10]");
//        MAPPING.put("S", "(+[]+String)[10]");
//        MAPPING.put("T", "(NaN+ Object(\"return Date\")()())[30]");
//        MAPPING.put("U", "(NaN+Object()[\"to\"+String[\"name\"]][\"call\"]())[11]");
//        MAPPING.put("V", null);
//        MAPPING.put("W", null);
//        MAPPING.put("X", null);
//        MAPPING.put("Y", null);
//        MAPPING.put("Z", null);
//
//        MAPPING.put(" ", "(NaN+[][\"flat\"])[11]");
//        MAPPING.put("!", null);
//        MAPPING.put("\"", "(\"\")[\"fontcolor\"]()[12]");
//        MAPPING.put("#", null);
//        MAPPING.put("$", null);
//
//        MAPPING.put("%", " Object(\"return escape\")()([][\"flat\"])[21]");
//        MAPPING.put("&", "(\"\")[\"fontcolor\"](\")[13]");
//        MAPPING.put("'", null);
//        MAPPING.put("(", "([][\"flat\"]+\"\")[13]");
//        MAPPING.put(")", "([0]+false+[][\"flat\"])[20]");
//        MAPPING.put("*", null);
//        MAPPING.put("+", "(+(+!+[]+(!+[]+[])[!+[]+!+[]+!+[]]+[+!+[]]+[+[]]+[+[]])+[])[2]");
//        MAPPING.put(",", "[[]][\"concat\"]([[]])+\"\"");
//        MAPPING.put("-", "(+(.+[0000001])+\"\")[2]");
//        MAPPING.put(".", "(+(+!+[]+[+!+[]]+(!![]+[])[!+[]+!+[]+!+[]]+[!+[]+!+[]]+[+[]])+[])[+!+[]]");
//        MAPPING.put("/", "(false+[0])[\"italics\"]()[10]");
//        MAPPING.put(":", "(RegExp()+\"\")[3]");
//        MAPPING.put(";", "(\"\")[\"fontcolor\"](NaN+\")[21]");
//        MAPPING.put("<", "(\"\")[\"italics\"]()[0]");
//        MAPPING.put("=", "(\"\")[\"fontcolor\"]()[11]");
//        MAPPING.put(">", "(\"\")[\"italics\"]()[2]");
//        MAPPING.put("?", "(RegExp()+\"\")[2]");
//        MAPPING.put("@", null);
//        MAPPING.put("[", "([][\"entries\"]()+\"\")[0]");
//
//
//        MAPPING.put("\\\\", "(RegExp(\"/\")+\"\")[1]");
//        MAPPING.put("]", "([][\"entries\"]()+\"\")[22]");
//        MAPPING.put("^", null);
//        MAPPING.put("_", null);
//        MAPPING.put("`", null);
//        MAPPING.put("{", "(true+[][\"flat\"])[20]");
//        MAPPING.put("|", null);
//        MAPPING.put("}", "([][\"flat\"]+\"\")[\"slice\"](\"-1\")");
//        MAPPING.put("~", null);
//
//
//    }
//
//
//    void fillMissingDigits() {
//        String output;
//        int number, i;
//        for (number = 0; number < 10; number++) {
//            output = "+[]";
//            if (number > 0) {
//                output = "+!" + output;
//            }
//            for (i = 1; i < number; i++) {
//                output = "+!+[]" + output;
//            }
//            if (number > 1) {
//                output = output.substring(1);
//            }
//            MAPPING.put(number+"", '[' + output + ']');
//        }
//    }
//    /**********************************************************/
//    String replace(String pattern, Map replacementMap, String value) {
//        String res = value;
//        Pattern compile = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = compile.matcher(value);
//        while (matcher.find()) {
//            res= value.replace(matcher.group(), String.valueOf(replacementMap.get(matcher.group())));
//        }
//        return  res;
//    }
//
//    String digitReplacer (String x){
//        return MAPPING.get(x);
//    }
//
//    ArrayList<String> numberReplacer (String y) {
//        char[] chars = y.toCharArray();
//        ArrayList<Integer> values = new ArrayList<>();
//        for (char aChar : chars) {
//            values.add((int) aChar);
//        }
//
//        int head = 0;
//        if (!values.isEmpty()) {
//            head += values.remove(0);
//        }
//        String output = "+[]";
//
//        if (head > 0) {
//            output = "+!" + output;
//        }
//        for (int i = 1; i < head; i++) {
//            output = "+!+[]" + output;
//        }
//        if (head > 1) {
//            output = output.substring(1);
//        }
//
//        ArrayList<String> strings = new ArrayList<>();
//        strings.add(0,output);
//        for (int i = 0; i < strings.size(); i++) {
//            String string = strings.get(i) + "+";
//            string= replace("(\\d)", MAPPING, string);
//            strings.set(i, string);
//        }
////        for (String string : strings) {
////            string += "+";
////
////            string= replace("(\\d)", MAPPING, string);
//////            Pattern compile = Pattern.compile("(\\d)", Pattern.CASE_INSENSITIVE);
//////            Matcher matcher = compile.matcher(string);
//////            while (matcher.find()) {
//////                string = string.replace(matcher.group(), digitReplacer(matcher.group()));
//////            }
////        }
//        return strings;
//    }
//    void replaceMap() {
//        char character;
//        String value;
//
//        int i;
//        for (i = MIN; i <= MAX; i++) {
//             character = (char) i;
//            value = MAPPING.get(String.valueOf(character));
//            if (null == value || "".equals(value)) {
//                continue;
//            }
//
//            for (Object key : CONSTRUCTORS.keySet()) {
//                value=   replace("\\b" + key, CONSTRUCTORS.get(key) + "[\"constructor\"]",value);
//            }
//            for (Object key : SIMPLE.keySet()) {
//                value=  replace(key+"", SIMPLE.get(key),value);
//            }
//
//
//
//
//            replace("(\\d\\d+)", numberReplacer());
//            replace("\\((\\d)\\)", digitReplacer);
//            replace('\\[(\\d)\\]', digitReplacer);
//
//            replace("GLOBAL", GLOBAL);
//            replace("\\+\"\"", '+[]');
//            replace("\"\"", '[]+[]');
//
//            MAPPING[character] = value;
//        }
//    }
//
///**********************************************************/
//    Object replaceStrings() {
//        var regEx = /[^\[\]\(\)\!\+]{
//            1
//        }/g,
//                all,
//                value,
//                missing,
//                count = MAX - MIN;
//
//        Object findMissing () {
//            var all,
//                    value,
//                    done = false;
//
//            missing = {};
//
//            for (all in MAPPING) {
//                value = MAPPING[all];
//
//                if (value && value.match(regEx)) {
//                    missing[all] = value;
//                    done = true;
//                }
//            }
//
//            return done;
//        }
//
//        Object mappingReplacer (a, b){
//            return b.split('').join('+');
//        }
//
//        Object valueReplacer (c) {
//        return missing[c] ? c : MAPPING[c];
//        }
//
//        for (all in MAPPING) {
//            if (MAPPING[all]) {
//                MAPPING[all] = MAPPING[all].replace(
//                        /\"([^\"]+)\"/gi,
//                mappingReplacer
//                );
//            }
//        }
//
//        while (findMissing()) {
//            for (all in missing) {
//                value = MAPPING[all];
//                value = value.replace(regEx, valueReplacer);
//
//                MAPPING[all] = value;
//                missing[all] = value;
//            }
//
//            if (count-- == = 0) {
//                console.error(
//                        'Could not compile the following chars:',
//                        missing
//                );
//            }
//        }
//    }
//
//    Object escapeSequence(c) {
//        var cc = c.charCodeAt(0);
//        if (cc < 256) {
//            return '\\' + cc.toString(8);
//        } else {
//            var cc16 = cc.toString(16);
//            return '\\u' + ('0000' + cc16).substring(cc16.length);
//        }
//    }
//
//    Object escapeSequenceForReplace(c) {
//        return escapeSequence(c).replace('\\', 't');
//    }
//
//    Object encode(input, wrapWithEval, runInParentScope) {
//        var output = [];
//
//        if (!input) {
//            return '';
//        }
//
//        var unmappped = '';
//        for (var k in MAPPING){
//            if (MAPPING[k]) {
//                unmappped += k;
//            }
//        }
//        unmappped = unmappped.replace( /[.*+ ?^$ {
//        }
//        () |[\]\\]/g, '\\$&');
//        unmappped = new RegExp('[^' + unmappped + ']', 'g');
//        var unmappedCharactersCount = (input.match(unmappped) ||[]).length;
//        if (unmappedCharactersCount > 1) {
//            // Without this optimization one unmapped caracter has encoded length
//            // of about 3600 characters. Every additional unmapped character adds
//            // 2000 to the total length. For example, the lenght of `~` is 3605,
//            // `~~` is 5600, and `~~~` is 7595.
//            //
//            // The loader with replace has encoded length of about 5300 characters
//            // and every additional character adds 100 to the total length.
//            // In the same example the length of `~~` becomes 5371 and `~~~` -- 5463.
//            //
//            // So, when we have more than one unmapped character we want to encode whole input
//            // except select characters (that have encoded length less than about 70)
//            // into an escape sequence.
//            //
//            // NOTE: `t` should be escaped!
//            input = input.replace(
//                    /[^0123456789. adefilnrsuN]/g,
//                    escapeSequenceForReplace
//            );
//        } else if (unmappedCharactersCount > 0) {
//            //Because we will wrap the input into a string we need to escape Backslash
//            // and Double quote characters (we do not need to worry about other characters
//            // because they are not mapped explicitly).
//            // The JSFuck-encoded representation of `\` is 2121 symbols,
//            // so esacped `\` is 4243 symbols and escaped `"` is 2261 symbols
//            // however the escape sequence of that characters are
//            // 2168 and 2155 symbols respectively, so it's more practical to
//            // rewrite them as escape sequences.
//            input = input.replace( /["\\]/g, escapeSequence);
//            //Convert all unmapped characters to escape sequence
//            input = input.replace(unmappped, escapeSequence);
//        }
//
//        var r = '';
//        for (var i in SIMPLE){
//            r += i + '|';
//        }
//        r += '.';
//
//        input.replace(new RegExp(r, 'g'), Object(c) {
//            var replacement = SIMPLE[c];
//            if (replacement) {
//                output.push('(' + replacement + '+[])');
//            } else {
//                replacement = MAPPING[c];
//                if (replacement) {
//                    output.push(replacement);
//                } else {
//                    throw new Error('Found unmapped character: ' + c);
//                }
//            }
//        });
//
//        output = output.join('+');
//
//        if (/^\d$ /.test(input)){
//            output += '+[]';
//        }
//
//        if (unmappedCharactersCount > 1) {
//            // replace `t` with `\\`
//            output =
//                    '(' +
//                            output +
//                            ')[' +
//                            encode('split') +
//                            '](' +
//                            encode('t') +
//                            ')[' +
//                            encode('join') +
//                            '](' +
//                            encode('\\') +
//                            ')';
//        }
//
//        if (unmappedCharactersCount > 0) {
//            output =
//                    '[][' +
//                            encode('flat') +
//                            ']' +
//                            '[' +
//                            encode('constructor') +
//                            ']' +
//                            '(' +
//                            encode('return"') +
//                            '+' +
//                            output +
//                            '+' +
//                            encode('"') +
//                            ')()';
//        }
//
//        if (wrapWithEval) {
//            if (runInParentScope) {
//                output =
//                        '[][' +
//                                encode('flat') +
//                                ']' +
//                                '[' +
//                                encode('constructor') +
//                                ']' +
//                                '(' +
//                                encode('return eval') +
//                                ')()' +
//                                '(' +
//                                output +
//                                ')';
//            } else {
//                output =
//                        '[][' +
//                                encode('flat') +
//                                ']' +
//                                '[' +
//                                encode('constructor') +
//                                ']' +
//                                '(' +
//                                output +
//                                ')()';
//            }
//        }
//
//        return output;
//    }
//
//    fillMissingDigits();
//
//    replaceMap();
//
//    replaceStrings();
//
//
//}

import lambda.Evaluate;
import lambda.Person;

import java.time.LocalTime;
import java.util.*;
import java.util.function.*;


public class
Main {

    public static <T> boolean check (T t, Predicate < T > lambda){
        return lambda.test(t);
    }
    public static void main(String[] args) {
//        selfLambdaExample();
//
//        predicateExample();
//
//        biPredicateExample();
//
//        supplierExample();
//
//        consumerExample();
//
        functionExample();
//
//        unaryBinaryExample();
//
//        boundMethodReferences();
//
//        unboundMethodReferences();
//
//        constructorMethodReferences();
//
//        staticMethodReferences();
//
//        methodsAndContext();
    }


    private static void selfLambdaExample() {
        /*
         * Created Lambda Expression by Myself
         */
        // created Lambda Expression by Myself
        Evaluate<Integer> integerEvaluate = integer -> integer < 0;
        System.out.println("Is the Evaluate Integer Number Negative: " + integerEvaluate.isNegative(-1));
        //  Another way of Implementing Lambda expretion
        Evaluate<Double> doubleEvaluate = d -> {return d < 0;};
        System.out.println("Is the Evaluate Double Number Negative: " + doubleEvaluate.isNegative(1.2));
    }

    private static void predicateExample() {
        /*
         * Predicate Lambda Expression API
         * Used when you want to Filter or Match
         *
         *
         *
         * a) Using a lambda expression, implement the Evaluate interface (typed for Integer).
         * The relevant method returns true if the argument passed is < 0, otherwise it returns false.
         * Invoke the relevant method twice – the first time pass in -1 and the second time pass in +1
         * b) Using a lambda expression, implement 3a using a Predicate.
         */

        Predicate<Integer> integerPredicate = integer -> integer > 0 ;
        System.out.println("Is the Predicate Integer Number Negative: " + integerPredicate.test(-1));

        // Usage of Predicate Lambda Expression API
        System.out.println("function Integer check " + check(2, integer -> integer > 3));
        System.out.println("function String check " + check("Yahya", s -> s.startsWith("Ya")));
    }

    private static void biPredicateExample() {
        /*
         * BiPredicate Lambda Expression API
         * It takes 2 generic variables
         */
        BiPredicate<String, Integer> checkLength = (s, integer) -> s.length() == integer;
        System.out.println("BiPredicate compare length of String check " + checkLength.test("Yahya", 5));
    }

    private static void supplierExample(){
        /*
        * Supplier Lambda Expression API
        * Used when you want to supply values without any input
        */
        Supplier<StringBuilder> stringSupplier = () -> new StringBuilder();
        System.out.println("Supplier String" + stringSupplier.get().append("Builder"));

        Supplier<LocalTime> localTimeSupplier = () -> LocalTime.now();
        System.out.println("time now is: " + localTimeSupplier.get());

        Supplier<Double> integerSupplier = () -> Math.random();
        System.out.println("random double is: " + integerSupplier.get());


        Supplier<Integer> intSupplier = () -> 77;
        System.out.println("Supplier integer" + intSupplier.get());
    }


    private static void consumerExample(){
        /*
         * Consumer and BiConsumer Lambda Expression API
         * Use Parameters But not interested in the return Value
         */
        Consumer<String> printString = s -> System.out.println(s);
        Consumer<String> printable = System.out::println;
        printable.accept("My Name is Yahya Ghanem");
        printString.accept("My Name is Yahya Ghanem");
        // print List
        List<String> names = new ArrayList<>();
        names.add("Yahya"); names.add("Ghanem");
        names.forEach(printString);

        // BiConsumer Lambda API
        var mapCapitalCities = new HashMap<String, String>();
        // Note: the return value of put(k,v) is just ignored
        BiConsumer<String, String> biConsumer = (key, value) -> mapCapitalCities.put(key, value);
        biConsumer.accept("Cairo", "Egypt");
        biConsumer.accept("Dublin", "Ireland");
        biConsumer.accept("Kuwait", "Kuwait");
        System.out.println(mapCapitalCities);
        // print map
        BiConsumer<String,String> mapPrint = (key, value) -> System.out.println(key + " is the Capital of "+ value);
        mapCapitalCities.forEach(mapPrint);
    }

    private static void functionExample() {
        /*
         * Function and BiFunction Lambda Expression API
         * Use to Transform the input to an output (different types of input and output
         */
        Function<String, Integer> stringLength = s -> s.length();
        System.out.println(stringLength.apply("count string length"));

        BiFunction<String, String, String> biFunction = (s, s2) -> s.concat(s2);
        System.out.println(biFunction.apply("Hello", "Function Operator!"));

        Function<Integer, String> numberToString= s -> "number is " + s;
        System.out.println(numberToString.apply(25));
    }

    private static void unaryBinaryExample() {
        /*
         * unary Extends Function and Binary Extends BiFunction Lambda Expression API
         * Use to Transform the input to an output (same types of input and output
         */
        UnaryOperator<String> unaryOperator = name -> "My name is " +name;
        System.out.println(unaryOperator.apply("Yahya"));

        BinaryOperator<String> binaryOperator = (s, s2) -> s.concat(s2);
        System.out.println(binaryOperator.apply("Hello", " Binary Operator!"));
    }

    public static void boundMethodReferences(){
        String name = "Mr. Yahya Ghanem";
        // Supplier<T>
        //  T get()

        Supplier<String> lowerCase = () -> name.toLowerCase();
        Supplier<String> lowerMR = name::toLowerCase;

        // no need to say which instance to call it on  - the supplier is bound to  name
        System.out.println(lowerCase.get());
        System.out.println(lowerMR.get());

        // Predicate<T>
        // boolean test<T t>
        Predicate<String> title = s -> name.startsWith(s);
        Predicate<String> titleMR = name::startsWith;

        System.out.println(title.test("Mr"));
        System.out.println(titleMR.test("Ms"));
    }

    public static void unboundMethodReferences(){
        Function<String,String> upper = s -> s.toUpperCase();
        Function<String,String> upperMR = String::toUpperCase;;

        // function is unbound so you need to specify which input
        System.out.println(upper.apply("omg!"));
        System.out.println(upperMR.apply("shhhhhhh"));

        BiFunction<String,String,String> concat = (s, s2) -> s.concat(s2);
        BiFunction<String,String,String> concatMR = String::concat;

        System.out.println(concatMR.apply("jon", "snow") );
        System.out.println(concatMR.apply("Hello unbound ", "Method References") );

    }

    public static void constructorMethodReferences(){
        // Supplier<T>
        //      T get()
        Supplier<StringBuilder> sbL   = () -> new StringBuilder();  // lambda
        Supplier<StringBuilder> sbMR  = StringBuilder::new;         // method reference
        StringBuilder sb1 = sbL.get(); sb1.append("lambda version"); System.out.println(sb1);
        StringBuilder sb2 = sbMR.get(); sb2.append("method reference version"); System.out.println(sb2);

        //  Function<T, R>
        //      R apply(T)
        //          List<String> apply(Integer)
        //  ArrayList(int initialCapacity)
        Function<Integer, List<String>> alL  = x -> new ArrayList(x);
        Function<Integer, List<String>> alMR = ArrayList::new;
        List<String> ls1 = alL.apply(10);  // size 10
        ls1.add("21");
        System.out.println(ls1);//[21]
        List<String> ls2 = alMR.apply(5);  // size 5
        ls2.add("88");
        System.out.println(ls2);//[88]
    }

    public static void staticMethodReferences(){
        //  Static method references are considered UNBOUND also. An example static method
        //  is Collections.sort(List)
        //  Consumer<T>
        //      void accept(T t)
        //          void accept(List<Integer>)
        //  NB: Consumer takes one parameter => sort(List) is used, as opposed to sort(List, Comparator)
        Consumer<List<Integer>> sortL  = list -> Collections.sort(list);
        Consumer<List<Integer>> sortMR = Collections::sort;

        List<Integer> listOfNumbers = Arrays.asList(2,1,5,4,9);
        sortL.accept(listOfNumbers);// execution
        System.out.println(listOfNumbers);  // [1, 2, 4, 5, 9]

        listOfNumbers = Arrays.asList(8,12,4,3,7);
        sortMR.accept(listOfNumbers);// execution
        System.out.println(listOfNumbers);  // [3, 4, 7, 8, 12]
    }

    private static void methodsAndContext(){
        // No Person being passed in => Supplier
        Supplier<Integer> lambda1 = () -> Person.howMany();
        Supplier<Integer> mr1     = Person::howMany;
        System.out.println(lambda1.get());  // 0
        System.out.println(mr1.get());      // 0

        // One Person to be passed in => Function
        Function<Person, Integer> lambda2 = person -> Person.howMany(person);
        Function<Person, Integer> mr2     = Person::howMany;
        System.out.println(lambda2.apply(new Person()));  // 1
        System.out.println(mr2.apply(new Person()));      // 1

        // Two Person's to be passed in => BiFunction
        BiFunction<Person, Person, Integer> lambda3 = (p1, p2) -> Person.howMany(p1, p2);
        BiFunction<Person, Person, Integer> mr3     = Person::howMany;
        System.out.println(lambda3.apply(new Person(), new Person()));  // 2
        System.out.println(mr3.apply(new Person(), new Person()));      // 2
    }
}
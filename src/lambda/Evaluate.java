package lambda;
/*
*
* Functional Interface is the interface that have only one method
* in this way Lambda Expressions works
*
* Created by Yahya
*/
public interface Evaluate<T> {
    boolean isNegative(T t);
}

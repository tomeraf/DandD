package utilites;

public class Pair<T, U> {
    final private T first;
    final private U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T first() {
        return first;
    }

    public U second() {
        return second;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Pair<?,?>) {
            return ((Pair)other).first.equals(first) && ((Pair)other).second.equals(second);
        }
        return false;
    }
    @Override
    public String toString(){
        return " "+first.toString()+" , "+second.toString();
    }
}

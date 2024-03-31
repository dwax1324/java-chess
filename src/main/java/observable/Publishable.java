package observable;

public interface Publishable<T> {
    void subscribe(Observable observable);

    void update(T t);
}

package pe.gob.sunass.marcacion.comparator;

import java.util.Comparator;
import java.util.function.Function;


public class MarcacionGeneralComparator{
	public static <T, U extends Comparable<U>> Comparator<T> nullsLastComparator(Comparator<U> comparator, Function<T, U> keyExtractor) {
        return Comparator.nullsLast(Comparator.comparing(keyExtractor, Comparator.nullsLast(comparator)));
    }
}

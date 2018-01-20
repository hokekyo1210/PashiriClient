package DB;
/**
 * <p>2つの情報を格納するためのクラス</p>
 * @author 5415 土田
 *
 * @param <T>
 * @param <O>
 */
public class Pair<T,O> {

	public T first;
	public O second;
	public Pair(T first, O second){
		this.first = first;
		this.second = second;
	}

}

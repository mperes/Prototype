package prototype.tweener;

public interface Interval {
	void update();
	boolean finished();
	void onFinish();
}
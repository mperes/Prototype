package prototype;

public interface Behavior {
	void initBehavior(Part parent);
	Part parent();
	int type();
}

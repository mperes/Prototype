package prototype;

import java.util.ArrayList;
import processing.core.PApplet;

public class Tweener {
	PApplet _parent;
	private ArrayList<Interval> _timeline;
	private int _animating = 0;

	public Tweener() {
		_timeline = new ArrayList<Interval>();
	}

	public void update() {
		for(int t=0; t<_timeline.size(); t++) {
			Interval interval = _timeline.get(t);
			interval.update();
			if(interval.finished()) {
				interval.onFinish();
				_timeline.remove(t);
				_animating--;
			} 
		}
	}
	
	public void tween(Object __parent, String __tweenField, int __easing, float __tweenV0, float __tweenV1, int __tweenDuration) {
		Tween newTween = new Tween(__parent, __tweenField, __easing, __tweenV0, __tweenV1, __tweenDuration);
		_timeline.add(newTween);
		_animating++;
	}

	public void tween(Object __parent, String __tweenField, int __easing, float __tweenV0, float __tweenV1, int __tweenDuration, String __tweenCallback) {
		Tween newTween = new Tween(__parent, __tweenField, __easing, __tweenV0, __tweenV1, __tweenDuration, __tweenCallback);
		_timeline.add(newTween);
		_animating++;
	}
	
	public void tween(Object __parent, String __tweenField, int __easing, float __tweenV0, float __tweenV1, int __tweenDuration, int __delay) {
		Tween newTween = new Tween(__parent, __tweenField, __easing, __tweenV0, __tweenV1, __tweenDuration, __delay);
		_timeline.add(newTween);
		_animating++;
	}

	public void tween(Object __parent, String __tweenField, int __easing, float __tweenV0, float __tweenV1, int __tweenDuration, int __delay, String __tweenCallback) {
		Tween newTween = new Tween(__parent, __tweenField, __easing, __tweenV0, __tweenV1, __tweenDuration, __delay, __tweenCallback);
		_timeline.add(newTween);
		_animating++;
	}
	
	public void invoke(Object __parent, String __tweenCallback,  int __delay) {
		Delay newDelay = new Delay(__parent, __tweenCallback, __delay);
		_timeline.add(newDelay);
	}
	
	public void add(Interval interval) {
		_timeline.add(interval);
	}
	
	public void pre() {
		update();
	}
	
	public boolean animating() {
		return (_animating > 0);
	}

}



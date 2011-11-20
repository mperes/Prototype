package Prototype;
/**
 * ##library.name##
 * ##library.sentence##
 * ##library.url##
 *
 * Copyright ##copyright## ##author##
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 * 
 * @author      ##author##
 * @modified    ##date##
 * @version     ##library.prettyVersion## (##library.version##)
 */


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



package Prototype;
import java.lang.reflect.Method;

public class Delay implements Interval {
	public Object _parentObject;
	int _duration;
	public Method _onFinishCallback;
	int _currentFrame;
	boolean _finished;

	public Delay(Object __parent, String __tweenCallback, int __tweenDuration) {
		_parentObject = __parent;
		try { _onFinishCallback = _parentObject.getClass().getMethod(__tweenCallback, new Class[] { Delay.class }); }
		catch (Exception e) { System.out.println("Invalid Method"); }
		_duration = __tweenDuration;
		_finished = false;
	}		

	public void update() {
		if(!_finished) {
			if(_currentFrame <= _duration) {
				_currentFrame++;
			} 
			else {
				_finished = true;
			}
		} else {
			_currentFrame++;
		}
	}

	public boolean finished() {
		return _finished;
	}

	public void onFinish() {
		if (_onFinishCallback != null) {
			try {
				_onFinishCallback.invoke(_parentObject, new Object[] { this });
			} 
			catch (Exception e) {
				e.printStackTrace();
				_onFinishCallback = null;
			}
		}
	}
	
	public void reset() {
		_currentFrame = 0;
		_finished = false;
	}
}
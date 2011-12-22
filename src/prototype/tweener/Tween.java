package prototype.tweener;

import java.lang.reflect.*;

public class Tween implements Interval {
	public Object _parentObject;
	Field _field;
	int _easing;
	float _start;
	float _end;
	float _change;
	float _currentValue;
	int _duration;
	int _delay;
	public Method _onFinishCallback;
	int _currentFrame;
	boolean _finished;

	Tween(Object __parent, String __tweenField, int __easing, float __tweenV0, float __tweenV1, int __tweenDuration) {
		_parentObject = __parent;
		try { _field = _parentObject.getClass().getField(__tweenField); } 
		catch (Exception e) { System.out.println("Ivalid Field"); }
		setBasic(__easing, __tweenV0, __tweenV1, __tweenDuration);
		//updateField();
		_delay = 0;
	}

	Tween(Object __parent, String __tweenField, int __easing, float __tweenV0, float __tweenV1, int __tweenDuration, String __tweenCallback) {
		_parentObject = __parent;
		try { _onFinishCallback = _parentObject.getClass().getMethod(__tweenCallback, new Class[] { Tween.class }); }
		catch (Exception e) { System.out.println("Invalid Method"); }
		try { _field = _parentObject.getClass().getDeclaredField(__tweenField); } 
		catch (Exception e) { System.out.println("Ivalid Field"); }
		setBasic(__easing, __tweenV0, __tweenV1, __tweenDuration);
		//updateField();
		_delay = 0;
	}	

	Tween(Object __parent, String __tweenField, int __easing, float __tweenV0, float __tweenV1, int __tweenDuration, int __delay) {
		_parentObject = __parent;
		try { _field = _parentObject.getClass().getField(__tweenField); } 
		catch (Exception e) { System.out.println("Ivalid Field"); }
		setBasic(__easing, __tweenV0, __tweenV1, __tweenDuration);
		//updateField();
		_delay = __delay;
	}

	Tween(Object __parent, String __tweenField, int __easing, float __tweenV0, float __tweenV1, int __tweenDuration, int __delay, String __tweenCallback) {
		_parentObject = __parent;
		try { _onFinishCallback = _parentObject.getClass().getMethod(__tweenCallback, new Class[] { Tween.class }); }
		catch (Exception e) { System.out.println("Invalid Method"); }
		try { _field = _parentObject.getClass().getDeclaredField(__tweenField); } 
		catch (Exception e) { System.out.println("Ivalid Field"); }
		setBasic(__easing, __tweenV0, __tweenV1, __tweenDuration);
		//updateField();
		_delay = __delay;
	}	

	void setBasic(int __easing, float f0, float f1, int d) {
		_easing = __easing;
		_start = f0;
		_end = f1;
		_change = _end - _start;
		_currentValue = _start;
		_duration = d;
		_currentFrame = 0;
		_finished = false;
	}

	public void update() {
		if(!_finished) {
			if(_currentFrame > _delay) {
				if(_currentFrame <= _duration + _delay) {
					_currentValue = Ease.get(_easing, _currentFrame - _delay, _start, _change, _duration);
					_currentFrame++;
					updateField();
				} 
				else {
					try {
						_field.setFloat(_parentObject, _end);
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					_finished = true;
				}
			} else {
				_currentFrame++;
			}
		}
	}

	public boolean finished() {
		return _finished;
	}

	float getState() {
		return _currentValue;
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

	public void updateField() {
		try {
			if(_field.getType().getName() == "float") {
				_field.setFloat(_parentObject, _currentValue);
			}
			else if(_field.getType().getName() == "int") {
				_field.setInt(_parentObject, (int)_currentValue); 
			}      
		} catch (Exception e) { System.out.println("Invalid field. Class must be Public, Field must be Public and of the type float or int."); }
	}
}
package prototype.tweener;

public class Ease {

	public static final int easeNone = 0;
	public static final int easeInQuad = 1;
	public static final int easeOutQuad = 2;
	public static final int easeInOutQuad = 3;
	public static final int easeInCubic = 4;
	public static final int easeOutCubic = 5;
	public static final int easeInOutCubic = 6;
	public static final int easeInSine = 7;
	public static final int easeOutSine = 8;
	public static final int easeInOutSine = 9;
	public static final int easeInExpo = 10;
	public static final int easeOutExpo = 11;
	public static final int easeInOutExpo = 12;
	public static final int easeInCirc = 13;
	public static final int easeOutCirc = 14;
	public static final int easeInOutCirc = 15;
	public static final int easeInBounce = 16;
	public static final int easeOutBounce = 17;
	public static final int easeInOutBounce = 18;

	Ease() {
	}

	//The following equations were based on Robert Pernner's Easing Equations:
	//Available at: http://robertpenner.com/easing/

	static float get(int __easingEquation, float __time, float __begin, float __change, float __duration) {
		switch(__easingEquation) {
		case easeNone:
			return __change * __time / __duration + __begin;
		case easeInQuad:
			return __change * (__time/=__duration) * __time + __begin;
		case easeOutQuad:
			return -__change * (__time/=__duration)*(__time-2) + __begin;
		case easeInOutQuad:
			if ( (__time/=__duration/2) < 1) { return __change / 2 * __time * __time + __begin; }
			return -__change/2 * ((--__time)*(__time-2) - 1) + __begin;
		case easeInCubic:
			return (float) (__change * Math.pow (__time/__duration, 3) + __begin);
		case easeOutCubic:
			return (float) (__change * (Math.pow (__time/__duration-1, 3) + 1) + __begin);
		case easeInOutCubic:
			if ((__time/=__duration/2) < 1) { return (float) (__change/2 * Math.pow (__time, 3) + __begin); }
			return (float) (__change/2 * (Math.pow (__time-2, 3) + 2) + __begin);
		case easeInSine:
			return (float) (__change * (1 - Math.cos(__time/__duration * (Math.PI/2))) + __begin);
		case easeOutSine:
			return (float) (__change * Math.sin(__time/__duration * (Math.PI/2)) + __begin);
		case easeInOutSine:
			return (float) (__change/2 * (1 - Math.cos(Math.PI*__time/__duration)) + __begin);	
		case easeInExpo:
			return (float) (__change * Math.pow(2, 10 * (__time/__duration - 1)) + __begin);
		case easeOutExpo:
			return (float) (__change * (-Math.pow(2, -10 * __time/__duration) + 1) + __begin);
		case easeInOutExpo:
			if ((__time/=__duration/2) < 1) { return (float) (__change/2 * Math.pow(2, 10 * (__time - 1)) + __begin); }
			return (float) (__change/2 * (-Math.pow(2, -10 * --__time) + 2) + __begin);			
		case easeInCirc:
			return (float) (__change * (1 - Math.sqrt(1 - (__time/=__duration)*__time)) + __begin);
		case easeOutCirc:
			return (float) (__change * Math.sqrt(1 - (__time=__time/__duration-1)*__time) + __begin);
		case easeInOutCirc:
			if ((__time/=__duration/2) < 1) {	return (float) (__change/2 * (1 - Math.sqrt(1 - __time*__time)) + __begin); }
			return (float) (__change/2 * (Math.sqrt(1 - (__time-=2)*__time) + 1) + __begin);
		case easeInBounce:
			return __change - Ease.get(Ease.easeOutBounce, __duration-__time, 0, __change, __duration) + __begin;
		case easeOutBounce:
			if ((__time/=__duration) < (1/2.75)) {
				return (float) (__change*(7.5625*__time*__time) + __begin);
			} else if (__time < (2/2.75)) {
				return (float) (__change*(7.5625*(__time-=(1.5/2.75))*__time + .75) + __begin);
			} else if (__time < (2.5/2.75)) {
				return (float) (__change*(7.5625*(__time-=(2.25/2.75))*__time + .9375) + __begin);
			} else {
				return (float) (__change*(7.5625*(__time-=(2.625/2.75))*__time + .984375) + __begin);
			}
		case easeInOutBounce:
			if (__time < __duration/2) return (float) (Ease.get(Ease.easeInBounce, __time*2, 0, __change, __duration) * .5 + __begin);
			return (float) (Ease.get(Ease.easeOutBounce, __time*2-__duration, 0, __change, __duration) * .5 + __change*.5 + __begin);
		default:
			return 0;
		}
	}
}
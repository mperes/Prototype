package Prototype;

import processing.core.PGraphics3D;
import processing.core.PMatrix3D;

abstract public class Coordinates {

	final static PGraphics3D world = (PGraphics3D)Prototype.stage.g;

	public static float[] localMouse() {
		PMatrix3D cam = world.camera;
		PMatrix3D mvi = world.modelviewInv;  
		// First, transform along the camera matrix.
		float z = 0;
		float aX = Prototype.stage.mouseX*cam.m00 + Prototype.stage.mouseY*cam.m01 + z*cam.m02 + cam.m03;
		float aY = Prototype.stage.mouseX*cam.m10 + Prototype.stage.mouseY*cam.m11 + z*cam.m12 + cam.m13;
		// Next, transform along the modelviewInv matrix.
		float modelX = aX*mvi.m00 + aY*mvi.m01 + z*mvi.m02 + mvi.m03;
		float modelY = aX*mvi.m10 + aY*mvi.m11 + z*mvi.m12 + mvi.m13;
		
		return new float[] {modelX, modelY, 0};
	}

}

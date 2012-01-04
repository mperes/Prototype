package prototype;

import javax.media.opengl.GL;
import processing.core.PConstants;

public class Mask extends Part implements PrototypeConstants {
	public Mask(final int x, final int y, final int w, final int h) {
		super(
			w, h,
			new ShapeRender() {
				public void draw() {
					Prototype.stage.fill(0);
					Prototype.stage.noStroke();
					Prototype.stage.rectMode(PConstants.CORNER);
					Prototype.stage.rect(0, 0, w, h);
				}
			}
		);
		
		this.x(x);
		this.y(y);
	}
	
	@Override
	public void draw(boolean parentUpdated, boolean parentScaled, boolean parentChangedAlpha) {
		if(visible() && onScreen()) {
			if(parentScaled == true) { this.scaled = true; }
			if(parentChangedAlpha == true) { this.alphaChanged = true; }
			this.worldToLocal();
			if(updated || parentUpdated) {
				this.updateLocalModel();
			}
			if(updated || parentUpdated || alphaChanged) {
				this.updateAlphaStack();
			}
			
			drawShape();
			this.drawChildren();
			Prototype.pgl.endGL();
			this.localToWorld();
			updated = false;
			scaled = false;
			alphaChanged = false;
			stateChanged = false;
		}
	}
	
	@Override
	protected void drawShape() {
		Prototype.stage.pushMatrix();
		Prototype.stage.translate(-this.width()*this.pivotX(), -this.height()*this.pivotY());
		if(this.widthToScale() != 1 || this.heightToScale() != 1) {
			Prototype.stage.scale(this.widthToScale(), this.heightToScale());
		}
		Prototype.gl.glColorMask(false, false, false, false); 
		Prototype.gl.glDepthMask(true);
		Prototype.gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
		dynamicTexture[safeState()].draw();
		Prototype.gl.glDepthMask(false);
		Prototype.gl.glColorMask(true, true, true, true);
		Prototype.gl.glDepthFunc(GL.GL_EQUAL);
		Prototype.stage.popMatrix();
	}
}

package prototype;
import java.util.ArrayList;

import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PMatrix3D;

public class ImagePart extends Part {
	private PImage diffuseMap;
	private float[][][] faces;

	public ImagePart (String skin, Behavior... behaviors) {
		diffuseMap = Prototype.loadTexture(skin);
		setDefaultValues();
	}

	public ImagePart (PartBuilder builder) {
		//super(blueprint, x, y, behaviors);
	}

	protected void initPart (Skin blueprint) {
		basicSetup(blueprint);
		this.getBlueprint().initBlueprint(Prototype.offScreenBuffer);

		this.readBlueprint();
		this.calcBox();
	}

	protected void readBlueprint() {
		Prototype.offScreenBuffer.beginDraw();
		getBlueprint().description();
		Prototype.offScreenBuffer.endDraw();
		diffuseMap = Prototype.addDiffuseMap(getBlueprint(), getWidth(), getHeight());
		if(getBlueprint().scaleGrid != null) {
			faces = new float[3][3][8];
		}
	}

	public void drawPart() {
		Prototype.stage.tint(255, 255*getAlpha());
		if(getBlueprint().scaleGrid != null) {
			//scale9Grid(this.getWidth(), this.getHeight(), this.getPivotX(), this.getPivotY(), getBlueprint().scaleGrid, this.diffuseMap);
			scale9Grid(getBlueprint().width, getBlueprint().height, this.getPivotX(), this.getPivotY(), getBlueprint().scaleGrid, this.diffuseMap);
		} else {
			//drawPlane(this.getWidth(), this.getHeight(), this.getPivotX(), this.getPivotY(), this.diffuseMap);
			drawPlane(getBlueprint().width, getBlueprint().height, this.getPivotX(), this.getPivotY(), this.diffuseMap);
		}
	}

	@SuppressWarnings("deprecation")
	void drawPlane(float width, float height, float pivotX, float pivotY, PImage texture) {
		Prototype.stage.pushMatrix();
		if(widthToScale() != 1 || heightToScale() != 1) {
			Prototype.stage.scale(widthToScale(), heightToScale());
		}
		Prototype.stage.pushStyle();
		Prototype.stage.textureMode(PConstants.NORMALIZED);
		Prototype.stage.noStroke();
		Prototype.stage.beginShape();
		Prototype.stage.texture(texture);
		Prototype.stage.vertex(-width*pivotX, -height*pivotY, 0, 0);
		Prototype.stage.vertex(width*(1-pivotX), -height*pivotY, 1, 0);
		Prototype.stage.vertex(width*(1-pivotX), height*(1-pivotY), 1, 1);
		Prototype.stage.vertex(-width*pivotX, height*(1-pivotY), 0, 1);
		Prototype.stage.endShape(PConstants.CLOSE);
		Prototype.stage.popStyle();
		Prototype.stage.popMatrix();
	}

	void scale9Grid(int width, int height, float pivotX, float pivotY, Box box, PImage texture) {
		Prototype.stage.pushMatrix();
		Prototype.stage.translate(-getWidth()*pivotX, -getHeight()*pivotY);
		if(widthToScale() != 1 || heightToScale() != 1) {
			Prototype.stage.scale(widthToScale(), heightToScale());
		}
		faces[0] = new float[][] {
				{ 0, 0, box.left/widthToScale(), box.top/heightToScale(), 0, 0, box.left, box.top }, 
				{ box.left/widthToScale(), 0, width-box.right/widthToScale(), box.top/heightToScale(), box.left, 0, texture.width-box.right, box.top },
				{ width-box.right/widthToScale(), 0, width, box.top/heightToScale(), texture.width-box.right, 0, texture.width, box.top } 
		};
		faces[1] = new float[][]{ 
				{ faces[0][0][0], box.top/heightToScale(), faces[0][0][2], height-box.bottom/heightToScale(), faces[0][0][4], box.top+1, faces[0][0][6],  texture.height-box.bottom-1 }, 
				{ faces[0][1][0], box.top/heightToScale(), faces[0][1][2], height-box.bottom/heightToScale(), faces[0][1][4], box.top+1, faces[0][1][6],  texture.height-box.bottom-1 },
				{ faces[0][2][0], box.top/heightToScale(), faces[0][2][2], height-box.bottom/heightToScale(), faces[0][2][4], box.top+1, faces[0][2][6],  texture.height-box.bottom-1 } 
		};
		faces[2] = new float[][] {
				{ faces[0][0][0], height-box.bottom/heightToScale(), faces[0][0][2], height, faces[0][0][4], texture.height-box.bottom, faces[0][0][6],  texture.height},
				{ faces[0][1][0], height-box.bottom/heightToScale(), faces[0][1][2], height, faces[0][1][4], texture.height-box.bottom, faces[0][1][6],  texture.height},
				{ faces[0][2][0], height-box.bottom/heightToScale(), faces[0][2][2], height, faces[0][2][4], texture.height-box.bottom, faces[0][2][6],  texture.height}
		};	
		Prototype.stage.pushStyle();
		Prototype.stage.noStroke();
		Prototype.stage.textureMode(PConstants.IMAGE);
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {
				rectShape(texture, faces[row][col][0], faces[row][col][1], faces[row][col][2], faces[row][col][3], faces[row][col][4], faces[row][col][5], faces[row][col][6], faces[row][col][7]);
			}
		}
		Prototype.stage.popStyle();
		Prototype.stage.popMatrix();
	}

	private void rectShape(PImage texture, float x1, float y1, float x2, float y2, float tx1, float ty1, float tx2, float ty2) {
		Prototype.stage.beginShape();
		Prototype.stage.texture(texture);
		Prototype.stage.vertex(x1, y1, tx1, ty1);
		Prototype.stage.vertex(x2, y1, tx2, ty1);
		Prototype.stage.vertex(x2, y2, tx2, ty2);
		Prototype.stage.vertex(x1, y2, tx1, ty2);
		Prototype.stage.endShape(PConstants.CLOSE);
	}

	public boolean mouseInside() {
		switch(getBlueprint().collisionMethod) {
		case Part.DEFAULT:
			return pixelCollide(localMouseX(), localMouseY(), diffuseMap);
		case Part.BOX:
			return boxCollide(localMouseX(), localMouseY());
		case Part.CIRCLE:
			return circleCollide(localMouseX(), localMouseY());
		case Part.PIXEL:
			return pixelCollide(localMouseX(), localMouseY(), diffuseMap);
		default:
			System.err.println("Invalid Collision Method. Switching to Box Collision");
			return boxCollide(localMouseX(), localMouseY());
		}
	}
}

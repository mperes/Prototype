package prototype;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

public class TextRender implements DynamicImage {
	String text;
	PFont font;
	int width;
	int height;
	String cropChar;
	String formatedText;
	
	public TextRender(String text, PFont font) {
		this.text = text;
		this.font = font;
		Prototype.stage.textFont(font);
		this.width =  PApplet.ceil(Prototype.stage.textWidth(text));
		this.height = PApplet.ceil(Prototype.stage.textAscent()+Prototype.stage.textDescent());
		this.cropChar = "...";
		formatText();
	}
	
	public TextRender(String text, PFont font, int width) {
		this.text = text;
		this.font = font;
		Prototype.stage.textFont(font);
		this.width =  width;
		this.height = PApplet.ceil(Prototype.stage.textAscent()+Prototype.stage.textDescent());
		this.cropChar = "...";
		formatText();
	}
	
	public void draw() {
		Prototype.stage.smooth();
		Prototype.stage.textAlign(PConstants.LEFT, PConstants.TOP);
		Prototype.stage.text(formatedText, 0, 0);	
	}
	
	private void formatText() {
		this.formatedText = "" + this.text;
		if(Prototype.stage.textWidth(formatedText) > width) {
			while(Prototype.stage.textWidth(formatedText+cropChar) > width) {
				formatedText = formatedText.substring(0, formatedText.length()-1);
			}
			formatedText = formatedText + cropChar;
		}
	}

}

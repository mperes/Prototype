package prototype;

public class PartList extends Part {
	private int margin;
	
	public PartList(Part...parts) {
		super(
			Utils.getPartsWidth(parts),
			Utils.getPartsHeight(parts),
			new ShapeRender() {
				public void draw() {}
			}
		);
		initList(parts);
		margin = 0;
	}
	
	private void initList(Part...parts) {
		int accumulatedHeight = 0;
		for(Part part : parts) {
			this.part(part);
			this.y(accumulatedHeight);
			accumulatedHeight += this.height();
		}
	}
	
	public void updateList() {
		int accumulatedHeight = 0;
		int width = 0;
		for(int y = 0; y < parts.size(); y++) {
			parts.get(y).y(accumulatedHeight + y*margin);
			accumulatedHeight += parts.get(y).height();
			width = (parts.get(y).width() > width) ? parts.get(y).width() : width;
		}
		this.height(accumulatedHeight + (parts.size()-1)*margin);
		this.width(width);
	}
	
	public void set(int index, Part part) {
		parts.set(index, part);
		updateList();
	}
	
	public void add(Part part) {
		parts.add(part);
		updateList();
	}
	
	public Part get(int index) {
		return parts.get(index);
	}
	
	public int margin() { return this.margin; }
	public void margin(int value) { this.margin = value; updateList(); }
}

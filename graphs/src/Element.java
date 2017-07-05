

public class Element {

	public Object object;
	public Element parent;
	public int rank;
	
	public Element(Object object){
		this.object = object;
		this.parent = this;
		this.rank = 0;
	}
}

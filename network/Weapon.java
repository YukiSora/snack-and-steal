public class Weapon implements java.io.Serializable{
	int id;
	String name;
	double size;
	
	Weapon(){
		id = 0;
		name = "";
		size = 0;
	}
	
	Weapon(int id, String name, double d){
		this.id = id;
		this.name = name;
		this.size = d;
	}
	
	public String toString(){
		return "id=" + id + "&name=" + name + "&size=" + size;
	}
}

package kr.or.ddit.designpattern.adapter.example;

public class RoomKST {
	public ConcentKST concentKST= new ConcentKST() ;
	
	public static void main(String[] args) {
		RoomKST  room = new RoomKST();
		
		PluggableKST samsung = new SamsumgProduct();
		PluggableKST lg = new LGProduct();
		PluggableCN xiao = new XiaomiProduct();
		
		room.concentKST.plugin(samsung);
		room.concentKST.plugin(lg);
		room.concentKST.plugin(new AdapterPlug(xiao));
	}
}

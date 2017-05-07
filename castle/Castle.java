package castle;

public class Castle {
	private Room currentRoom;
	
	public Castle() 
    {
		createRooms();    	
    }
	
	private void createRooms()
    {
        Room outside, lobby, pub, study, bedroom, dinning, garden;
      
        //	制造房间
        outside = new Room("城堡外");
        lobby = new Room("大堂");
        pub = new Room("小酒吧");
        study = new Room("书房");
        bedroom = new Room("卧室");
        dinning = new Room("餐厅");
        garden = new Room("花园");
        
        
        //	初始化房间的出口
        outside.setExit("east", lobby);
        outside.setExit("south", study);
        outside.setExit("west", pub);
        lobby.setExit("west", outside);
        pub.setExit("east", outside);
        study.setExit("north",outside);
        study.setExit("east", bedroom);
        bedroom.setExit("west", study);
        lobby.setExit("up", pub);
        pub.setExit("down", lobby);
        lobby.setExit("north",dinning);
        lobby.setExit("east", garden);
        dinning.setExit("south", lobby);
        garden.setExit("west", lobby);

        currentRoom = outside;  //	从城堡门外开始
    }
	
	public Room goRoom(String direction) 
    {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            return null;
        }
        else {
            currentRoom = nextRoom;
            return currentRoom;
        }
    }
	
	public Room getCurrentRoom()
	{
		return currentRoom;
	}
}

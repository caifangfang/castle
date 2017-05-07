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
      
        //	���췿��
        outside = new Room("�Ǳ���");
        lobby = new Room("����");
        pub = new Room("С�ư�");
        study = new Room("�鷿");
        bedroom = new Room("����");
        dinning = new Room("����");
        garden = new Room("��԰");
        
        
        //	��ʼ������ĳ���
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

        currentRoom = outside;  //	�ӳǱ����⿪ʼ
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

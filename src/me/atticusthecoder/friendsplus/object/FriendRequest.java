package me.atticusthecoder.friendsplus.object;

public class FriendRequest {
	Friend from;
	PlayerData to;
	boolean valid;
	
	public FriendRequest(Friend from, PlayerData to) {
		this.from = from;
		this.to = to;
		valid = true;
		
		Thread t = new Thread() {
			public void run() {
				try {
					Thread.sleep(1000 * 300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				// Then close the friend request
				valid = false;
			}
		};
		t.start();
	}
	
	public void disable() {
		valid = false;
	}
	
	public Friend getFrom() {
		return from;
	}
	
	public PlayerData getTo() {
		return to;
	}
	
	public String getFromName() {
		return from.getName();
	}
	
	public String getToName() {
		return to.getPlayer().getName();
	}
}

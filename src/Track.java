
public class Track {

	private String startImage; // 게임 선택 창 표지 이미지
	private String gameImage; // 해당 곡을 실행했을 때 표지 이미지
	private String gameMusic; // 해당 곡을 실행했을 때 음악
	

	public String getStartImage() {
		return startImage;
	}
	public void setStartImage(String startImage) {
		this.startImage = startImage;
	}
	public String getGameImage() {
		return gameImage;
	}
	public void setGameImage(String gameImage) {
		this.gameImage = gameImage;
	}

	public String getGameMusic() {
		return gameMusic;
	}
	public void setGameMusic(String gameMusic) {
		this.gameMusic = gameMusic;
	}
	
	public Track(String startImage, String gameImage, String gameMusic) {
		super();
		this.startImage = startImage;
		this.gameImage = gameImage;
		this.gameMusic = gameMusic;
	}
	
}
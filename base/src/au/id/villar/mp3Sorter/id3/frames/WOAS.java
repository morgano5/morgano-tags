package au.id.villar.mp3Sorter.id3.frames;

public class WOAS extends UrlFrame {

	WOAS(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Official audio source webpage";
	}
}

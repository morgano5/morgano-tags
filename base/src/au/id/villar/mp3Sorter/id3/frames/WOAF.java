package au.id.villar.mp3Sorter.id3.frames;

public class WOAF extends UrlFrame {

	WOAF(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Official audio file webpage";
	}
}

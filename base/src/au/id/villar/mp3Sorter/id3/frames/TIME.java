package au.id.villar.mp3Sorter.id3.frames;

public class TIME extends TextFrame {

	TIME(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Time";
	}
}

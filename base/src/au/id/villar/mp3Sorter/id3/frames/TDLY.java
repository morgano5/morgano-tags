package au.id.villar.mp3Sorter.id3.frames;

public class TDLY extends TextFrame {

	TDLY(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Playlist delay";
	}
}

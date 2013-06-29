package au.id.villar.mp3Sorter.id3.frames;

public class TOAL extends TextFrame {

	TOAL(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Original album/movie/show title";
	}
}

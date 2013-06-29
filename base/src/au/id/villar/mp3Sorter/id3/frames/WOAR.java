package au.id.villar.mp3Sorter.id3.frames;

public class WOAR extends UrlFrame {

	WOAR(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Official artist/performer webpage";
	}
}

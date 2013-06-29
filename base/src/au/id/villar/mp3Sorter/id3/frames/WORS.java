package au.id.villar.mp3Sorter.id3.frames;

public class WORS extends UrlFrame {

	WORS(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Official internet radio station homepage";
	}
}

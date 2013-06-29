package au.id.villar.mp3Sorter.id3.frames;

public class WPUB extends UrlFrame {

	WPUB(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Publishers official webpage";
	}
}

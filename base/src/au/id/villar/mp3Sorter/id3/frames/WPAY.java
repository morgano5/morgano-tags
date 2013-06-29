package au.id.villar.mp3Sorter.id3.frames;

public class WPAY extends UrlFrame {

	WPAY(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Payment";
	}
}

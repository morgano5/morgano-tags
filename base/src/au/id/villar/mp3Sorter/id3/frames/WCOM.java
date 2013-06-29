package au.id.villar.mp3Sorter.id3.frames;

public class WCOM extends UrlFrame {

	WCOM(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Commercial information";
	}
}

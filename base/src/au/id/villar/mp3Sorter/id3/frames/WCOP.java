package au.id.villar.mp3Sorter.id3.frames;

public class WCOP extends UrlFrame {

	WCOP(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Copyright/Legal information";
	}
}

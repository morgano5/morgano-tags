package au.id.villar.mp3Sorter.id3.frames;

public class TALB extends TextFrame {

	TALB(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Album/Movie/Show title";
	}
}

package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

public class APIC extends Frame {

	private String mimeType;
	private PictureType type;
	private String description;
	private byte[] data;

	public APIC(FrameHeader header) {
		super(header);
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public PictureType getType() {
		return type;
	}

	public void setType(PictureType type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException,
			InvalidTagException {
		boolean unicode;

		unicode = reader.readBodyByte() == 1;
		mimeType = reader.readString(unicode);
		type = PictureType.getPictureType(reader.readBodyByte());
		description = reader.readString(unicode);
		data = reader.readRestOfFrame();
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(" ").append(type.getDescription())
				.append(", MIME type: ").append(mimeType).append(", description: \"")
				.append(description).append('"').toString();
	}

	public static enum PictureType {

		OTHER(0x0, "Other"),
		PNG32x32(0x1, "32x32 pixels 'file icon'"),
		OTHERICON(0x2, "Other file icon"),
		COVER_FRONT(0x3, "Cover (front)"),
		COVER_BACK(0x4, "Cover (back)"),
		LEAFLET_PAGE(0x5, "Leaflet page"),
		MEDIA(0x6, "Media"),
		LEADER(0x7, "Lead artist/lead performer/soloist"),
		ARTIST(0x8, "Artist/performer"),
		CONDUCTOR(0x9, "Conductor"),
		BAND(0xA, "Band/Orchestra"),
		COMPOSER(0xB, "Composer"),
		LYRICIST(0xC, "Lyricist/text writer"),
		LOCATION(0xD, "Recording Location"),
		RECORDING(0xE, "During recording"),
		PERFORMANCE(0xF, "During performance"),
		MOVIE(0x10, "Movie/video screen capture"),
		FISH(0x11, "A bright coloured fish"),
		ILLUSTRATION(0x12, "Illustration"),
		LOGO(0x13, "Band/artist logotype"),
		PUBLISHER(0x14, "Publisher/Studio logotype");

		private int type;
		private String description;

		PictureType(int type, String description) {
			this.type = type;
			this.description = description;
		}

		public int getType() {
			return type;
		}

		public String getDescription() {
			return description;
		}

		public static PictureType getPictureType(int type) {
			switch(type) {
			case 0x0:
				return PictureType.OTHER;
			case 0x1:
				return PictureType.PNG32x32;
			case 0x2:
				return PictureType.OTHERICON;
			case 0x3:
				return PictureType.COVER_FRONT;
			case 0x4:
				return PictureType.COVER_BACK;
			case 0x5:
				return PictureType.LEAFLET_PAGE;
			case 0x6:
				return PictureType.MEDIA;
			case 0x7:
				return PictureType.LEADER;
			case 0x8:
				return PictureType.ARTIST;
			case 0x9:
				return PictureType.CONDUCTOR;
			case 0xA:
				return PictureType.BAND;
			case 0xB:
				return PictureType.COMPOSER;
			case 0xC:
				return PictureType.LYRICIST;
			case 0xD:
				return PictureType.LOCATION;
			case 0xE:
				return PictureType.RECORDING;
			case 0xF:
				return PictureType.PERFORMANCE;
			case 0x10:
				return PictureType.MOVIE;
			case 0x11:
				return PictureType.FISH;
			case 0x12:
				return PictureType.ILLUSTRATION;
			case 0x13:
				return PictureType.LOGO;
			case 0x14:
				return PictureType.PUBLISHER;
			default:
				throw new IllegalArgumentException("PictureType value not known");
			}
		}
	}

	@Override
	public String getFrameDescription() {
		return "Attached picture";
	}
}

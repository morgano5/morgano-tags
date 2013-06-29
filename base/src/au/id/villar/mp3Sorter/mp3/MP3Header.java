package au.id.villar.mp3Sorter.mp3;

public class MP3Header {

	public enum MPEGVersion {
		MPEG_1,
		MPEG_2,
		MPEG_2_5,
		RESERVED
	}

	public enum Layer {
		RESERVED,
		I, II, III
	}

	public enum ChannelMode {
		STEREO,
		JOINT_STEREO,
		DUAL_CHANNEL,
		SINGLE_CHANNEL
	}

	private int rawHeader;

	public void setRawHeader(int rawHeader) {
		this.rawHeader = rawHeader;
	}

	public void setRawHeader(byte[] rawHeader) {
		int intRawHeader = 0;
		intRawHeader = (rawHeader[0] & 0xFF) << 24 | (rawHeader[1] & 0xFF) << 16 |
				(rawHeader[2] & 0xFF) << 8 | (rawHeader[3] & 0xFF);
		setRawHeader(intRawHeader);
	}

	public boolean hasSyncBits() {
		return ((rawHeader >> 20) & 0xFFE) == 0xFFE;
	}


	public MPEGVersion getMPEGVersion() {
		switch((rawHeader >>> 19) & 3) {
		case 0: return MPEGVersion.MPEG_2_5;
		case 1: return MPEGVersion.RESERVED;
		case 2: return MPEGVersion.MPEG_2;
		case 3: return MPEGVersion.MPEG_1;
		}
		return null;
	}

	public Layer getLayer() {
		switch((rawHeader >>> 17) & 3) {
		case 0: return Layer.RESERVED;
		case 1: return Layer.III;
		case 2: return Layer.II;
		case 3: return Layer.I;
		}
		return null;
	}

	public boolean isProtected() {
		return (rawHeader & 0x10000) == 0;
	}

//	private static int[] bitRates = {
//		 32,  32,  32,  32,   8,
//		 64,  48,  40,  48,  16,
//		 96,  56,  48,  56,  24,
//		128,  64,  56,  64,  32,
//		160,  80,  64,  80,  40,
//		192,  96,  80,  96,  48,
//		224, 112,  96, 112,  56,
//		256, 128, 112, 128,  64,
//		288, 160, 128, 144,  80,
//		320, 192, 160, 160,  96,
//		352, 224, 192, 176, 112,
//		384, 256, 224, 192, 128,
//		416, 320, 256, 224, 144,
//		448, 384, 320, 256, 160
//	};


	private static int[] bitRates = {
		 32,  32,  32,  32,  32,   8,
		 64,  48,  40,  64,  48,  16,
		 96,  56,  48,  96,  56,  24,
		128,  64,  56, 128,  64,  32,
		160,  80,  64, 160,  80,  64,
		192,  96,  80, 192,  96,  80,
		224, 112,  96, 224, 112,  56,
		256, 128, 112, 256, 128,  64,
		288, 160, 128, 288, 160, 128,
		320, 192, 160, 320, 192, 160,
		352, 224, 192, 352, 224, 112,
		384, 256, 224, 384, 256, 128,
		416, 320, 256, 416, 320, 256,
		448, 384, 320, 448, 384, 320
	};

	public int getBitRate() {
		MPEGVersion version;
		Layer layer;
		int index;

		index = (rawHeader >>> 12) & 0xF;
		if(index == 0xF)
			return -1; // BAD
		if(index == 0)
			return 0; // FREE
		index--;




		index *= 6;
		version = getMPEGVersion();
		layer = getLayer();
		switch(version) {
		case MPEG_1:
			break;
		case MPEG_2: case MPEG_2_5:
			index += 3;
			break;
		case RESERVED:
			return -1;
		}
		switch(layer) {
		case RESERVED:
			return -1;
		case III:
			if(version != MPEGVersion.MPEG_2
					&& version != MPEGVersion.MPEG_2_5)
				index++;
		case II:
			index++;
		case I:
			break;
		}
		return bitRates[index] * 1000;






//		index *= 5;
//		version = getMPEGVersion();
//		layer = getLayer();
//		switch(version) {
//		case MPEG_1:
//			break;
//		case MPEG_2: case MPEG_2_5:
//			index += 3;
//			break;
//		case RESERVED:
//			return -1;
//		}
//		switch(layer) {
//		case RESERVED:
//			return -1;
//		case III:
//			if(version != MPEGVersion.MPEG_2
//					&& version != MPEGVersion.MPEG_2_5)
//				index++;
//		case II:
//			index++;
//		case I:
//			break;
//		}
////		TODO allowed modes
//		return bitRates[index] * 1000;
	}

	private static int[] sampligRates = {
		44100, 22050, 11025,
		48000, 24000, 12000,
		32000, 16000, 8000
	};

	public int getSampleRate() {
		int index;

		index = (rawHeader >>> 10) & 3;
		if(index == 3)
			return -1;
		index *= 3;
		switch(getMPEGVersion()) {
		case MPEG_2_5:
			index++;
		case MPEG_2:
			index++;
		case MPEG_1:
			break;
		default:
			return -1;
		}
		return sampligRates[index];
	}

	public boolean isPadded() {
		return (rawHeader & 0x200) != 0;
	}

	public boolean isPrivate() {
		return (rawHeader & 0x100) != 0;
	}

	public ChannelMode getChannelMode() {
		switch((rawHeader >>> 6) & 3) {
		case 0: return ChannelMode.STEREO;
		case 1: return ChannelMode.JOINT_STEREO;
		case 2: return ChannelMode.DUAL_CHANNEL;
		case 3: return ChannelMode.SINGLE_CHANNEL;
		default: return null;
		}
	}

	public boolean isCopyrighted() {
		return (rawHeader & 0x8) != 0;
	}

	public boolean isOriginal() {
		return (rawHeader & 0x4) != 0;
	}

	public int getFrameLength() {
		switch(getLayer()) {
		case I:
			return (12 * getBitRate() / getSampleRate() + (isPadded()? 1: 0)) * 4;
		case II: case III:
			return 144 * getBitRate() / getSampleRate() + (isPadded()? 1: 0);
		default:
			return -1;
		}

// FrameSize = 144 * BitRate / (SampleRate + Padding)
	}

	// ------------------------------------

	public int getModeExtension() {
		return (rawHeader >>> 4) & 3;
	}
	// TODO lower bits

/*

Emphasis value   Emphasis method
0 0              none
0 1              50/15ms
1 0
1 1              CCITT j.17

*/

}

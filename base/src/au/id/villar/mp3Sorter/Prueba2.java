package au.id.villar.mp3Sorter;

public class Prueba2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		cuentaFrames(args);
//		extractMP3Data(args);
	}

/*
	public static void cuentaFrames(String[] args) {

		Mp3Header header = new Mp3Header();
		FileInputStream fileInput = null;
		int byteRead, status, rawHeader;
		int total = 0;

		rawHeader = 0;
		status = 0;
		try {
			fileInput = new FileInputStream(new File(args[0]));
			while((byteRead = fileInput.read()) != -1) {
				switch(status) {
				case 0:
					if(byteRead == 0xFF)
						status = 1;
					break;
				case 1:
					status = ((byteRead & 0xF0) == 0xF0)? 2: 0;
					if(status == 2) {
						rawHeader = (byteRead << 16) + 0xFF000000;
					}
					break;
				case 2:
					status++;
					rawHeader += (byteRead & 0xFF) << 8;
					break;
				case 3:
					total++;
					status = 0;
					rawHeader += byteRead & 0xFF;
					header.setRawHeader(rawHeader);
					fileInput.skip(header.getFrameLength() - 4);
					break;
				}
			}
			System.out.println("TOTAL: " + total);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try { if(fileInput != null) fileInput.close(); }
			catch(IOException e) {}
		}
	}


	public static void extractMP3Data(String[] args) {

		FileInputStream fileInput = null;
		int byteRead, status, rawHeader;
		int sum, crc, minus;
		int total = 0, totalSpare = 0, totalID3Headers = 0, totalID3v2Headers = 0;
		boolean par;
		int offset;

		FileOutputStream fileOutput = null;


		rawHeader = 0;
		status = 0;
		try {
			fileInput = new FileInputStream(new File(args[0]));
			fileOutput = new FileOutputStream(new File(args[1]));
			while((byteRead = fileInput.read()) != -1) {
				switch(status) {
				case 0:
					switch(byteRead) {
					case 0xFF: status = 1; break;
					case 'T': status = 4; break;
					case 'I':

						try {
							ID3v23Tag h = (ID3v23Tag)ID3v2Tag.readFromStreamSecondByte(fileInput);
							System.out.println("Header: size: " + h.getSize());
							for(ID3v23Frame f: h.getFrames()) {
								System.out.println("    frame: " + f);
							}
							totalID3v2Headers++;
							status = 0;
						} catch (Exception e) {
							System.out.println("valio madre");
							e.printStackTrace();
							System.exit(-1);
						}

						break;
					default: totalSpare++;
					}
					break;
				case 1:
					status = ((byteRead & 0xF0) == 0xF0)? 2: 0;
					if(status == 2) {
//						printBits((byte)0xFF);
//						printBits((byte)byteRead);
						rawHeader = (byteRead << 16) + 0xFF000000;
					}
					break;
				case 2:
					status++;
//					printBits((byte)byteRead);
					rawHeader += (byteRead & 0xFF) << 8;
					break;
				case 3: // MP3 header
					total++;
					status = 0;
//					printBits((byte)byteRead);
					rawHeader += byteRead & 0xFF;

					for(int x = 0; x < 4; x++) {
						fileOutput.write((rawHeader >> ((3 - x) * 8)) & 0xFF);
					}

					Mp3Header header = new Mp3Header();
					header.setRawHeader(rawHeader);
					crc = 0;
					minus = 4;
					par = false;
					if(header.isProtected()) {
						byteRead = fileInput.read();
						fileOutput.write(byteRead);
						crc = byteRead << 8;
						byteRead = fileInput.read();
						fileOutput.write(byteRead);
						crc += byteRead;
						minus = 6;
					}
					sum = rawHeader;
					for(int x = 0; x < header.getFrameLength() - minus; x++) {
						byteRead = fileInput.read();
						fileOutput.write(byteRead);
						sum += byteRead << (par? 0: 8);
						par = !par;
					}
					while((sum & 0xFFFF0000) != 0) {
						sum = (sum >>> 16) + (sum & 0xFFFF);
					}
//					System.out.print(" " + header.getMPEGVersion());
//					System.out.print(" " + header.getLayer());
//					System.out.print(" " + header.isProtected());
//					System.out.print(" " + header.getBitRate());
//					System.out.print(" " + header.getSampleRate());
//					System.out.print(" " + header.isPadded());
//					System.out.print(" " + header.getFrameLength());
//					System.out.print(" " + header.isPrivated());
//					System.out.print(" " + header.getChannelMode());
//					System.out.print(" " + header.isCopyrighted());
//					System.out.print(" " + header.isOriginal());
//					System.out.print(" " + Integer.toHexString(crc) + " " + Integer.toHexString(sum));
//					System.out.println();
					break;
				case 4: // ID3 v1 tag
					offset = 124;
					fileInput.read();
					byteRead = fileInput.read();
					if(byteRead == '+')
						offset = 223;
					while(offset-- > 0)
						fileInput.read();
					status = 0;
					totalID3Headers++;
					break;
				}
			}
			System.out.println("TOTAL: " + total + ", status 0: " + totalSpare + ", ID3 headers: " + totalID3Headers
					+ ", ID3v2 headers: " + totalID3v2Headers);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try { if(fileInput != null) fileInput.close(); }
			catch(IOException e) {}
			try { if(fileOutput != null) fileOutput.close(); }
			catch(IOException e) {}
		}
	}


	private static void printBits(byte b) {
		int p = 0x80;
		while(p != 0) {
			System.out.print((((b & p) != 0)? 1: 0));
			p >>= 1;
		}
		System.out.print(" ");
	}

	static class Mp3Header {
		private int rawHeader;

		public void setRawHeader(int rawHeader) {
			this.rawHeader = rawHeader;
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

		private static int[] bitRates = {
			 32,  32,  32,  32,   8,
			 64,  48,  40,  48,  16,
			 96,  56,  48,  56,  24,
			128,  64,  56,  64,  32,
			160,  80,  64,  80,  40,
			192,  96,  80,  96,  48,
			224, 112,  96, 112,  56,
			256, 128, 112, 128,  64,
			288, 160, 128, 144,  80,
			320, 192, 160, 160,  96,
			352, 224, 192, 176, 112,
			384, 256, 224, 192, 128,
			416, 320, 256, 224, 144,
			448, 384, 320, 256, 160
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
			index *= 5;
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
	//		TODO allowed modes
			return bitRates[index];
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

		public boolean isPrivated() {
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
				return (12000 * getBitRate() / getSampleRate() + (isPadded()? 1: 0)) * 4;
			case II: case III:
				return 144000 * getBitRate() / getSampleRate() + (isPadded()? 1: 0);
			default:
				return -1;
			}
		}

		// ------------------------------------

		public int getModeExtension() {
			return (rawHeader >>> 4) & 3;
		}
		// TODO lower bits
	}

	enum MPEGVersion {
		MPEG_1,
		MPEG_2,
		MPEG_2_5,
		RESERVED
	}

	enum Layer {
		RESERVED,
		I, II, III
	}

	enum ChannelMode {
		STEREO,
		JOINT_STEREO,
		DUAL_CHANNEL,
		SINGLE_CHANNEL
	}

*/
}

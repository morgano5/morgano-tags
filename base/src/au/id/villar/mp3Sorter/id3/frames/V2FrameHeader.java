package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

public class V2FrameHeader extends FrameHeader {

	@Override
	public Frame readFromStream(ID3Reader reader) throws IOException, InvalidTagException {
		Frame frame;
		byte[] bytesId;
		int size;

		bytesId = new byte[3];
		bytesId[0] = (byte)reader.readBodyByte();
		if(bytesId[0] == 0) {
			return null;
		}
		bytesId[1] = (byte)reader.readBodyByte();
		bytesId[2] = (byte)reader.readBodyByte();
		id = new String(bytesId);
		size = (reader.readBodyByte() << 16) | (reader.readBodyByte() << 8) | reader.readBodyByte();
		reader.setFrameCounter(size);
		switch(id) {
		case "BUF": frame = new RBUF(this); break;
		case "CNT": frame = new PCNT(this); break;
		case "COM": frame = new COMM(this); break;
		case "CRA": frame = new AENC(this); break;
		case "CRM": frame = new CRM(this); break;
		case "EQU": frame = new EQUA(this); break;
		case "ETC": frame = new ETCO(this); break;
		case "GEO": frame = new GEOB(this); break;
		case "IPL": frame = new IPLS(this); break;
		case "LNK": frame = new LINK(this); break;
		case "MCI": frame = new MCDI(this); break;
		case "MLL": frame = new MLLT(this); break;
		case "PIC": frame = new APIC(this); break;
		case "POP": frame = new POPM(this); break;
		case "REV": frame = new RVRB(this); break;
		case "RVA": frame = new RVAD(this); break;
		case "SLT": frame = new SYLT(this); break;
		case "STC": frame = new SYTC(this); break;
		case "TAL": frame = new TALB(this); break;
		case "TBP": frame = new TBPM(this); break;
		case "TCM": frame = new TCOM(this); break;
		case "TCO": frame = new TCON(this); break;
		case "TCR": frame = new TCOP(this); break;
		case "TDA": frame = new TDAT(this); break;
		case "TDY": frame = new TDLY(this); break;
		case "TEN": frame = new TENC(this); break;
		case "TFT": frame = new TFLT(this); break;
		case "TIM": frame = new TIME(this); break;
		case "TKE": frame = new TKEY(this); break;
		case "TLA": frame = new TLAN(this); break;
		case "TLE": frame = new TLEN(this); break;
		case "TMT": frame = new TMED(this); break;
		case "TOA": frame = new TOPE(this); break;
		case "TOF": frame = new TOFN(this); break;
		case "TOL": frame = new TOLY(this); break;
		case "TOR": frame = new TORY(this); break;
		case "TOT": frame = new TOAL(this); break;
		case "TP1": frame = new TPE1(this); break;
		case "TP2": frame = new TPE2(this); break;
		case "TP3": frame = new TPE3(this); break;
		case "TP4": frame = new TPE4(this); break;
		case "TPA": frame = new TPOS(this); break;
		case "TPB": frame = new TPUB(this); break;
		case "TRC": frame = new TRCK(this); break;
		case "TRD": frame = new TRDA(this); break;
		case "TRK": frame = new TRCK(this); break;
		case "TSI": frame = new TSIZ(this); break;
		case "TSS": frame = new TSSE(this); break;
		case "TT1": frame = new TIT1(this); break;
		case "TT2": frame = new TIT2(this); break;
		case "TT3": frame = new TIT3(this); break;
		case "TXT": frame = new TEXT(this); break;
		case "TXX": frame = new TXXX(this); break;
		case "TYE": frame = new TYER(this); break;
		case "UFI": frame = new UFID(this); break;
		case "ULT": frame = new USLT(this); break;
		case "WAF": frame = new WOAF(this); break;
		case "WAR": frame = new WOAR(this); break;
		case "WAS": frame = new WOAS(this); break;
		case "WCM": frame = new WCOM(this); break;
		case "WCP": frame = new WCOP(this); break;
		case "WPB": frame = new WPUB(this); break;
		case "WXX": frame = new WXXX(this); break;
		default: frame = new UnknownFrame(this);
		}
		frame.readBodyFromStream(reader);
		return frame;
	}
}

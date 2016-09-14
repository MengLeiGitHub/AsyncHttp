package com.async.request2.record;

public class RecordEntity {

	public enum RecordType {
		DOWN(1), UPLOAD(0);
		int type;

		private RecordType(int type) {
			this.type = type;
		}

		public int getType() {
			return type;
		}

	}
	
	private int tryNums=2 ;

	private String url;
	private long startTag;
	private long endTag;
	private int downLoadIndex;
	private long current;
	private String filePath;
	private long totalNum;
	private long contentlength;

	private RecordType type;

	public RecordEntity() {

	}

	public RecordEntity(String url, String filepath) {
		this.url = url;
		this.filePath = filepath;
	}

	public RecordEntity(String url, String filepath, RecordType type) {
		this.url = url;
		this.filePath = filepath;
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getStartTag() {
		return startTag;
	}

	public void setStartTag(long startTag) {
		this.startTag = startTag;
	}

	public long getEndTag() {
		return endTag;
	}

	public void setEndTag(long endTag) {
		this.endTag = endTag;
	}

	public int getDownLoadIndex() {
		return downLoadIndex;
	}

	public void setDownLoadIndex(int downLoadIndex) {
		this.downLoadIndex = downLoadIndex;
	}

	public long getCurrent() {
		return current;
	}

	public void setCurrent(long current) {
		this.current = current;
	}

	public RecordType getType() {
		return type;
	}

	public void setType(RecordType type) {
		this.type = type;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}

	public long getContentlength() {
		return contentlength;
	}

	public void setContentlength(long contentlength) {
		this.contentlength = contentlength;
	}
	
	public int getTryNums() {
		return tryNums;
	}
	public void setTryNums(int tryNums) {
		this.tryNums = tryNums;
	}
	
	
	
}

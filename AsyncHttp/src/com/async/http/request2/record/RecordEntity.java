package com.async.http.request2.record;

import java.io.File;

import com.async.http.utils.StringUtils;

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
	/**
	 * ���ԵĴ���
	 */
	
	private int tryNums=2 ;
	
	/**
	 * ��ַ
	 */

	private String url;
	/**
	 * ���ؿ�ʼλ��
	 */
	
	private long startTag;
	/**
	 * ���صĽ�����
	 */
	private long endTag;
	
	/**
	 * ��ǰ�����̵߳��±�
	 */
	
	private int downLoadIndex;
	/**
	 * ���߳��� ��ǰ������
	 */
	private long current;
	/*
	 * �ļ��洢·��
	 */
	private String filePath;
	/**
	 * ���ļ����� �߳���
	 */
	private long totalNum;
	/**
	 * ��δ�õ�  
	 */
	private long contentlength;
	/**
	 * �����ļ����ܴ�С
	 */
	private long filelength;
	/**
	 * ��¼�ļ���·��
	 */
	private String recordPath;
	/*
	 * ��ʾ�� �� ��������¼�ļ���¼���� ���´��� ����¼�Ѿ���������
	 */
	private long   totalDownloaded;
	
	
	/**
	 * ���������  �ϴ�������
	 */
	
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

	public long getFilelength() {
		return filelength;
	}

	public void setFilelength(long filelength) {
		this.filelength = filelength;
	}

	public String getRecordPath() {
	
		return getRecordPath(this);
	}

	public void setRecordPath(String recordPath) {
		this.recordPath = recordPath;
	}
	
	public long getTotalDownloaded() {
		return totalDownloaded;
	}

	public void setTotalDownloaded(long totalDownloaded) {
		this.totalDownloaded = totalDownloaded;
	}

	private String getRecordPath(RecordEntity recordEntity) {

		if (!StringUtils.isNull(recordPath))
			return  recordPath;

		String url = recordEntity.getUrl();
		String filepath = recordEntity.getFilePath();
		File file = new File(filepath);
		String recordfileParent = file.getParent() + File.separator
				+ url.substring(url.lastIndexOf("/"), url.lastIndexOf("."));
		if (!new File(recordfileParent).exists()) {
			new File(recordfileParent).mkdirs();
		}
		String f = recordfileParent + File.separator
				+ recordEntity.getStartTag() + "_" + recordEntity.getEndTag();

		return f;
	}

}

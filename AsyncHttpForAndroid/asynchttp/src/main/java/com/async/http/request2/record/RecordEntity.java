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
	 * 重试的次数
	 */

	private int tryNums=2 ;

	/**
	 * 网址
	 */

	private String url;
	/**
	 * 下载开始位置
	 */

	private long startTag;
	/**
	 * 下载的结束点
	 */
	private long endTag;

	/**
	 * 当前下载线程的下标
	 */

	private int downLoadIndex;
	/**
	 * 单线程中 当前下载量
	 */
	private long current;
	/*
	 * 文件存储路径
	 */
	private String filePath;
	/**
	 * 该文件下载 线程数
	 */
	private long totalNum;
	/**
	 * 暂未用到  
	 */
	private long contentlength;
	/**
	 * 下载文件的总大小
	 */
	private long filelength;
	/**
	 * 记录文件的路径
	 */
	private String recordPath;
	/*
	 * 表示在 从 请求出错记录文件记录数据 重新创建 ，记录已经下载总量
	 */
	private long   totalDownloaded;


	/**
	 * 任务的类型  上传或下载
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

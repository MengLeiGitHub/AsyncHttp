package com.async.http.request2.record;

import com.async.http.AsyncHttp;
import com.async.http.request2.download;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;


public class RecordManager implements RecordManagerHandler {

	private String caChepath;

	private Thread worker;

	private boolean isStop = false;
	private boolean isrunning;

	private LinkedHashMap<String,RecordEntity> errorqueue;

	private static RecordManager  recordManager;


	public static RecordManager Call(){
		if(recordManager==null){
			recordManager=new RecordManager();
		}
		return recordManager;
	}



	public RecordManager() {
		errorqueue = new LinkedHashMap<String,RecordEntity>();

		createWorker();

	}

	private void createWorker() {
		// TODO Auto-generated method stub

		worker = new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub

				while (!isStop) {
					synchronized (this) {
						isrunning=true;

						LinkedHashMap<String,RecordEntity> errorqueueClone=(LinkedHashMap<String, RecordEntity>) errorqueue.clone();

						synchronized (errorqueue) {

							errorqueue.notify();

							try {
								errorqueue.wait();

							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

						Iterator<String>  iterator=errorqueueClone.keySet().iterator();
						if (errorqueueClone.size() != 0) {

							while(iterator.hasNext()){
								String  key=iterator.next();
								RecordEntity recordEntity =errorqueueClone.get(key);
								String path = recordEntity.getFilePath();
								path=caChepath+File.separator+System.currentTimeMillis()+""+new Random().nextInt(10000);

								RandomAccessFile randomAccessFile = null;
								try {
									randomAccessFile = new RandomAccessFile(path,
											"rw");

									String r =AsyncHttp.instance().getJsonConvertInterface().serialize(recordEntity);

									randomAccessFile.write(r.getBytes());

								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} finally {

									try {
										randomAccessFile.close();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								}


							}

						} else {
							try {
								this.wait(3000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					}
				}

			}

		});

	}

	public void setCaChepath(String caChepath) {
		this.caChepath = caChepath;
		if(!new File(caChepath).exists())new File(caChepath).mkdirs();

	}

	public RecordEntity getErrorTask() {
		// TODO Auto-generated method stub
		return null;
	}

	public LinkedList<RecordEntity> getErrorTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 在程序启动时调用 获取 错误队列 
	 */

	public LinkedList<download> getErrorDownloadTasks() {
		// TODO Auto-generated method stub
		LinkedList<download>  linkedList=new LinkedList<download>();
		Set  set=errorqueue.keySet();
		Iterator<String>  iterator=	set.iterator();
		while(iterator.hasNext()){
			String key=iterator.next();
			RecordEntity val=errorqueue.get(key);
			download  download=new download(val.getUrl());
			download.setRecordEntity(val);
			linkedList.add(download);
		}
		errorqueue.clear();
		return linkedList;
	}

	public  void addErrorTask(RecordEntity recordEntity) {
		// TODO Auto-generated method stub
		RecordEntity obj=errorqueue.get(recordEntity.getFilePath());
		if(obj!=null)obj.setCurrent(recordEntity.getCurrent());
		else  errorqueue.put(recordEntity.getFilePath(), recordEntity);
		if(!isrunning){
			worker.start();
		}
	}

	public void removeErrorTasks(RecordEntity recordEntity) {
		// TODO Auto-generated method stub
		String path=recordEntity.getFilePath();
		File f=new File(path);
		if(f.exists()){
			f.delete();
		}
		if(errorqueue.containsKey(recordEntity.getFilePath())){
			errorqueue.remove(recordEntity.getFilePath());
		}
	}

	public LinkedList<download> getErrorDownloadTasksFromDisk() {
		// TODO Auto-generated method stub
		File file=new File(caChepath);
		File[] f= file.listFiles();
		LinkedList<download> list=new LinkedList<download>();
		for(File fil:f){
			try {
				RandomAccessFile randomAccessFile=new RandomAccessFile(fil, "r");

				String bean=randomAccessFile.readLine();

				RecordEntity r=AsyncHttp.instance().getJsonConvertInterface().deserialize(bean,RecordEntity.class);
				download d=new download(r.getFilePath());
				d.setRecordEntity(r);


				list.add(d);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



		}


		return list;
	}

}

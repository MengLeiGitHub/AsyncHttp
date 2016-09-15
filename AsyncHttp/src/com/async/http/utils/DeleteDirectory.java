package com.async.http.utils;

import java.io.File;

public class DeleteDirectory {
	/**
	 * ɾ����Ŀ¼
	 * 
	 * @param dir
	 *            ��Ҫɾ����Ŀ¼·��
	 */
	private static void doDeleteEmptyDir(String dir) {
		boolean success = (new File(dir)).delete();
		if (success) {
			System.out.println("Successfully deleted empty directory: " + dir);
		} else {
			System.out.println("Failed to delete empty directory: " + dir);
		}
	}

	/**
	 * �ݹ�ɾ��Ŀ¼�µ������ļ�����Ŀ¼�������ļ�
	 * 
	 * @param dir
	 *            ��Ҫɾ�����ļ�Ŀ¼
	 * @return boolean Returns "true" if all deletions were successful. If a
	 *         deletion fails, the method stops attempting to delete and returns
	 *         "false".
	 */
	public  static boolean deleteDir(File dir) {
		try{
			if (dir.isDirectory()) {
				String[] children = dir.list();
	 			// �ݹ�ɾ��Ŀ¼�е���Ŀ¼��
				for (int i = 0; i < children.length; i++) {
					boolean success = deleteDir(new File(dir, children[i]));
					if (!success) {
						return false;
					}
				}
			}
			// Ŀ¼��ʱΪ�գ�����ɾ��
			return dir.delete();
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	 
}

package com.async.pool.RecordCenter;
import java.util.Vector;

import com.async.pool.msg.CustomMessage;

public class sortUtils {

	/**
	 *
	 * description : 快速排序
	 *
	 * @autor ml
	 *
	 *        modify :2016-8-29
	 *
	 *
	 *
	 * @param taskQuence
	 *
	 * @param left
	 *
	 * @param right
	 *
	 * @return
	 */

	public static Vector<CustomMessage> quicksort(Vector<CustomMessage> taskQuence,
												  int left, int right) {

		int dp;

		if (left < right) {

			dp = partition(taskQuence, left, right);

			quicksort(taskQuence, left, dp - 1);

			quicksort(taskQuence, dp + 1, right);

		}

		return taskQuence;

	}

	static int partition(Vector<CustomMessage> taskQuence,
						 int left, int right) {

		CustomMessage pivot = taskQuence.get(left);

		while (left < right) {

			while (left < right
					&& taskQuence.get(right).getTask().getTaskPriority() >= pivot
					.getTask().getTaskPriority())

				right--;

			if (left < right) {

				CustomMessage righttask = taskQuence.get(right);

				taskQuence.set(left++, righttask);

				while (left < right
						&& taskQuence.get(left).getTask().getTaskPriority() <= pivot
						.getTask().getTaskPriority())

					left++;

				if (left < right) {

					CustomMessage lefttask = taskQuence.get(left);

					taskQuence.set(right--, lefttask);

				}

			}
		}

		taskQuence.set(left, pivot);

		return left;

	}

}

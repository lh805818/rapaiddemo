package com.company.project.test;

public class SalaryCal {

	/**
	 *
	 * @author qincd
	 * @date Nov 18, 2014 2:27:26 PM
	 */
	public static void main(String[] args) {
		// 社保
		float sb = 196.52f;
		// 公积金
		int gjj = 100;
		// 全勤奖
		int qqj = 100;
		
		for (int i=5000;i<=30000;i+=1000) {
			// 计算工资在哪个扣税区间
			float aa = i - sb - gjj - 3500;
			// 税率
			float percent = 0.0f;
			// 速算扣除数
			float kcs = 0f;
			
			if (aa <= 1500) {
				percent = 0.03f;
				kcs = 0;
			}
			else if (aa > 1500 && aa <= 4500) {
				percent = 0.1f;
				kcs = 105;
			}
			else if (aa > 4500 && aa <= 9000) {
				percent = 0.2f;
				kcs = 555;
			}
			else if (aa > 9000 && aa <=35000) {
				percent = 0.25f;
				kcs = 1005;
			}
			
			float ks = (i + qqj - sb - gjj - 3500) * percent - kcs;
			float result = i + qqj - sb - gjj - ks;
			System.out.println("工资：" + i + "\t扣税：" + ks + "\t拿到手：" + result);
		}
	}

}

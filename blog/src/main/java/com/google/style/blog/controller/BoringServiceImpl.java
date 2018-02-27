package com.google.style.blog.controller;


import java.util.Calendar;
import java.util.Date;

/**
 * @author 林依伦
 * @date 2018/2/22 17:56
 */
public class BoringServiceImpl implements BoringService {

    /**
     * 一天上班的的秒数
     */
    private static final double SECOND_OF_DAY = 60 * 60 * 8.5;


    @Override
    public void makeTimeSpeedFaster(double monthSalary) {

        System.out.println("正在进行工资计算初始化....");
        long initStart = System.currentTimeMillis();
        Calendar c = Calendar.getInstance();
        System.out.println(String.format("当前时间： %s时%s分%s秒", c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND)));
        //获取本月的天数
        int days = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        //获取每秒的薪水
        double secondSalary = monthSalary / days / SECOND_OF_DAY;
        //已经获取的总共的薪水
        double sumSalary = getAlreadySalary(secondSalary);
        //获取到本日下班剩余的秒数
        long leftSecond = getLeftSecond();
        System.out.println("每秒薪水：" + secondSalary);
        System.out.println("今日离下班剩余秒数：" + leftSecond);
        System.out.println("启动程序时已经上班秒数：" + getAlreadySecond());
        System.out.println("已经获取的薪水：" + sumSalary);
        System.out.println("初始化完毕....");

        long initEnd = System.currentTimeMillis();
        System.out.println(String.format("初始化用时：%sms", initEnd - initStart));
        c.add(Calendar.MILLISECOND, (int) (initEnd - initStart));
        while (leftSecond > 0) {
            c.setTime(new Date(System.currentTimeMillis()));
            sumSalary += secondSalary;
            leftSecond = getLeftSecond();
            System.out.println(String.format("%s时%s分%s秒 获得:%.4f,总计:%.4f,剩余:%s秒", c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND), secondSalary, sumSalary, leftSecond));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("下班了");
    }


    /**
     * 获取距离下班剩余秒数
     *
     * @return
     */
    private long getLeftSecond() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 18);
        c.set(Calendar.MINUTE, 30);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 999);
        return (c.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }

    /**
     * 获取上班已经多少秒
     *
     * @return
     */
    private long getAlreadySecond() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 10);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return (System.currentTimeMillis() - c.getTimeInMillis()) / 1000;
    }

    /**
     * 获取已经收获的薪水
     */
    private double getAlreadySalary(double secondSalary) {
        double sumSalary = secondSalary * getAlreadySecond();

        return sumSalary > 0 ? sumSalary : 0;
    }


    public static void main(String[] args) {
        BoringServiceImpl boringService = new BoringServiceImpl();
        boringService.makeTimeSpeedFaster(7000);
    }
}

package com.xzcoder.kaoshi.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.xzcoder.kaoshi.admin.service.StWrongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClearDbJob
 * 定时job：清理db中一周以前的错题
 * 每天中午12点半执行
 * cron：0 30 12 * * ? *
 * 每天中午12:30执行
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
@Component("clearDbJob")
public class ClearDbJob {

    private final static Logger logger = LoggerFactory.getLogger(ClearDbJob.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private StWrongService stWrongService;

    public void doWork() {
        try {
            long startTime = System.currentTimeMillis();//获取开始时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_YEAR, -7);//日期减去7天
            stWrongService.deleteWrongBeforeDate(calendar.getTime());
            long endTime = System.currentTimeMillis();//获取结束
            logger.info("clearDbJob 执行成功！用时：" + (endTime - startTime) + "ms，date=" + sdf.format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("clearDbJob 执行失败！date=" + sdf.format(new Date()));
        }
    }

}

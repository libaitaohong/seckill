package com.tarscode.dao.cache;

import com.tarscode.dao.SeckillDao;
import com.tarscode.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuyang on 17/3/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {

    private long id = 1001;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private RedisDao redisDao;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void testSeckill() throws Exception {

        //get and put
        Seckill seckill = redisDao.getSeckill(id);
        if (seckill == null) {
            seckill = seckillDao.queryById(id);
            if (seckill != null) {
                String result = redisDao.putSeckill(seckill);
                System.out.println(result);
                seckill = redisDao.getSeckill(id);
                System.out.println(seckill);
            }
        }
    }

}
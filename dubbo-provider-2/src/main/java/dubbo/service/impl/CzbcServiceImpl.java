package dubbo.service.impl;

import com.tiger.service.ZhxtService;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Date 2020/5/30
 * @Author tiger
 */

@Component
@Service(group = "czbc",version = "0.0.2")
public class CzbcServiceImpl implements ZhxtService {
    private static final Logger LOG = LoggerFactory.getLogger(CzbcServiceImpl.class);
    @Override
    public Object getCurstCredit(String name) {
        Random random = new Random();
        int money = random.nextInt(9) * 100;
        LOG.info(name+"："+money);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return money;
    }
}

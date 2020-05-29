package dubbo.service.impl;

import com.tiger.service.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @Date 2020/5/29
 * @Author tiger
 */
@Component
@Service
public class UerServiceImpl implements UserService {

    @Override
    public String hello(String name) {
        System.out.println("say hello to "+name);
        return "hello "+name;
    }
}
